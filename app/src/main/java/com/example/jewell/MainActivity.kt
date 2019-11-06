package com.example.jewell

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.jewell.adapter.ProductRecyclerViewAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private val TAG = "MainActivity"
    private lateinit var productAdapter: ProductRecyclerViewAdapter

    //vars
    private val mNames = ArrayList<String>()
    private val mImageUrls = ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val intent = Intent(this, BottomBarActivity::class.java)
        setContentView(R.layout.activity_main)
        startActivity(intent)
        initRecyclerView()
        addDataSet()
        Log.d(TAG, "onCreate: started.")
    }

    private fun addDataSet() {
        val data = DataSource.createDataSet()
        productAdapter.submitList(data)
    }

    private fun initRecyclerView() {
        Log.d(TAG, "initRecyclerView: init recyclerview.")
        productAdapter = ProductRecyclerViewAdapter()
        recycler_view.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = productAdapter
        }
//        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view)
//        val adapter = ProductRecyclerViewAdapter(this, mNames, mImageUrls)
//        recyclerView.adapter = adapter
//        recyclerView.layoutManager = LinearLayoutManager(this)
    }
}
