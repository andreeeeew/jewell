package com.example.jewell

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        open_bottom_navigation_bar.setOnClickListener {
            launchBottomBarActivity()
        }
    }

    private fun launchBottomBarActivity() {
        val intent = Intent(this, BottomBarActivity::class.java)
        startActivity(intent)
    }

}
