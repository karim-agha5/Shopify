package com.example.shopify.core.common.features.currency.data.remote

import com.example.shopify.core.common.features.currency.model.response.RemoteCurrenciesResponse
import retrofit2.http.Header

interface IRemoteCurrenciesSource {
    suspend fun getRemoteCurrencies(
        @Header("X-Shopify-Access-Token")
        accessToken: String
    ): RemoteCurrenciesResponse
}