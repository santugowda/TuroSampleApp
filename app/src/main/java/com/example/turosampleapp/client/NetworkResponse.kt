package com.example.turosampleapp.client

class NetworkResponse<T> private constructor(val status: NetworkStatus, val data: T?,
                                             val message: String?) {

    enum class NetworkStatus {
        SUCCESS, ERROR, IN_PROGRESS
    }

    companion object {
        fun <T> success(data: T?): NetworkResponse<T> {
            return NetworkResponse(NetworkStatus.SUCCESS, data, null)
        }

        fun <T> failure(msg: String?, data: T?): NetworkResponse<T> {
            return NetworkResponse(NetworkStatus.ERROR, data, msg)
        }

        fun <T> inProgress(data: T?): NetworkResponse<T> {
            return NetworkResponse(NetworkStatus.IN_PROGRESS, data, null)
        }
    }
}