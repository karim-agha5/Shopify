package com.example.shopify.features.category.repository

import com.example.shopify.core.common.data.model.CustomCollection
import com.example.shopify.features.category.model.fakeCustomCollectionList
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeCategoryRepository : ICategoryRepository {
    override suspend fun getMainCategories(): Flow<List<CustomCollection>> {
        return flow { emit(fakeCustomCollectionList) }
    }
}