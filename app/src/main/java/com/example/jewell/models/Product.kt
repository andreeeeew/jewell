package com.example.jewell.models

import java.io.Serializable
import java.time.LocalDate


// TODO(anвreew) change to double binding
data class Product(
    var name: String,
    var price: String,
    var image: String,
    var description: String,
    var arrivalDate: LocalDate,
    var type: String,
    var millesimal: Int,
    var size: Double,
    var weight: Double,
    var incomingPrice: Double,
    var sellingPrice: Double,
    var shop: String,
    var barCode: String,
    var gramPrice: Double,
    var quantityPrice: Double,
    var selingDate: LocalDate?,
    var firm: Firm): Serializable