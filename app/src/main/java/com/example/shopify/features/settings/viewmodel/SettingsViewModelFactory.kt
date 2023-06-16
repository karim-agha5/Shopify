package com.example.shopify.features.settings.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.shopify.core.common.features.currency.data.repository.IRemoteCurrencyRepository
import com.example.shopify.core.common.mappers.CurrencyMapper

class SettingsViewModelFactory(
    private val currencyRepository: IRemoteCurrencyRepository,
    private val currencyMapper: CurrencyMapper
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return SettingsViewModel(currencyRepository,currencyMapper) as T
    }
}