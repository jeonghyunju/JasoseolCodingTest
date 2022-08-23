package com.example.jasoseolcodingtest.model

import android.util.Log
import retrofit2.Response

abstract class BaseDataSource {
    protected suspend fun <T> getResult(call: suspend () -> Response<T>): Resource<T?> {
        try {
            val response = call()
            if(response.isSuccessful) {
                val body = response.body()
                if(body != null || response.code() == 200) {
                    Log.d("Success!", "$body")
                    return Resource.success(body)
                }
            }
            return error("${response.code()}")
        }catch (e: Exception) {
            return error(e.message ?: e.toString())
        }
    }

    private fun <T> error(message: String): Resource<T> {
        Log.e("Network Error", message)
        return Resource.error("Network call has failed for a following reason: $message")
    }
}