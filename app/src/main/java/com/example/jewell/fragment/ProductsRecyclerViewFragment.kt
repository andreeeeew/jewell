package com.example.jewell.fragment

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.jewell.PreviewActivity
import com.example.jewell.R
import com.example.jewell.activity.BarCodeScannerActivity
import com.example.jewell.adapter.ProductRecyclerViewAdapter
import com.example.jewell.models.Product
import com.example.jewell.view_decorator.TopSpacingItemDecoration
import com.example.jewell.viewmodels.StockTakingViewModel
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.products_fragment.view.*

class ProductsRecyclerViewFragment(
    val supportFragmentManager: FragmentManager,
    val products: MutableList<Product>,
    val stockTakingViewModel: StockTakingViewModel,
    val makeVisible: Boolean
) : Fragment() {
    private lateinit var inflatedView: View
    lateinit var productAdapter: ProductRecyclerViewAdapter
    private lateinit var intent: Intent
    private val TAG = "ProductsRecyclerViewFragment"
    lateinit var barCodeToProduct: HashMap<String, Product>


    lateinit var mChildEventListener: ChildEventListener

    companion object {
        lateinit var lifecycleOwner: LifecycleOwner
        lateinit var mFirebaseDatabase: FirebaseDatabase
        lateinit var mProductsDatabaseReference: DatabaseReference
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        lifecycleOwner = this
        inflatedView = inflater.inflate(R.layout.products_fragment, container, false)
        intent = Intent(context!!, BarCodeScannerActivity::class.java)
        productAdapter = ProductRecyclerViewAdapter(context!!, supportFragmentManager, stockTakingViewModel.stockTaking.inventorizedBarCodes)
        populateBarCodeToProduct()
        return inflatedView
    }

    private fun populateBarCodeToProduct() {
        barCodeToProduct = HashMap()
        products.forEach {
            barCodeToProduct[it.barCode] = it
        }
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val topSpacingItemDecoration = TopSpacingItemDecoration(30)
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerViewProducts)

        mFirebaseDatabase = FirebaseDatabase.getInstance()
        mProductsDatabaseReference = mFirebaseDatabase.reference.child("products")

        mChildEventListener = object: ChildEventListener {
            override fun onCancelled(p0: DatabaseError) {
            }

            override fun onChildMoved(p0: DataSnapshot, p1: String?) {
            }

            override fun onChildChanged(p0: DataSnapshot, p1: String?) {
                productAdapter!!.modifyProduct(p0.getValue(Product::class.java)!!)
            }

            override fun onChildAdded(p0: DataSnapshot, p1: String?) {
                var newProduct = p0.getValue(Product::class.java)
                productAdapter!!.addProduct(newProduct!!, p0.key!!)
            }

            override fun onChildRemoved(p0: DataSnapshot) {
            }
         }
        mProductsDatabaseReference.addChildEventListener(mChildEventListener)

        recyclerView.layoutManager = LinearLayoutManager(context!!)
        recyclerView.adapter = productAdapter
        recyclerView.addItemDecoration(topSpacingItemDecoration)
        if (makeVisible) {
            addOnScrollListener(view)
        }
    }

    private fun addOnScrollListener(view: View) {
        val fab = view.products_fab
        fab.show()
        val recyclerView = view.recyclerViewProducts
        recyclerView.addOnScrollListener(object: RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (dy > 0 && fab.visibility == View.VISIBLE) {
                    fab.hide()
                } else if (dy < 0 && fab.visibility != View.VISIBLE) {
                    fab.show()
                }
            }
        })
        fab.setOnClickListener {
            Log.d(TAG, "view is ${this.view}")
            startActivityForResult(intent, 1337)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode === 1337) {
            if (resultCode === Activity.RESULT_OK) { // contents contains whatever was encoded
                val barcode: String = data!!.getStringExtra("barcode")
                Log.d(TAG, "barcode is $barcode")
                if (products.indexOfFirst{ it.barCode.equals(barcode) } != -1) {
                    Log.d(TAG, "barcode exists")
                    var intent = Intent(context, PreviewActivity::class.java)
                    intent.putExtra("product", products.first { it.barCode.equals(barcode) })
                    startActivityForResult(intent, 1488)
                }
            }
        } else if (requestCode == 1488) {
            if (resultCode == Activity.RESULT_OK) {
                val barcode: String = data!!.getStringExtra("barcode")
                val success: Boolean = data!!.getBooleanExtra("success", false)
                Log.d(TAG, "success is $success, barcode is $barcode")
                if (!success)
                    return

                productAdapter!!.markProductAsInventorized(barcode)
                if (!stockTakingViewModel.stockTaking.inventorizedBarCodes.contains(barcode)){
                    stockTakingViewModel.stockTaking.inventorizedBarCodes.add(barcode)
                }
                Log.d(TAG, "stocktaking view model key is ${stockTakingViewModel.key}")
                Log.d(TAG, "new stocktaking barcodes are ${stockTakingViewModel.stockTaking.inventorizedBarCodes.toString()}")
                InventorizationRecyclerViewFragment
                    .mInventorizationsDatabaseReference
                    .child(stockTakingViewModel.key).setValue(stockTakingViewModel.stockTaking)
            }
        }
    }
}
