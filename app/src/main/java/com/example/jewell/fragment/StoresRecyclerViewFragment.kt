package com.example.jewell.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.jewell.DataSource
import com.example.jewell.R
import com.example.jewell.adapter.StoreRecyclerViewAdapter
import com.example.jewell.view_decorator.TopSpacingItemDecoration

class StoresRecyclerViewFragment(val supportFragmentManager: FragmentManager) : Fragment() {
    private lateinit var inflatedView: View
    private lateinit var storeAdapter: StoreRecyclerViewAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        inflatedView = inflater.inflate(R.layout.stores_fragment, container, false)
        storeAdapter = StoreRecyclerViewAdapter(context!!, supportFragmentManager)
        return inflatedView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val topSpacingItemDecoration = TopSpacingItemDecoration(30);
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerViewStores)
        recyclerView.layoutManager = LinearLayoutManager(context!!)
        recyclerView.adapter = storeAdapter
        recyclerView.addItemDecoration(topSpacingItemDecoration)
        addDataSet()
    }

    fun addDataSet() {
        val data = DataSource.createStoresDataSet()
        storeAdapter.submitList(data)
    }
}