package com.example.jewell.fragment

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.jewell.DataSource
import com.example.jewell.R
import com.example.jewell.activity.BarCodeScannerActivity
import com.example.jewell.adapter.ProductRecyclerViewAdapter
import com.example.jewell.view_decorator.TopSpacingItemDecoration
import kotlinx.android.synthetic.main.products_fragment.view.*

class ProductsRecyclerViewFragment(val makeVisible: Boolean) : Fragment() {
    private lateinit var inflatedView: View
    private lateinit var productAdapter: ProductRecyclerViewAdapter
    private lateinit var intent: Intent

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        inflatedView = inflater.inflate(R.layout.products_fragment, container, false)
        intent = Intent(context!!, BarCodeScannerActivity::class.java)
        productAdapter = ProductRecyclerViewAdapter(context!!)
        return inflatedView
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
        addDataSet()
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
            Log.d("DSA", "FAB was clicked")
            startActivity(intent.apply {
                //TODO(andreew) create hashmap of barcodes and done products. Think about it for a while.
                putExtra("products", this)
            })
        }
    }

    // TODO (andreew) rather don't need it. Need to pass hashmap to barcode scanner and play with it there
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        Log.d("SDA", "resultcode is 0")
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode === 0) {
            Log.d("SDA", "resultcode is 0")
            if (resultCode === Activity.RESULT_OK) { // contents contains whatever was encoded
                val contents: String = intent.getStringExtra("SCAN_RESULT")
                Log.d("DSA", "contents is $contents")
                // Format contains the type of code i.e. UPC, EAN, QRCode etc...
                val format: String = intent.getStringExtra("SCAN_RESULT_FORMAT")
                Log.d("DSA", "format is $format")
            }
        }
    }

    fun addDataSet() {
        val data = DataSource.createProductsDataSet()
        productAdapter.submitList(data)
    }
}
