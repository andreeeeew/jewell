package com.example.jewell

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import androidx.fragment.app.FragmentActivity
import com.example.jewell.adapter.ProductRecyclerViewAdapter
import com.example.jewell.models.Product

class ProductsActivity : FragmentActivity() {
    private val TAG = ""
    private lateinit var inflatedView: View
    private lateinit var productsAdapter: ProductRecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        inflatedView
        setContentView(R.layout.product_full_view_item)

        Log.d(TAG, "onCreate: started")
        getIncomingIntent()
    }

    override fun getIntent(): Intent {
        return super.getIntent()
    }

    private fun getIncomingIntent() {
        Log.d(TAG, "getIncomingIntent: checking for incoming intents.")
        var product: List<Product>
        if (intent.hasExtra("products")) {
            product = intent.extras?.get("products") as List<Product>
//            bind(products)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        var id = item.itemId

        if (id == android.R.id.home) {
            this.finish()
        }

        return super.onOptionsItemSelected(item)
    }

    // TODO(andreew) finish binding
//    fun bind(product: List<Product>) {
//        var arrivalDate = findViewById<TextView>(R.id.fv_arrival_date)
//        arrivalDate.text = product.arrivalDate.toString()
//
//        var type = findViewById<TextView>(R.id.fv_type)
//        type.text = product.type
//
//        var millesial = findViewById<TextView>(R.id.fv_millesimal)
//        millesial.text = product.millesimal.toString()
//
//        var size = findViewById<TextView>(R.id.fv_size)
//        size.text = product.size.toString()
//
//        var shop = findViewById<TextView>(R.id.fv_shop)
//        shop.text = product.shop
//
//        var image = findViewById<ImageView>(R.id.fv_image)
//
//        val requestOptions = RequestOptions()
//            .placeholder(R.drawable.ic_launcher_background)
//            .error(R.drawable.ic_launcher_background)
//
//        Glide
//            .with(this)
//            .applyDefaultRequestOptions(requestOptions)
//            .load(product.image)
//            .into(image)
//
//
//    }

}