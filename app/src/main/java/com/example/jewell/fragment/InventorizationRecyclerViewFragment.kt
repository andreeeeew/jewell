package com.example.jewell.fragment

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.jewell.DataSource
import com.example.jewell.R
import com.example.jewell.activity.BarCodeScannerActivity
import com.example.jewell.adapter.InventorisationRecyclerViewAdapter
import com.example.jewell.view_decorator.TopSpacingItemDecoration


class InventorizationRecyclerViewFragment(val supportFragmentManager: FragmentManager) : Fragment() {
    private lateinit var inflatedView: View
    private lateinit var inventorisationAdapter: InventorisationRecyclerViewAdapter
    private lateinit var intent: Intent


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        inflatedView = inflater.inflate(R.layout.inventorization_fragment, container, false)
        inventorisationAdapter = InventorisationRecyclerViewAdapter(context!!, supportFragmentManager)
        intent = Intent(context!!, BarCodeScannerActivity::class.java)
        return inflatedView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val topSpacingItemDecoration = TopSpacingItemDecoration(30)
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerViewInventorizations)
        recyclerView.layoutManager = LinearLayoutManager(context!!)
        recyclerView.adapter = inventorisationAdapter
        recyclerView.addItemDecoration(topSpacingItemDecoration)
        addDataSet()

//        val fab = view.findViewById<FloatingActionButton>(R.id.inventorization_fab)
//        recyclerView.addOnScrollListener(object: RecyclerView.OnScrollListener() {
//            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
//                super.onScrolled(recyclerView, dx, dy)
//                if (dy > 0 && fab.visibility == View.VISIBLE) {
//                    fab.hide()
//                } else if (dy < 0 && fab.visibility != View.VISIBLE) {
//                    fab.show()
//                }
//            }
//        })
//
//        fab.setOnClickListener {
//            Log.d("DSA", "FAB was clicked")
//            startActivityForResult(intent, 0)
//        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode === 0) {
            if (resultCode === RESULT_OK) { // contents contains whatever was encoded
                val contents: String = intent.getStringExtra("SCAN_RESULT")
                Log.d("DSA", "contents is $contents")
                // Format contains the type of code i.e. UPC, EAN, QRCode etc...
                val format: String = intent.getStringExtra("SCAN_RESULT_FORMAT")
                Log.d("DSA", "format is $format")
            }
        }
    }

    fun addDataSet() {
        val data = DataSource.createInventorizationsDataSet()
        inventorisationAdapter.submitList(data)
    }
}