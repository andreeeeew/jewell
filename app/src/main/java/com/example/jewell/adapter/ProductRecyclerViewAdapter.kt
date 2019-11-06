package com.example.jewell.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.jewell.R
import com.example.jewell.models.Product
import kotlinx.android.synthetic.main.layout_product_list_item.view.*


class ProductRecyclerViewAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ProductViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.layout_product_list_item, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return products.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is ProductViewHolder -> {
                holder.bind(products.get(position))
            }
        }
    }

    fun submitList(productList: List<Product>) {
        products = productList
    }

    private var products: List<Product> = ArrayList()
    private val TAG = "RecyclerViewAdapter"




    class ProductViewHolder constructor(
        itemView: View
    ): RecyclerView.ViewHolder(itemView){
        val productImage = itemView.product_image
        val productName = itemView.product_name
        val productDescription = itemView.product_description

        fun bind(product: Product) {
            productName.setText(product.name)
            productDescription.setText(product.description)

            val requestOptions = RequestOptions()
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background)

            Glide.with(itemView.context)
                .applyDefaultRequestOptions(requestOptions)
                .load(product.image)
                .into(productImage)
        }
    }
}