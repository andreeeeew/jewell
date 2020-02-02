package com.example.jewell

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.jewell.databinding.ProductPreviewBinding
import com.example.jewell.models.Product

class PreviewActivity: AppCompatActivity() {
    private val TAG = "ProductFullViewActivity"
    private lateinit var product: Product

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ProductPreviewBinding =
            DataBindingUtil.setContentView(this, R.layout.product_preview)

        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        Log.d(TAG, "onCreate: started")
        getIncomingIntent(binding)
        setOnClickListeners()
    }

    private fun setOnClickListeners() {
        val inventorize =  findViewById(R.id.add_preview_button) as Button
        inventorize.setOnClickListener {
            val returnIntent = Intent()
            returnIntent.putExtra("success", true)
            returnIntent.putExtra("barcode", product.barCode)
            setResult(Activity.RESULT_OK, returnIntent)
            finish()
        }
        val cancel = findViewById(R.id.cancel_preview_button) as Button
        cancel.setOnClickListener {
            val returnIntent = Intent()
            returnIntent.putExtra("success", false)
            returnIntent.putExtra("barcode", product.barCode)
            setResult(Activity.RESULT_OK, returnIntent)
            finish()
        }
    }

    private fun getIncomingIntent(binding: ProductPreviewBinding) {
        Log.d(TAG, "getIncomingIntent: checking for incoming intents.")

        if (intent.hasExtra("product")) {
            product = intent.extras?.get("product") as Product
            binding.product = product

            val requestOptions = RequestOptions()
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background)

            Glide.with(this)
                .applyDefaultRequestOptions(requestOptions)
                .load(product.image)
                .into(binding.productImage)
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