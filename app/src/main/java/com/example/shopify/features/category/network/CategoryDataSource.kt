package com.example.shopify.features.category.network

import com.example.shopify.core.common.data.model.MainCategoriesResponse

interface CategoryDataSource {
    suspend fun downloadCategories() : MainCategoriesResponse
}