package com.example.jewell.models

import androidx.databinding.Bindable
import androidx.databinding.BindingAdapter
import androidx.databinding.Observable
import androidx.databinding.PropertyChangeRegistry
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.jewell.R
import de.hdodenhof.circleimageview.CircleImageView
import java.io.Serializable
import java.time.LocalDate


// TODO(anвreew) change to double binding
class Product(): Serializable, Observable {
    private val callbacks: PropertyChangeRegistry by lazy { PropertyChangeRegistry()}
    companion object DataBindingAdapter{
        @BindingAdapter("bind:product_image_url")
        @JvmStatic
        fun loadImage(view: CircleImageView, url: String) {
            val requestOptions = RequestOptions()
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background)
            Glide.with(view.context)
                .applyDefaultRequestOptions(requestOptions)
                .load(url)
                .into(view)
        }
    }
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

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
        callbacks.add(callback)
    }

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
        callbacks.add(callback)
    }
}