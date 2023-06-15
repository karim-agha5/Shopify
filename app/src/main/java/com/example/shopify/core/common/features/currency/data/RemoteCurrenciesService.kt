package com.example.shopify.core.common.features.currency.data

import com.example.shopify.core.common.features.currency.model.response.RemoteCurrenciesResponse
import retrofit2.http.GET
import retrofit2.http.Header

interface RemoteCurrenciesService {

    @GET("currencies.json")
    suspend fun getRemoteCurrencies(
        @Header("X-Shopify-Access-Token")
        accessToken: String
    ): RemoteCurrenciesResponse

}