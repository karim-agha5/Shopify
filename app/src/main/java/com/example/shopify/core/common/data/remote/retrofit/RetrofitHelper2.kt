package com.example.shopify.core.common.data.remote.retrofit

import com.example.shopify.core.common.interfaces.IShopifyService
import com.example.shopify.core.util.Constants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitHelper2 {
    val retrofit = Retrofit.Builder().addConverterFactory(GsonConverterFactory.create()).baseUrl(
            Constants.BASE_URL).build()
}

object API {
    val retrofitService: IShopifyService by lazy {
        RetrofitHelper2.retrofit.create(IShopifyService::class.java)
    }
}