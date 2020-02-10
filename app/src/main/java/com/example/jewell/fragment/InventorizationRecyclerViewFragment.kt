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
import com.example.jewell.R
import com.example.jewell.activity.BarCodeScannerActivity
import com.example.jewell.adapter.InventorisationRecyclerViewAdapter
import com.example.jewell.models.StockTaking
import com.example.jewell.view_decorator.TopSpacingItemDecoration
import com.google.firebase.database.*


class InventorizationRecyclerViewFragment(val supportFragmentManager: FragmentManager) : Fragment() {
    private val TAG = "InventorizationRecyclerViewFragment"
    private lateinit var inflatedView: View
    private lateinit var inventorisationAdapter: InventorisationRecyclerViewAdapter
    private lateinit var intent: Intent

    companion object {
        lateinit var mFirebaseDatabase: FirebaseDatabase
        lateinit var mInventorizationsDatabaseReference: DatabaseReference
        lateinit var mChildEventListener: ChildEventListener
    }


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

        mFirebaseDatabase = FirebaseDatabase.getInstance()
        mInventorizationsDatabaseReference = mFirebaseDatabase.reference.child("inventorizations")
        mChildEventListener = object : ChildEventListener {
            override fun onCancelled(p0: DatabaseError) {
            }

            override fun onChildMoved(p0: DataSnapshot, p1: String?) {
            }

            override fun onChildChanged(p0: DataSnapshot, p1: String?) {
                Log.d(TAG, "child changed, new child is ${p0.getValue(StockTaking::class.java)}")
                val inventorization = p0.getValue(StockTaking::class.java)
                inventorisationAdapter.addInventorizedProducts(inventorization!!, p0.key!!)
            }

            override fun onChildAdded(p0: DataSnapshot, p1: String?) {
                val inventorization = p0.getValue(StockTaking::class.java)
                inventorisationAdapter.addInventorization(inventorization!!, p0.key!!)
            }

            override fun onChildRemoved(p0: DataSnapshot) {
            }
        }
        mInventorizationsDatabaseReference.addChildEventListener(mChildEventListener)

        recyclerView.layoutManager = LinearLayoutManager(context!!)
        recyclerView.adapter = inventorisationAdapter
        recyclerView.addItemDecoration(topSpacingItemDecoration)
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "We are resumed!!")
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

}