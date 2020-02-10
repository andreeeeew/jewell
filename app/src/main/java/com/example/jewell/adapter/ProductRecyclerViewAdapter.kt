package com.example.jewell.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.jewell.R
import com.example.jewell.databinding.LayoutProductListItemBinding
import com.example.jewell.fragment.FullViewFragment
import com.example.jewell.models.Product
import com.example.jewell.presenter.ProductPresenter
import com.example.jewell.viewmodels.ProductViewModel
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
        val BackStackName = "Products"
    }

    private lateinit var lastProductViewHolder: ProductViewHolder
    private var supportFragmentManager: FragmentManager
    private var mContext: Context
    private var products: MutableList<Product> = ArrayList()
    private var productsViewModels: MutableList<ProductViewModel> = ArrayList()
    private var keys: MutableList<String> = ArrayList()
    var inventorizedBarCodes: MutableList<String>
    private val TAG = "RecyclerViewAdapter"
    lateinit var layoutProductListItemBinding: LayoutProductListItemBinding
    private val barcodeToViewHolder: HashMap<String, ProductViewHolder> = HashMap()

    constructor(context: Context, supportFragmentManager: FragmentManager, inventorizedBarCodes: MutableList<String>) : super() {
        this.inventorizedBarCodes = inventorizedBarCodes
        mContext = context
        this.supportFragmentManager = supportFragmentManager
    }

    override fun onViewDetachedFromWindow(holder: ProductViewHolder) {
        super.onViewDetachedFromWindow(holder)
        Log.d(TAG, "View Detached from window")
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        layoutProductListItemBinding = LayoutProductListItemBinding.inflate(layoutInflater, parent, false)
        layoutProductListItemBinding.presenter = ProductPresenter()
        return ProductViewHolder(layoutProductListItemBinding)
    }

    override fun getItemCount(): Int {
        return products.size
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        var product = products[position]
        holder.bind(productsViewModels[position])
        barcodeToViewHolder[product.barCode] = holder
        if (inventorizedBarCodes.contains(product.barCode)) {
            holder.binding.productConstraintLayout.background = ContextCompat.getDrawable(mContext, R.drawable.custom_done)
        }

        backStackListener(holder, products[position])
        holder.itemView.setOnClickListener {
            Log.d(TAG, "R == 0")
            supportFragmentManager.beginTransaction()
                .replace(R.id.productsRelativeLayout, FullViewFragment(productsViewModels[position]))
                .addToBackStack(FullViewFragment.BackStackName)
                .commit()
        }
        lastProductViewHolder = holder
    }

    private fun backStackListener(holder: ProductViewHolder, product: Product) {
        supportFragmentManager.addOnBackStackChangedListener {
            val position: Int = supportFragmentManager.backStackEntryCount
            if (position == 0) {
                holder.productType.text = product.type
            }
        }
    }

    fun addProduct(product: Product, key: String) {
        products.add(product)
        keys.add(key)
        productsViewModels.add(ProductViewModel(product, key))
        Log.d(TAG, "Before notifyDataSetChanged()")
        notifyDataSetChanged()
    }

    fun modifyProduct(product: Product) {
        Log.d(TAG, "new modified product is ${product.toString()}")
        val index = products.
            indexOfFirst {
                it.barCode == product.barCode
            }
        Log.d(TAG, "index of first is $index")
        products[index] = product
        productsViewModels[index].product = product
        Log.d(TAG, "new product is ${products[index]} and coresponding viewmodel product is ${productsViewModels[index].product}")
        Log.d(TAG, "Before notifyDataSetChanged()")
        notifyDataSetChanged()
    }

    fun  markProductAsInventorized(barcode: String) {
        if (inventorizedBarCodes.contains(barcode))
            return
        Log.d(TAG, "inventorizedBarCodes ${inventorizedBarCodes.toString()}, new bar code is $barcode")
        inventorizedBarCodes.add(barcode)
        Log.d(TAG, "new barcodes are ${inventorizedBarCodes.toString()}")
        barcodeToViewHolder[barcode]?.binding?.productConstraintLayout?.background = ContextCompat.getDrawable(mContext, R.drawable.custom_done)
    }


    class ProductViewHolder constructor(
        itemView: View
    ): RecyclerView.ViewHolder(itemView){

        lateinit var binding: LayoutProductListItemBinding
        constructor(binding: LayoutProductListItemBinding) : this(binding.root) {
            this.binding = binding
        }

        val productImage = itemView.product_image
        val productName = itemView.product_name
        val productType = itemView.product_type
        val TAG = "ProductViewHolder"

        fun bind(product: ProductViewModel) {
            binding.product = product
            binding.executePendingBindings()
        }
    }
}