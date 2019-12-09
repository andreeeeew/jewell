package com.example.jewell

import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.jewell.databinding.ProductFullViewItemBinding
import com.example.jewell.models.Product

class ProductFullViewActivity: AppCompatActivity() {
    private val TAG = "ProductFullViewActivity"
    private lateinit var product: Product

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ProductFullViewItemBinding =
            DataBindingUtil.setContentView<ProductFullViewItemBinding>(this, R.layout.product_full_view_item)
                .apply{
                    lifecycleOwner = this@ProductFullViewActivity

                }
//        setContentView(R.layout.product_full_view_item)

        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        Log.d(TAG, "onCreate: started")
        getIncomingIntent(binding)
    }

    private fun getIncomingIntent(binding: ProductFullViewItemBinding) {
        Log.d(TAG, "getIncomingIntent: checking for incoming intents.")

        if (intent.hasExtra("product")) {
            product = intent.extras?.get("product") as Product
            Log.d("ASD", "Product address in ProductFullViewActivity is ${System.identityHashCode(product)}")
            product.type.observe(this, Observer {
                Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
            })
            Log.d(TAG, "Product type is ${product.type.value}")
            binding.product = product
            bind(product)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("ASD", "Activity was destroyed. product is ${product.type.value}")
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
//        var arrivalDate = findViewById<TextView>(R.id.fv_arrival_date)
//        arrivalDate.text = product.arrivalDate.toString()
//
//        var type = findViewById<TextView>(R.id.fv_type)
//        type.text = product.type.value
//
//        var millesial = findViewById<TextView>(R.id.fv_millesimal)
//        millesial.text = product.millesimal.toString()
//
//        var size = findViewById<TextView>(R.id.fv_size)
//        size.text = product.size.toString()
//
//        var shop = findViewById<TextView>(R.id.fv_shop)
//        Log.d("FDS", "product shop is ${product.shop}")
//        shop.text = product.shop

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