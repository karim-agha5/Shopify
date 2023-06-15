package com.example.shopify.core.common.features.currency.data.repository

import com.example.shopify.core.common.features.currency.model.response.RemoteCurrenciesResponse
import retrofit2.http.Header

/**
 * Defines a contract for a repository to fetch the currencies remotely only
 * If you want to fetch from other sources along with the API extend this interface
 * */
interface IRemoteCurrencyRepository {
    suspend fun getRemoteCurrencies(
        @Header("X-Shopify-Access-Token")
        accessToken: String
    ): RemoteCurrenciesResponse
}