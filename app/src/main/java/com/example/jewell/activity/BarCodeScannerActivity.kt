package com.example.jewell.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import com.google.android.material.snackbar.Snackbar
import com.google.zxing.Result
import me.dm7.barcodescanner.zxing.ZXingScannerView


class BarCodeScannerActivity : Activity(), ZXingScannerView.ResultHandler {
    private var mScannerView: ZXingScannerView? = null
    private val TAG = "BarCodeScannerActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d(TAG, "OnCreate")
        super.onCreate(savedInstanceState)
        mScannerView = ZXingScannerView(this)
        setContentView(mScannerView)
    }

    override fun onResume() {
        Log.d(TAG, "OnResume")
        super.onResume()
        mScannerView!!.setResultHandler(this)
        mScannerView!!.startCamera()
    }

    override fun onPause() {
        super.onPause()
        mScannerView!!.stopCamera()
    }



    override fun handleResult(rawResult: Result?) {
        Log.v(TAG, rawResult!!.getText())
        Log.v(TAG, rawResult!!.getBarcodeFormat().toString())
        val snackbar = Snackbar
            .make(mScannerView!!.rootView, "Bar Code is ${rawResult!!.text}", Snackbar.LENGTH_LONG)
        snackbar.show()
        val returnIntent = Intent()
        returnIntent.putExtra("barcode", rawResult.text.toString())
        setResult(RESULT_OK, returnIntent)
        finish()
        mScannerView?.resumeCameraPreview(this)
    }
}