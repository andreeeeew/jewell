package com.example.jewell

import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.jewell.models.Product

class ProductFullViewActivity: AppCompatActivity() {
    private val TAG = "ProductFullViewActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.product_full_view_item)

        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        Log.d(TAG, "onCreate: started")
        getIncomingIntent()
    }

    private fun getIncomingIntent() {
        Log.d(TAG, "getIncomingIntent: checking for incoming intents.")
        var product: Product
        if (intent.hasExtra("product")) {
            product = intent.extras?.get("product") as Product
            bind(product)
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
    fun bind(product: Product) {
        var arrivalDate = findViewById<TextView>(R.id.fv_arrival_date)
        arrivalDate.text = product.arrivalDate.toString()

        var type = findViewById<TextView>(R.id.fv_type)
        type.text = product.type

        var millesial = findViewById<TextView>(R.id.fv_millesimal)
        millesial.text = product.millesimal.toString()

        var size = findViewById<TextView>(R.id.fv_size)
        size.text = product.size.toString()

        var shop = findViewById<TextView>(R.id.fv_shop)
        shop.text = product.shop


    }
}