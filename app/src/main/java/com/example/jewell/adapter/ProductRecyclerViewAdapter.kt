package com.example.jewell.adapter

import android.content.Context
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
    private var inventorizedBarCodes = HashSet<String>()
    private val TAG = "RecyclerViewAdapter"
    lateinit var layoutProductListItemBinding: LayoutProductListItemBinding
    private val barcodeToViewHolder: HashMap<String, ProductViewHolder> = HashMap()

    constructor(context: Context, supportFragmentManager: FragmentManager) : super() {
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
        //TODO(andreew) mark products as invetorized if it's already inventorized
        var product = products[position]
        holder.bind(productsViewModels[position])


        barcodeToViewHolder[product.barCode] = holder
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

    fun submitProducts(
        productList: MutableList<Product>,
        inventorizedBarCodes: HashSet<String>
    ) {
        products = productList
        this.inventorizedBarCodes = inventorizedBarCodes
    }

    fun addProduct(product: Product, key: String) {
        //TODO(andreew) add to inventorized if it works
        products.add(product)
        keys.add(key)
        productsViewModels.add(ProductViewModel(product, key))
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
        notifyDataSetChanged()
//        notifyItemChanged(index)
    }

    fun markProductAsInventorized(barcode: String) {
        inventorizedBarCodes.add(barcode)
        barcodeToViewHolder[barcode]?.binding?.productConstraintLayout?.setBackgroundColor(Color.GREEN)
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