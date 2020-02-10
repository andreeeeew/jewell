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
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        var id = item.itemId

        if (id == android.R.id.home) {
            this.finish()
        }

        return super.onOptionsItemSelected(item)
    }
}