package com.example.jewell.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.example.jewell.R
import com.example.jewell.databinding.ProductFullViewItemBinding
import com.example.jewell.models.Product

class FullViewFragment(val product: Product): Fragment() {
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
        Log.d(TAG, "Product address is ${System.identityHashCode(product)}")
        Log.d(TAG, "Has active obs? ${product.type.hasActiveObservers()}")
        product.type.observe(this, Observer {
            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
        })
        Log.d(TAG, "Product address is ${System.identityHashCode(product)}")

        return binding.root
    }

    private fun getIncomingIntent(binding: ProductFullViewItemBinding) {
            Log.d(TAG, "Product type is ${product.type.value}")
            binding.product = product
        }
}