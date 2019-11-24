package com.example.jewell

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private val TAG = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val intent = Intent(this, BottomBarActivity::class.java)
        startActivity(intent)
        Log.d(TAG, "onCreate: started.")

//        setContentView(R.layout.layout_store_list_item)
    }
}
