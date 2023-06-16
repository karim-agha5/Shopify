package com.example.shopify.core.common.features.currency.data.remote

import com.example.shopify.core.common.features.currency.data.RemoteCurrenciesService
import com.example.shopify.core.common.features.currency.model.response.RemoteCurrenciesResponse
import com.example.shopify.core.util.Constants

class RemoteCurrenciesSourceImpl(
    private val remoteCurrenciesService: RemoteCurrenciesService
)  : IRemoteCurrenciesSource{

    override suspend fun getRemoteCurrencies(accessToken: String): RemoteCurrenciesResponse {
        return remoteCurrenciesService.getRemoteCurrencies(accessToken)
    }

}