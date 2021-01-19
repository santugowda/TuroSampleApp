package com.example.turosampleapp.client

import com.example.turosampleapp.utils.YelpConstants.AUTH_TOKEN
import com.example.turosampleapp.utils.YelpConstants.YELP_BASE_API
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object YelpRetrofitClient {
    private val TAG = YelpRetrofitClient::class.java.simpleName

    @JvmStatic
    fun createRetrofitInstance(): Retrofit {
        val okHttpClient = OkHttpClient.Builder().readTimeout(20, TimeUnit.SECONDS)
            .connectTimeout(20, TimeUnit.SECONDS)
            .addNetworkInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .addInterceptor(object  : Interceptor {
                override fun intercept(chain: Interceptor.Chain): Response {
                    val newRequest: Request = chain.request().newBuilder()
                        .addHeader("Authorization", "Bearer $AUTH_TOKEN")
                        .build()
                    return chain.proceed(newRequest)
                }
            })
            .build()

        return Retrofit.Builder()
            .baseUrl(YELP_BASE_API)
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
    }

    @JvmStatic
    fun <S> getClient(serviceClass: Class<S>?): S {
        return createRetrofitInstance().create(serviceClass)
    }
}