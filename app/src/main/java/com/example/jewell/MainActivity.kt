package com.example.jewell

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val intent = Intent(this, BottomBarActivity::class.java)
        setContentView(R.layout.activity_main)
        startActivity(intent)
    }
}
