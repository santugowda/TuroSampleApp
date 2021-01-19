package com.example.turosampleapp.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Business(
    @SerializedName("id")
    @Expose
    var id: String? = null,

    @SerializedName("alias")
    @Expose
    var alias: String? = null,

    @SerializedName("name")
    @Expose
    var name: String? = null,

    @SerializedName("image_url")
    @Expose
    var imageUrl: String? = null,

    @SerializedName("is_closed")
    @Expose
    var isClosed: Boolean,

    @SerializedName("url")
    @Expose
    var url: String,

    @SerializedName("review_count")
    @Expose
    var reviewCount: Int,

    @SerializedName("categories")
    @Expose
    var categories: List<Category>,

    @SerializedName("rating")
    @Expose
    var rating: Double,

    @SerializedName("coordinates")
    @Expose
    var coordinates: Coordinates,

    @SerializedName("transactions")
    @Expose
    var transactions: List<String>? = null,

    @SerializedName("price")
    @Expose
    var price: String? = null,

    @SerializedName("location")
    @Expose
    var location: Location,

    @SerializedName("phone")
    @Expose
    var phone: String? = null,

    @SerializedName("display_phone")
    @Expose
    var displayPhone: String? = null,

    @SerializedName("distance")
    @Expose
    var distance: Double
)
