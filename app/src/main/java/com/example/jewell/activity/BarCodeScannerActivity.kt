package com.example.jewell.activity

import android.app.Activity
import android.os.Bundle
import android.util.Log
import com.google.android.material.snackbar.Snackbar
import com.google.zxing.Result
import me.dm7.barcodescanner.zxing.ZXingScannerView



class BarCodeScannerActivity : Activity(), ZXingScannerView.ResultHandler {
    private var mScannerView: ZXingScannerView? = null
    private val TAG = "BarCodeScannerActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mScannerView = ZXingScannerView(this)
        setContentView(mScannerView)
    }

    override fun onResume() {
        super.onResume()
        mScannerView!!.setResultHandler(this)
        mScannerView!!.startCamera()
    }

    override fun onPause() {
        super.onPause()
        mScannerView!!.stopCamera()
    }

    override fun handleResult(rawResult: Result?) {
        // Do something with the result here
        Log.v(TAG, rawResult!!.getText()); // Prints scan results
        Log.v(TAG, rawResult!!.getBarcodeFormat().toString()); // Prints the scan format (qrcode, pdf417 etc.)
        val snackbar = Snackbar
            .make(mScannerView!!.rootView, "Bar Code is ${rawResult!!.text}", Snackbar.LENGTH_LONG)

        snackbar.show()

        // If you would like to resume scanning, call this method below:
        mScannerView?.resumeCameraPreview(this);
    }
}