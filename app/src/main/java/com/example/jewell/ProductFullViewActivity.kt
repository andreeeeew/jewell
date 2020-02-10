package com.example.jewell

import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.jewell.databinding.ProductFullViewItemBinding
import com.example.jewell.viewmodels.ProductViewModel

class ProductFullViewActivity: AppCompatActivity() {
    private val TAG = "ProductFullViewActivity"
    private lateinit var product: ProductViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ProductFullViewItemBinding =
            DataBindingUtil.setContentView<ProductFullViewItemBinding>(this, R.layout.product_full_view_item)
                .apply{
                    lifecycleOwner = this@ProductFullViewActivity

                }

        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        Log.d(TAG, "onCreate: started")
        getIncomingIntent(binding)
    }

    private fun getIncomingIntent(binding: ProductFullViewItemBinding) {
        Log.d(TAG, "getIncomingIntent: checking for incoming intents.")

        if (intent.hasExtra("product")) {
            product = intent.extras?.get("product") as ProductViewModel
            Log.d("ASD", "Product address in ProductFullViewActivity is ${System.identityHashCode(product)}")
            Log.d(TAG, "Product type is ${product.getType()}")
            binding.product = product
            bind(product)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        var id = item.itemId

        if (id == android.R.id.home) {
            this.finish()
        }

        return super.onOptionsItemSelected(item)
    }

    fun bind(product: ProductViewModel) {
        var image = findViewById<ImageView>(R.id.fv_image)

        val requestOptions = RequestOptions()
            .placeholder(R.drawable.ic_launcher_background)
            .error(R.drawable.ic_launcher_background)

        Glide
            .with(this)
            .applyDefaultRequestOptions(requestOptions)
            .load(product.getImage())
            .into(image)


    }
}