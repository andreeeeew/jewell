package com.example.jewell.models

import java.io.Serializable
import java.time.LocalDate

class StockTaking(): Serializable {
    lateinit var startDate: LocalDate
    var endDate: LocalDate? = null
    var stockTakingID: Int = 0
    lateinit var store: Store
    lateinit var inventorizedBarCodes: HashSet<String>

    constructor(startDate: LocalDate, endDate: LocalDate?, stockTakingID: Int, store: Store): this(){
        this.startDate = startDate
        this.endDate = endDate
        this.stockTakingID = stockTakingID
        this.store = store
        inventorizedBarCodes = HashSet()
    }
}