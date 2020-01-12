package com.example.jewell.viewmodels

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.example.jewell.models.StockTaking

class StockTakingViewModel (var stockTaking: StockTaking, var key: String): BaseObservable() {
    private val TAG = "StockTakingViewModel"

    @Bindable
    fun getNumberOfInventorizedProducts(): String {
        // TODO(andreew) make sure there are no duplicates
        return stockTaking.inventorizedBarCodes.count().toString()
    }

    @Bindable
    fun getNumberOfAllProducts(): String {
        return stockTaking.store.products.size.toString()
    }

    @Bindable
    fun getImage(): String {
        return "https://abas-erp.com/sites/default/files/styles/content-image-third/public/Stocktaking%20erp%20.png?itok=BBOB_YxO"
    }
}