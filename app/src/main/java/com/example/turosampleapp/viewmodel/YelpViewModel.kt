package com.example.turosampleapp.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.turosampleapp.client.NetworkResponse
import com.example.turosampleapp.model.SearchResult
import com.example.turosampleapp.repository.YelpRepository

class YelpViewModel : ViewModel() {

    val TAG = YelpViewModel::class.java.simpleName
    var yelpRepository = YelpRepository()

    private var catalogResponse : MutableLiveData<NetworkResponse<SearchResult>> = MutableLiveData()

    fun businessSearchResult(foodTypeLocation : Map<String, String>): MutableLiveData<NetworkResponse<SearchResult>> {
        catalogResponse = yelpRepository.businessSearch(foodTypeLocation)
        Log.d(TAG, "view model to get business")
        return catalogResponse
    }
}
