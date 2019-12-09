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

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreate(savedInstanceState)
        val binding =
            DataBindingUtil.inflate<ProductFullViewItemBinding>(inflater, R.layout.product_full_view_item, container, false)
                .apply {
                    lifecycleOwner = this@FullViewFragment
                }
        product.type.observe(this, Observer {
            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
        })

        getIncomingIntent(binding, product)
        return binding.root
    }

    private fun getIncomingIntent(binding: ProductFullViewItemBinding, product: Product) {
            Log.d(TAG, "Product type is ${product.type.value}")
            binding.product = product
            bind(product)
        }

//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        val topSpacingItemDecoration = TopSpacingItemDecoration(30)
//        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerViewProducts)
//        recyclerView.layoutManager = LinearLayoutManager(context!!)
//        recyclerView.adapter = productAdapter
//        recyclerView.addItemDecoration(topSpacingItemDecoration)
//        if (makeVisible) {
//            Log.d("ALO", "Is visible")
//            addOnScrollListener(view)
//        }
//        addDataSet()
//    }
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

//        var image = findViewById<ImageView>(R.id.fv_image)
//
//        val requestOptions = RequestOptions()
//            .placeholder(R.drawable.ic_launcher_background)
//            .error(R.drawable.ic_launcher_background)
//
//        Glide
//            .with(this)
//            .applyDefaultRequestOptions(requestOptions)
//            .load(product.image)
//            .into(image)


    }
}