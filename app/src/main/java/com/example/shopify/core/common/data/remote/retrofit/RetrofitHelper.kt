package com.example.shopify.core.common.data.remote.retrofit

import com.example.shopify.core.util.Constants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitHelper {
    fun getInstance (): Retrofit {
        return Retrofit.Builder().addConverterFactory(GsonConverterFactory.create()).baseUrl(
            Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).build()
    }
}