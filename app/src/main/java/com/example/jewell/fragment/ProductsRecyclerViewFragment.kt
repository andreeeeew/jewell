package com.example.jewell.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.jewell.DataSource
import com.example.jewell.R
import com.example.jewell.adapter.ProductRecyclerViewAdapter
import com.example.jewell.view_decorator.TopSpacingItemDecoration

class ProductsRecyclerViewFragment : Fragment() {
    private lateinit var inflatedView: View
    private lateinit var productAdapter: ProductRecyclerViewAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        inflatedView = inflater.inflate(R.layout.products_fragment, container, false)
        productAdapter = ProductRecyclerViewAdapter(context!!)
        return inflatedView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val topSpacingItemDecoration = TopSpacingItemDecoration(30)
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerViewProducts)
        recyclerView.layoutManager = LinearLayoutManager(context!!)
        recyclerView.adapter = productAdapter
        recyclerView.addItemDecoration(topSpacingItemDecoration)
        addDataSet()
    }

    fun addDataSet() {
        val data = DataSource.createProductsDataSet()
        productAdapter.submitList(data)
    }
}
