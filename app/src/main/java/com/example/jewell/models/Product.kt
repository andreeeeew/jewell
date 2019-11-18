package com.example.jewell.models

import java.io.Serializable

data class Product(
    var name: String,
    var price: String,
    var image: String,
    var description: String): Serializable