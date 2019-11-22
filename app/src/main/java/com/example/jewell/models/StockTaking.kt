package com.example.jewell.models

import java.io.Serializable
import java.time.LocalDate

data class StockTaking(
    var startDate: LocalDate,
    var endDate: LocalDate?,
    var stockTakingID: StockTaking
): Serializable