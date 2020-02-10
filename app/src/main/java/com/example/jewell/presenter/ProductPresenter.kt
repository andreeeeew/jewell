package com.example.jewell.presenter

import android.content.Intent
import android.view.View
import com.example.jewell.ProductFullViewActivity
import com.example.jewell.models.Product

class ProductPresenter {
    fun onProductClicked(view: View, product: Product) {
        var intent = Intent(view.context, ProductFullViewActivity::class.java)
        intent.putExtra("product", product)
        view.context.startActivity(intent)
    }
}