package com.example.turosampleapp.repository

import com.example.turosampleapp.client.YelpBusinessApi
import com.example.turosampleapp.client.YelpRetrofitClient

open class BaseRepository {

    open val yelpService = YelpRetrofitClient.getClient(YelpBusinessApi::class.java)
}