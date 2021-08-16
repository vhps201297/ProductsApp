package com.example.products.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Producto(
    @SerializedName("id")
    var id: String,
    @SerializedName("name")
    var name: String,
    @SerializedName("thumbnail_url")
    var urlImg: String,
    @SerializedName("provider")
    var provider: String,
    @SerializedName("price")
    var price: String,
    @SerializedName("delivery")
    var delivery: String,
    @SerializedName("desc")
    var description: String,
    @SerializedName("imag_url")
    var imageDetail: String
    ) : Serializable