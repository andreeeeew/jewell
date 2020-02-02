package com.example.jewell.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.jewell.R
import com.example.jewell.databinding.ProductPreviewBinding
import com.example.jewell.models.Product

class PreviewFragment(val product: Product): Fragment() {
    private val TAG = "PreviewViewFragment"

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreate(savedInstanceState)
        val binding =
            DataBindingUtil.inflate<ProductPreviewBinding>(inflater, R.layout.product_preview, container, false)
                .apply {
                }
        getIncomingIntent(binding)

        return binding.root
    }

    private fun getIncomingIntent(binding: ProductPreviewBinding) {
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