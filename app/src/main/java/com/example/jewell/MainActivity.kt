package com.example.jewell

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private val TAG = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.product_full_view_item)
        val intent = Intent(this, BottomBarActivity::class.java)
        setContentView(R.layout.activity_main)
        startActivity(intent)
        Log.d(TAG, "onCreate: started.")
    }
}
