package com.example.jewell

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LifecycleOwner
import com.firebase.ui.auth.AuthUI
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class MainActivity : AppCompatActivity() {
    lateinit var mFirebaseAuth: FirebaseAuth
    lateinit var mAuthStateListener: FirebaseAuth.AuthStateListener
    private val TAG = "MainActivity"
    private var mUsername = ""
    val RC_SIGN_IN = 1827
    companion object {
        lateinit var lifecycleOwner: LifecycleOwner
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        FirebaseDatabase.getInstance()
        mFirebaseAuth = FirebaseAuth.getInstance()
        mAuthStateListener = FirebaseAuth.AuthStateListener{
            val user = it.currentUser
            if (user != null) {
                Toast.makeText(this, "You're now signed in. Welcome to FriendlyChat.", Toast.LENGTH_SHORT).show();
            } else{
                val providers = arrayListOf(
                    AuthUI.IdpConfig.EmailBuilder().build(),
                    AuthUI.IdpConfig.GoogleBuilder().build())

                startActivityForResult(
                    AuthUI.getInstance()
                        .createSignInIntentBuilder()
                        .setAvailableProviders(providers)
                        .build(),
                    RC_SIGN_IN)
            }
        }
        mFirebaseAuth.addAuthStateListener(mAuthStateListener)
        val intent = Intent(this, BottomBarActivity::class.java)
        lifecycleOwner = this
        startActivity(intent)
    }

    override fun onResume() {
        super.onResume()
        mFirebaseAuth.addAuthStateListener { mAuthStateListener }
    }
    override fun onPause() {
        super.onPause()
        if (mAuthStateListener != null) {
            mFirebaseAuth.removeAuthStateListener(mAuthStateListener)
        }
    }
}
