package com.example.jewell

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private val TAG = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        val intent = Intent(this, BottomBarActivity::class.java)
//        setContentView(R.layout.activity_main)
//        startActivity(intent)
//        Log.d(TAG, "onCreate: started.")

        setContentView(R.layout.layout_store_list_item)
    }
}
