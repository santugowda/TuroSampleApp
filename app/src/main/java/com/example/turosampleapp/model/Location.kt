package com.example.turosampleapp.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.util.*

data class Location(
    @SerializedName("address1")
    @Expose
    var address1: String,

    @SerializedName("address2")
    @Expose
    var address2: String,

    @SerializedName("address3")
    @Expose
    var address3: String,

    @SerializedName("city")
    @Expose
    var city: String,

    @SerializedName("zip_code")
    @Expose
    var zipCode: String,

    @SerializedName("country")
    @Expose
    var country: String,

    @SerializedName("state")
    @Expose
    var state: String,

    @SerializedName("display_address")
    @Expose
    var displayAddress: List<String>
)

data class Coordinates(

    @SerializedName("latitude")
    @Expose
    var latitude: Double,

    @SerializedName("longitude")
    @Expose
    var longitude: Double
)

data class Center(

    @SerializedName("latitude")
    @Expose
    var latitude: Double,

    @SerializedName("longitude")
    @Expose
    var longitude: Double
)

data class Region(
    @SerializedName("latitude")
    @Expose
    var center: Center
)

data class Category(
    @SerializedName("alias")
    @Expose
    var alias: String? = null,

    @SerializedName("title")
    @Expose
    var title: String? = null
)