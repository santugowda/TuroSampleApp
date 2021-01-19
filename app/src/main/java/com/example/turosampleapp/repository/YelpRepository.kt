package com.example.turosampleapp.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.turosampleapp.client.NetworkResponse
import com.example.turosampleapp.client.YelpBusinessApi
import com.example.turosampleapp.model.SearchResult
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class YelpRepository : BaseRepository() {

    private val TAG = YelpRepository::class.java.simpleName
    private val searchResultResponse = MutableLiveData<NetworkResponse<SearchResult>>()

    fun businessSearch(foodTypeLocation : Map<String, String>) : MutableLiveData<NetworkResponse<SearchResult>> {
        yelpService.getBusinessSearch(foodTypeLocation).enqueue(object : Callback<SearchResult> {
            override fun onResponse(call: Call<SearchResult>, response: Response<SearchResult>) {
                if(response.isSuccessful){
                    Log.d(TAG, "Retrofit call successful with response " + response.body().toString())
                    searchResultResponse.postValue(NetworkResponse.success(response.body()))
                }
            }

            override fun onFailure(call: Call<SearchResult>, t: Throwable) {
                Log.d(TAG, "Retrofit call failure with error " + t.localizedMessage)
                searchResultResponse.postValue(NetworkResponse.failure(t.localizedMessage, null))
            }
        })
        return searchResultResponse
    }


}