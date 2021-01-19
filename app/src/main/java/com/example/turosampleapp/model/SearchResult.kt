package com.example.turosampleapp.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class SearchResult(
    @SerializedName("businesses")
    @Expose
    var businesses: List<Business>? = null,

    @SerializedName("total")
    @Expose
    var total: Int? = 0,

    @SerializedName("region")
    @Expose
    var region: Region
)

