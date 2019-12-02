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
import com.example.jewell.models.StockTaking
import kotlinx.android.synthetic.main.layout_inventorization_list_item.view.*
import kotlinx.android.synthetic.main.products_fragment.view.*


class InventorizationRecyclerViewAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private var mContext: Context
    private var inventorizations: List<StockTaking> = ArrayList()
    private val TAG = "InventorizationRecyclerViewAdapter"
    private lateinit var supportFragmentManager: FragmentManager

    constructor(context: Context, supportFragmentManager: FragmentManager) : super() {
        mContext = context
        this.supportFragmentManager = supportFragmentManager
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return InventorizationViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.layout_inventorization_list_item, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return inventorizations.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is InventorizationViewHolder -> {
                holder.bind(inventorizations[position])
                holder.itemView.setOnClickListener {
                    Log.d(TAG, "inventorization was clicked")
                    var superIntent = Intent(mContext, ProductsRecyclerViewFragment::class.java)

                    val tr = supportFragmentManager.beginTransaction()
                    tr.replace(R.id.inventorizationRelativeLayout, ProductsRecyclerViewFragment(true))
                    tr.addToBackStack("products")
                    tr.commit()
//                    addOnScrollListener(holder, superIntent)

//                    var intent = Intent(mContext, ProductFullViewActivity::class.java)
//                    intent.putExtra("inventorization", inventorizations[position])
//                    mContext.startActivity(superIntent)
                }
            }
        }
    }

    private fun addOnScrollListener(
        holder: InventorizationViewHolder,
        superIntent: Intent
    ) {
        val fab = holder.itemView.products_fab
        val recyclerView = holder.itemView.recyclerViewProducts
        recyclerView.addOnScrollListener(object: RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (dy > 0 && fab.visibility == View.VISIBLE) {
                    fab.hide()
                } else if (dy < 0 && fab.visibility != View.VISIBLE) {
                    fab.show()
                }
            }
        })

//        fab.setOnClickListener {
//            Log.d("DSA", "FAB was clicked")
//            startActivityForResult(intent, 0)
//        }
    }

    fun submitList(inventorizationList: List<StockTaking>) {
        inventorizations = inventorizationList
    }

    class InventorizationViewHolder constructor(
        itemView: View
    ): RecyclerView.ViewHolder(itemView){
        val inventorizationImage = itemView.inventorization_image
        val inventorizationStoreName = itemView.inventorization_store_name
//        val inventorizationLastInvent = itemView.inventorization_last_inventorization

        fun bind(inventorization: StockTaking) {
            inventorizationStoreName.text = inventorization.store.name
//            inventorizationProducts.text = inventorization.products.size.toString()
//            inventorizationLastInvent.text = inventorization.lastInvent.toString()

            val requestOptions = RequestOptions()
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background)

            Glide.with(itemView.context)
                .applyDefaultRequestOptions(requestOptions)
                .load(inventorization.store.image)
                .into(inventorizationImage)
        }
    }
}