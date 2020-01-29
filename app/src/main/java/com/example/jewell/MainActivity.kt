package com.example.jewell

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
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

        FirebaseDatabase.getInstance()
        mFirebaseAuth = FirebaseAuth.getInstance()
        mAuthStateListener = FirebaseAuth.AuthStateListener{
            val user = it.currentUser
            if (user != null) {
                Toast.makeText(this, "You're now signed in. Welcome to Simple Inventorization App.", Toast.LENGTH_SHORT).show();
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

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == RC_SIGN_IN) {
            if (resultCode == Activity.RESULT_OK) { // Sign-in succeeded, set up the UI
                Toast.makeText(this, "Signed in!", Toast.LENGTH_SHORT).show()
            } else if (resultCode == Activity.RESULT_CANCELED) { // Sign in was canceled by the user, finish the activity
                Toast.makeText(this, "Sign in canceled", Toast.LENGTH_SHORT).show()
                finish()
            }
        }
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return true
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
