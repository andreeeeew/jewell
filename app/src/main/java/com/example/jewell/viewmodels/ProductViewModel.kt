package com.example.jewell.viewmodels

import android.util.Log
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.example.jewell.models.Product

class ProductViewModel(var product: Product, var key: String): BaseObservable() {
    private val TAG = "ProductViewModel"
    fun showType() {
        Log.d(TAG, "type is ${product.type}")
    }

    @Bindable
    fun getName(): String {
        return product.name
    }

    @Bindable
    fun getPrice(): String {
        return product.price
    }

    @Bindable
    fun getType(): String {
        return product.type
    }

    fun setType(type: String) {
        product.type = type
    }

    @Bindable
    fun getShop(): String {
        return product.shop
    }

    @Bindable
    fun getImage(): String {
        return product.image
    }

    @Bindable
    fun getBarcode(): String {
        return product.barCode
    }

    @Bindable
    fun setImage(image: String) {

    }
}