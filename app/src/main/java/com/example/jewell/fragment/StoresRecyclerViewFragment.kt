package com.example.jewell.fragment

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
import com.example.jewell.adapter.StoreRecyclerViewAdapter
import com.example.jewell.models.Store
import com.example.jewell.view_decorator.TopSpacingItemDecoration
import com.google.firebase.database.*

class StoresRecyclerViewFragment(val supportFragmentManager: FragmentManager) : Fragment() {
    private lateinit var inflatedView: View
    private lateinit var storeAdapter: StoreRecyclerViewAdapter
    private val TAG = "StoresRecyclerViewFragment"

    lateinit var mFirebaseDatabase: FirebaseDatabase
    lateinit var mStoresDatabaseReference: DatabaseReference
    lateinit var mChildEventListener: ChildEventListener

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
        val topSpacingItemDecoration = TopSpacingItemDecoration(30)
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerViewStores)

        mFirebaseDatabase = FirebaseDatabase.getInstance()
        mStoresDatabaseReference =  mFirebaseDatabase.reference.child("stores")
        mChildEventListener = object: ChildEventListener {
            override fun onCancelled(p0: DatabaseError) {
            }

            override fun onChildMoved(p0: DataSnapshot, p1: String?) {
            }

            override fun onChildChanged(p0: DataSnapshot, p1: String?) {
            }

            override fun onChildAdded(p0: DataSnapshot, p1: String?) {
                var newStore = p0.getValue(Store::class.java)
                Log.d(TAG, "new store is $newStore")
                storeAdapter.addStore(newStore!!)
            }

            override fun onChildRemoved(p0: DataSnapshot) {
            }
        }
        mStoresDatabaseReference.addChildEventListener(mChildEventListener)

        recyclerView.layoutManager = LinearLayoutManager(context!!)
        recyclerView.adapter = storeAdapter
        recyclerView.addItemDecoration(topSpacingItemDecoration)
    }
}