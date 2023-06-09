package com.example.shopify.features.home.view.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.shopify.features.home.repository.RepoInterface


class HomeViewModelFactory(
    private val repo: RepoInterface
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            HomeViewModel(repo) as T
        } else {
            throw IllegalArgumentException("HomeViewModel class not found")
        }
    }
}