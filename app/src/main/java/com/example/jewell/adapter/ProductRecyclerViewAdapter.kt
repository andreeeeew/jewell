package com.example.jewell.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.jewell.R
import com.example.jewell.databinding.LayoutProductListItemBinding
import com.example.jewell.models.Product
import com.example.jewell.presenter.ProductPresenter
import de.hdodenhof.circleimageview.CircleImageView
import kotlinx.android.synthetic.main.layout_product_list_item.view.*
import java.io.Serializable


class ProductRecyclerViewAdapter : RecyclerView.Adapter<ProductRecyclerViewAdapter.ProductViewHolder> {
    companion object DataBindingAdapter: Serializable {
        @BindingAdapter("bind:product_image_url")
        @JvmStatic
        fun loadImage(view: CircleImageView, url: String) {
            val requestOptions = RequestOptions()
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background)
            Glide.with(view.context)
                .applyDefaultRequestOptions(requestOptions)
                .load(url)
                .into(view)
        }
    }
    private var mContext: Context
    private var products: List<Product> = ArrayList()
    private val inventorised = HashSet<String>()
    private val TAG = "RecyclerViewAdapter"

    constructor(context: Context) : super() {
        mContext = context
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val layoutProductListItemBinding = LayoutProductListItemBinding.inflate(layoutInflater, parent, false)
        layoutProductListItemBinding.presenter = ProductPresenter()
//        val presenter = ProductPresenter()
//        layoutProductListItemBinding.setVariable(BR.presenter, presenter)
        return ProductViewHolder(layoutProductListItemBinding)
    }

    override fun getItemCount(): Int {
        return products.size
    }



    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        var product = products[position]
        holder.bind(product)
//        when (holder) {
//            is ProductViewHolder -> {
//                holder.bind(products[position])
//                Log.d(TAG, "product barcode is ${products[position].barCode}")
//                Log.d(TAG, "inventorised is ${inventorised.toList().toString()}")
//                if (inventorised.contains(products[position].barCode)) {
//                    holder.itemView.product_constraint_layout.setBackgroundColor(Color.GREEN)
//                }
//                holder.itemView.setOnClickListener {
//                    Log.d(TAG, "Item was clicked")
//
//                    var intent = Intent(mContext, ProductFullViewActivity::class.java)
//                    intent.putExtra("product", products[position])
//                    mContext.startActivity(intent)
//                }
//            }
//        }
    }

    fun submitList(productList: List<Product>) {
        products = productList
    }

    fun markProudctAsInventorized(barcode: String) {
        inventorised.add(barcode)
    }


    class ProductViewHolder constructor(
        itemView: View
    ): RecyclerView.ViewHolder(itemView){


        private lateinit var binding: LayoutProductListItemBinding
        constructor(binding: LayoutProductListItemBinding) : this(binding.root) {
            this.binding = binding
        }

        val productImage = itemView.product_image
        val productName = itemView.product_name
        val productType = itemView.product_type

        fun bind(product: Product) {
            binding.product = product
            binding.executePendingBindings()
//            productType.setText(product.type)
//
//            val requestOptions = RequestOptions()
//                .placeholder(R.drawable.ic_launcher_background)
//                .error(R.drawable.ic_launcher_background)
//
//            Glide.with(itemView.context)
//                .applyDefaultRequestOptions(requestOptions)
//                .load(product.image)
//                .into(productImage)
        }
    }
}