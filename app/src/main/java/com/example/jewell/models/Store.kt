package com.example.jewell.models

import java.io.Serializable

// TODO(anвreew) change to double binding
data class Store(
    var image: String,
    var city: String,
    var ID: Int,
    var zipCode: String,
    var name: String,
    var products: MutableList<Product>,
    var lastInvent: String?): Serializable {
    constructor(): this(
        "",
        "",
        0,
        "",
        "",
        arrayListOf<Product>(),
        "")
}