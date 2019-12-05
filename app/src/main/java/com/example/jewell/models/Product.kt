package com.example.jewell.models

import android.os.Parcel
import android.os.Parcelable
import androidx.databinding.Bindable
import androidx.databinding.Observable
import androidx.databinding.PropertyChangeRegistry
import java.time.LocalDate


class Product(): Observable, Parcelable {
    private val callbacks: PropertyChangeRegistry by lazy { PropertyChangeRegistry()}
    var name = ""
    var price = ""
    @get:Bindable
    var image: String = ""
    var description = ""
    var arrivalDate = LocalDate.now()
    var type = ""
    var millesimal = 0
    var size = 0.0
    var weight = 0.0
    var incomingPrice = 0.0
    var sellingPrice = 0.0
    var shop = ""
    var barCode = ""
    var gramPrice = 0.0
    var quantityPrice = 0.0
    var selingDate =  LocalDate.now()

    constructor(
        name: String,
        price: String,
        image: String,
        description: String,
        arrivalDate: LocalDate?,
        type: String,
        millesimal: Int,
        size: Double,
        weight: Double,
        incomingPrice: Double,
        sellingPrice: Double,
        shop: String,
        barCode: String,
        gramPrice: Double,
        quantityPrice: Double,
        selingDate: LocalDate?,
        firm: Firm
    ) : this() {
        this.name = name
        this.price = price
        this.image = image
        this.description = description
        this.arrivalDate = arrivalDate
        this.type = type
        this.millesimal = millesimal
        this.size = size
        this.weight = weight
        this.incomingPrice = incomingPrice
        this.sellingPrice = sellingPrice
        this.shop = shop
        this.barCode = barCode
        this.gramPrice = gramPrice
        this.quantityPrice = quantityPrice
        this.selingDate = selingDate
        this.firm = firm
    }

    var firm = Firm(
        article = 0,
        firmID = 0,
        firmName = ""
    )

    constructor(parcel: Parcel) : this() {
        name = parcel.readString()!!
        price = parcel.readString()!!
        image = parcel.readString()!!
        description = parcel.readString()!!
        type = parcel.readString()!!
        millesimal = parcel.readInt()
        size = parcel.readDouble()
        weight = parcel.readDouble()
        incomingPrice = parcel.readDouble()
        sellingPrice = parcel.readDouble()
        shop = parcel.readString()!!
        barCode = parcel.readString()!!
        gramPrice = parcel.readDouble()
        quantityPrice = parcel.readDouble()
    }

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
        callbacks.add(callback)
    }

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
        callbacks.add(callback)
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeString(price)
        parcel.writeString(image)
        parcel.writeString(description)
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