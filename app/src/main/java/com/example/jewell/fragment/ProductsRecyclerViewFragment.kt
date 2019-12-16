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
import com.example.jewell.R
import com.example.jewell.activity.BarCodeScannerActivity
import com.example.jewell.adapter.ProductRecyclerViewAdapter
import com.example.jewell.models.Product
import com.example.jewell.view_decorator.TopSpacingItemDecoration
import kotlinx.android.synthetic.main.products_fragment.view.*
import kotlin.random.Random

class ProductsRecyclerViewFragment(
    val supportFragmentManager: FragmentManager,
    val products: List<Product>,
    val inventorizedBarCodes: HashSet<String>,
    val makeVisible: Boolean
) : Fragment() {
    private lateinit var inflatedView: View
    private lateinit var productAdapter: ProductRecyclerViewAdapter
    private lateinit var intent: Intent
    private val TAG = "ProductsRecyclerViewFragment"
    lateinit var barCodeToProduct: HashMap<String, Product>

    companion object {
        lateinit var lifecycleOwner: LifecycleOwner
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        lifecycleOwner = this
        inflatedView = inflater.inflate(R.layout.products_fragment, container, false)
        intent = Intent(context!!, BarCodeScannerActivity::class.java)
        productAdapter = ProductRecyclerViewAdapter(context!!, supportFragmentManager)
        populateBarCodeToProduct()
        return inflatedView
    }

    private fun populateBarCodeToProduct() {
        barCodeToProduct = HashMap()
        products.forEach {
            barCodeToProduct.put(it.barCode, it)
        }
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val topSpacingItemDecoration = TopSpacingItemDecoration(30)
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerViewProducts)
        recyclerView.layoutManager = LinearLayoutManager(context!!)
        recyclerView.adapter = productAdapter
        recyclerView.addItemDecoration(topSpacingItemDecoration)
        if (makeVisible) {
            Log.d("ALO", "Is visible")
            addOnScrollListener(view)
        }
        productAdapter.submitProducts(products, inventorizedBarCodes)
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
            val barcode = Random.nextInt(1, 9).toString()
            Log.d("DSA", "FAB was clicked, barcode is $barcode")
            productAdapter.markProductAsInventorized(barcode)
//            startActivityForResult(intent, 0)
            //TODO(andreew) pass barCodeToProduct to this activity
            startActivityForResult(intent, 1337)
        }
    }

    // TODO (andreew) rather don't need it. Need to pass hashmap to barcode scanner and play with it there
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        Log.d("SDA", "requestcode is $requestCode resultcode is $resultCode")
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode === 1337) {
            Log.d("SDA", "resultcode is 0")
            if (resultCode === Activity.RESULT_OK) { // contents contains whatever was encoded
                val contents: String = data!!.getStringExtra("barcode")
                productAdapter.markProductAsInventorized("123")
                Log.d("DSA", "contents is $contents")
            }
        }
    }
}
