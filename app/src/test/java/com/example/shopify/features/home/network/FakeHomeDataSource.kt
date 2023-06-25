package com.example.shopify.features.home.network

import com.example.shopify.core.common.data.model.ProductsResponse
import com.example.shopify.core.common.data.model.SmartCollectionResponse
import com.example.shopify.features.home.model.fakeSmartCollectionList
import com.example.shopify.features.products.model.fakeProducts

class FakeHomeDataSource : HomeDataSource {
    override suspend fun downloadBrands(): SmartCollectionResponse {
        return SmartCollectionResponse(fakeSmartCollectionList)
    }

    override suspend fun downloadTenProducts(limit: Int): ProductsResponse {
        return ProductsResponse(fakeProducts)
    }

    override suspend fun getAllProducts(): ProductsResponse {
        return ProductsResponse(fakeProducts)
    }
}