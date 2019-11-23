package com.example.jewell.adapter

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.jewell.ProductFullViewActivity
import com.example.jewell.R
import com.example.jewell.models.Store
import kotlinx.android.synthetic.main.layout_store_list_item.view.*

class StoreRecyclerViewAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private var mContext: Context
    private var stores: List<Store> = ArrayList()
    private val TAG = "RecyclerViewAdapter"

    constructor(context: Context) : super() {
        mContext = context
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return StoreViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.layout_store_list_item, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return stores.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is StoreViewHolder -> {
                holder.bind(stores[position])
                holder.itemView.setOnClickListener {
                    Log.d(TAG, "Store was clicked")

                    var intent = Intent(mContext, ProductFullViewActivity::class.java)
                    intent.putExtra("store", stores[position])
                    mContext.startActivity(intent)
                }
            }
        }
    }

    fun submitList(storesList: List<Store>) {
        stores = storesList
    }

    class StoreViewHolder constructor(
        itemView: View
    ): RecyclerView.ViewHolder(itemView){
        val storeImage = itemView.store_image
        val storeName = itemView.store_name
        val storeProducts = itemView.store_products
        val storeLastInvent = itemView.store_last_inventorization

        fun bind(store: Store) {
            storeName.text = store.name
            storeProducts.text = store.products.size.toString()
            storeLastInvent.text = store.lastInvent.toString()

            val requestOptions = RequestOptions()
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background)

            Glide.with(itemView.context)
                .applyDefaultRequestOptions(requestOptions)
                .load(store.image)
                .into(storeImage)
        }
    }
}