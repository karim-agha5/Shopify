package com.example.shopify.core.common.mappers

import com.example.shopify.core.common.features.currency.model.response.RemoteCurrency
import com.example.shopify.features.settings.model.CurrencyUiState

class CurrencyMapper {
    fun fromRemoteToUiState(remoteCurrency: RemoteCurrency) : CurrencyUiState{
        return CurrencyUiState(remoteCurrency.currency)
    }
}