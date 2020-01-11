package com.example.jewell.models

import java.io.Serializable

class StockTaking(): Serializable {
    lateinit var startDate: String
    var endDate: String? = null
    var stockTakingID: Int = 0
    lateinit var store: Store
    lateinit var inventorizedBarCodes: HashSet<String>

    constructor(startDate: String, endDate: String?, stockTakingID: Int, store: Store): this(){
        this.startDate = startDate
        this.endDate = endDate
        this.stockTakingID = stockTakingID
        this.store = store
        inventorizedBarCodes = HashSet()
    }
}