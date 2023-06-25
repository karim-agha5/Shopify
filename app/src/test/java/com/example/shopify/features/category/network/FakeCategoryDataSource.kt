package com.example.shopify.features.category.network

import com.example.shopify.core.common.data.model.MainCategoriesResponse
import com.example.shopify.features.category.model.fakeCustomCollectionList

class FakeCategoryDataSource : CategoryDataSource {
    override suspend fun downloadCategories(): MainCategoriesResponse {
        return MainCategoriesResponse(fakeCustomCollectionList)
    }
}