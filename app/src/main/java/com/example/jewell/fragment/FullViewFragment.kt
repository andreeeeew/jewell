package com.example.jewell.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.jewell.R
import com.example.jewell.databinding.ProductFullViewItemBinding
import com.example.jewell.viewmodels.ProductViewModel

class FullViewFragment(val product: ProductViewModel): Fragment() {
    private val TAG = "FullViewFragment"
    companion object {
        val BackStackName = "FullView"
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreate(savedInstanceState)
        val binding =
                DataBindingUtil.inflate<ProductFullViewItemBinding>(inflater, R.layout.product_full_view_item, container, false)
                .apply {
                }
        getIncomingIntent(binding)


        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "On destroy, with key equals to ${product.key} and barcode is ${product.product.barCode}")
        val curProduct = ProductsRecyclerViewFragment.mProductsDatabaseReference.child(product.key)
        curProduct.setValue(product.product)
    }

    private fun getIncomingIntent(binding: ProductFullViewItemBinding) {
        Log.d(TAG, "Product type is ${product.getType()}")
        binding.product = product
        val requestOptions = RequestOptions()
            .placeholder(R.drawable.ic_launcher_background)
            .error(R.drawable.ic_launcher_background)

        Glide.with(this)
            .applyDefaultRequestOptions(requestOptions)
            .load(product.getImage())
            .into(binding.fvImage)
    }

}