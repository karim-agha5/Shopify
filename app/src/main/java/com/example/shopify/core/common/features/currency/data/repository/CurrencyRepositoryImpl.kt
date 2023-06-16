package com.example.shopify.core.common.features.currency.data.repository

import com.example.shopify.core.common.features.currency.data.remote.IRemoteCurrenciesSource
import com.example.shopify.core.common.features.currency.model.response.RemoteCurrenciesResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * Main-safe repository that fetches the current available currencies on the Shopify API
 * */
class CurrencyRepositoryImpl(
    private val remoteCurrenciesSource: IRemoteCurrenciesSource
) : IRemoteCurrencyRepository{

    override suspend fun getRemoteCurrencies(accessToken: String): RemoteCurrenciesResponse {
        return withContext(Dispatchers.IO){
             remoteCurrenciesSource.getRemoteCurrencies(accessToken)
        }
    }

}