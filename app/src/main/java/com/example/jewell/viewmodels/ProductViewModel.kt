package com.example.jewell.viewmodels

import android.util.Log
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.example.jewell.models.Product

class ProductViewModel(var product: Product): BaseObservable() {
    fun showType() {
        Log.d("ProductViewModel", "type is ${product.type}")
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
}