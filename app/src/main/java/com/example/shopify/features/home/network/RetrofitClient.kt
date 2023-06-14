package com.example.shopify.features.home.network

import com.example.shopify.core.common.data.model.ProductsResponse
import com.example.shopify.core.common.data.model.SmartCollectionResponse
import com.example.shopify.core.common.data.remote.IRemoteDataSource
import com.example.shopify.core.common.data.remote.retrofit.RetrofitHelper
import com.example.shopify.core.util.Constants


class RetrofitClient private constructor() : IRemoteDataSource {

private val brandService : BrandService by lazy {
    RetrofitHelper.getInstance().create(BrandService::class.java)
}

   override suspend fun downloadBrands () : SmartCollectionResponse {
        return brandService.getBrands(Constants.password)
   }

    override suspend fun downloadTenProducts(limit: Int): ProductsResponse {
        return brandService.getTenProducts(Constants.password,limit)
    }

    override suspend fun downloadProductsByCollection(collectionId: Long): ProductsResponse {
        return brandService.getProductsByCollectionId(Constants.password,collectionId)
    }
    

    companion object {
        private var instance: RetrofitClient? = null
        fun getInstance(): RetrofitClient {
            return instance ?: synchronized(this) {
                val temp = RetrofitClient()
                instance = temp
                temp
            }
        }
    }
}