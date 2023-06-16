package com.example.shopify.features.category.repository

import com.example.shopify.core.common.data.model.CustomCollection
import com.example.shopify.features.category.network.CategoryDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class CategoryRepository  private constructor(private val remoteSource: CategoryDataSource) : ICategoryRepository {

    companion object {
        private var instance: CategoryRepository? = null
        fun getInstance(remoteSource: CategoryDataSource): CategoryRepository {
            return instance ?: synchronized(this) {
                val temp = CategoryRepository(remoteSource)
                instance = temp
                temp
            }
        }
    }

    override suspend fun getMainCategories(): Flow<List<CustomCollection>> {
        return flow { emit(remoteSource.downloadCategories().custom_collections) }
    }

}