package com.example.jewell.models

import java.io.Serializable

// TODO(anвreew) change to double binding
data class Store(
    var storeCity: String,
    var storeID: Int,
    var zipCode: String,
    var storeName: String): Serializable