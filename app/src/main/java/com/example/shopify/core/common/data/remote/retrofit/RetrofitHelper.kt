package com.example.shopify.core.common.data.remote.retrofit

import com.example.shopify.core.util.Constants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitHelper {
    private val retrofit: Retrofit =
        Retrofit
        .Builder()
        .baseUrl(Constants.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
            .build()
    fun getInstance (): Retrofit = retrofit
}