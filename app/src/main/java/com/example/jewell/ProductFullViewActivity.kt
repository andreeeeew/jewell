package com.example.jewell

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.jewell.models.Product

class ProductFullViewActivity: AppCompatActivity() {
    private val TAG = "ProductFullViewActivity"

    // TODO(andreew) inject data in layout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.product_full_view_item)
        Log.d(TAG, "onCreate: started")
    }

    private fun getIncomingIntent() {
        Log.d(TAG, "getIncomingIntent: checking for incoming intents.")
        var product: Product
        if (intent.hasExtra("product")) {
            product = intent.extras?.get("product") as Product
        }
    }

    fun bind() {


    }
}