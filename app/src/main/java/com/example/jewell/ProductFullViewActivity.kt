package com.example.jewell

import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.jewell.databinding.ProductFullViewItemBinding
import com.example.jewell.models.Product

class ProductFullViewActivity: AppCompatActivity() {
    private val TAG = "ProductFullViewActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ProductFullViewItemBinding =
            DataBindingUtil.setContentView(this, R.layout.product_full_view_item)
//        setContentView(R.layout.product_full_view_item)

        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        Log.d(TAG, "onCreate: started")
        getIncomingIntent(binding)
    }

    private fun getIncomingIntent(binding: ProductFullViewItemBinding) {
        Log.d(TAG, "getIncomingIntent: checking for incoming intents.")
        var product: Product
        if (intent.hasExtra("product")) {
            product = intent.extras?.get("product") as Product
            binding.product = product
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
        Log.d("FDS", "product shop is ${product.shop}")
        shop.text = product.shop

        var image = findViewById<ImageView>(R.id.fv_image)

        val requestOptions = RequestOptions()
            .placeholder(R.drawable.ic_launcher_background)
            .error(R.drawable.ic_launcher_background)

        Glide
            .with(this)
            .applyDefaultRequestOptions(requestOptions)
            .load(product.image)
            .into(image)


    }
}