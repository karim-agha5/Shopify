package com.example.shopify.features.category.repository

import com.example.shopify.core.common.data.model.CustomCollection
import kotlinx.coroutines.flow.Flow

interface ICategoryRepository {

    suspend fun getMainCategories() : Flow<List<CustomCollection>>
}