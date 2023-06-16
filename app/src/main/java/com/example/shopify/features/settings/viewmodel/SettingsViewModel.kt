package com.example.shopify.features.settings.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.shopify.core.common.features.currency.data.repository.IRemoteCurrencyRepository
import com.example.shopify.core.common.features.currency.model.response.RemoteCurrency
import com.example.shopify.core.common.mappers.CurrencyMapper
import com.example.shopify.core.util.Constants
import com.example.shopify.features.settings.model.CurrencyUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class SettingsViewModel(
    private val currencyRepository: IRemoteCurrencyRepository,
    private val currencyMapper: CurrencyMapper
) : ViewModel() {

    private val _currenciesUiState = MutableStateFlow(listOf(CurrencyUiState("N/A")))
    var currenciesUiState = _currenciesUiState.asStateFlow()

    suspend fun getRemoteCurrencies() {
        val remoteCurrenciesArray = currencyRepository.getRemoteCurrencies(Constants.PASSWORD).currencies
        _currenciesUiState.value = mapRemoteCurrenciesToUiState(remoteCurrenciesArray)
    }

    private fun mapRemoteCurrenciesToUiState(
        remoteCurrenciesArray: List<RemoteCurrency>?
    ): List<CurrencyUiState>{

        val currenciesUiStateList = mutableListOf(CurrencyUiState("N/A"))
        if (remoteCurrenciesArray != null) {
            currenciesUiStateList.removeAt(0)
            for(remoteCurrency in remoteCurrenciesArray){
                currenciesUiStateList.add(currencyMapper.fromRemoteToUiState(remoteCurrency))
            }
        }
         return currenciesUiStateList.toList()
    }
}