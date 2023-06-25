package com.example.shopify.features.products.network

import com.example.shopify.core.common.data.model.ProductsResponse
import com.example.shopify.features.products.model.fakeProducts

class FakeProductsDataSource : ProductsDataSource {
    override suspend fun downloadProductsByCollection(collectionId: Long): ProductsResponse {
        return ProductsResponse(fakeProducts)
    }


}