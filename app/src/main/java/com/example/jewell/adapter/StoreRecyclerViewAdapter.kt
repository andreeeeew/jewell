package com.example.jewell.adapter

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.jewell.R
import com.example.jewell.fragment.ProductsRecyclerViewFragment
import com.example.jewell.models.Store
import kotlinx.android.synthetic.main.layout_store_list_item.view.*

class StoreRecyclerViewAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private var mContext: Context
    private var stores: MutableList<Store> = ArrayList()
    private val TAG = "StoreRecyclerViewAdapter"
    private var supportFragmentManager: FragmentManager

    constructor(context: Context, supportFragmentManager: FragmentManager) : super() {
        mContext = context
        this.supportFragmentManager = supportFragmentManager
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
        val curStore = stores[position]
        when (holder) {
            is StoreViewHolder -> {
                holder.bind(curStore)
                holder.itemView.setOnClickListener {
                    Log.d(TAG, "Store was clicked")

                    var superIntent = Intent(mContext, ProductsRecyclerViewFragment::class.java)
                    val tr = supportFragmentManager.beginTransaction()
                    tr.replace(R.id.storeRelativeLayout, ProductsRecyclerViewFragment(
                        supportFragmentManager,
                        curStore.products,
                        ArrayList(),
                        false
                    ))
                    tr.addToBackStack("products")
                    tr.commit()

//                    var intent = Intent(mContext, ProductFullViewActivity::class.java)
//                    intent.putExtra("store", stores[position])
//                    mContext.startActivity(superIntent)
                }
            }
        }
    }

    fun addStore(store: Store) {
        stores.add(store)
        notifyDataSetChanged()
    }

    fun submitList(storesList: MutableList<Store>) {
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