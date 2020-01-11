package com.example.jewell.models

import android.os.Parcel
import android.os.Parcelable


data class Product(
    var name: String,
    var price: String,
    var image: String,
    var description: String,
    var arrivalDate: String,
    var type: String,
    var millesimal: Int,
    var size:Double,
    var weight: Double,
    var incomingPrice: Double,
    var sellingPrice: Double,
    var shop: String,
    var barCode: String,
    var gramPrice: Double,
    var quantityPrice: Double,
    var selingDate: String?):Parcelable {
    constructor(): this(
        "",
        "",
        "",
        "",
        "",
        "",
        0,
        0.0,
        0.0,
        0.0,
        0.0,
        "",
        "",
        0.0,
        0.0,
        ""
    )
    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readInt(),
        parcel.readDouble(),
        parcel.readDouble(),
        parcel.readDouble(),
        parcel.readDouble(),
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readDouble(),
        parcel.readDouble(),
        parcel.readString()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeString(price)
        parcel.writeString(image)
        parcel.writeString(description)
        parcel.writeString(arrivalDate)
        parcel.writeString(type)
        parcel.writeInt(millesimal)
        parcel.writeDouble(size)
        parcel.writeDouble(weight)
        parcel.writeDouble(incomingPrice)
        parcel.writeDouble(sellingPrice)
        parcel.writeString(shop)
        parcel.writeString(barCode)
        parcel.writeDouble(gramPrice)
        parcel.writeDouble(quantityPrice)
        parcel.writeString(selingDate)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Product> {
        override fun createFromParcel(parcel: Parcel): Product {
            return Product(parcel)
        }

        override fun newArray(size: Int): Array<Product?> {
            return arrayOfNulls(size)
        }
    }
}