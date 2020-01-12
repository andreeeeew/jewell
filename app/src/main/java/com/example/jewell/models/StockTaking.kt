package com.example.jewell.models

import java.io.Serializable

data class StockTaking(
    var startDate: String,
    var endDate: String?,
    var stockTakingID: Int,
    var store: Store,
    var inventorizedBarCodes: MutableList<String>): Serializable {
    constructor(): this(
        "",
        "",
        0,
        Store(),
        ArrayList<String>()
    )
}