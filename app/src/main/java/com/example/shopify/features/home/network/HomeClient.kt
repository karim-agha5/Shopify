package com.example.shopify.features.home.network

import com.example.shopify.core.common.data.model.ProductsResponse
import com.example.shopify.core.common.data.model.SmartCollectionResponse
import com.example.shopify.core.common.data.remote.retrofit.RetrofitHelper
import com.example.shopify.core.util.Constants


class HomeClient private constructor() : HomeDataSource {

    private val homeServices: HomeServices by lazy {
        RetrofitHelper.getInstance().create(HomeServices::class.java)
    }

    override suspend fun downloadBrands(): SmartCollectionResponse {
        return homeServices.getBrands(Constants.password)
    }

    override suspend fun downloadTenProducts(limit: Int): ProductsResponse {
        return homeServices.getTenProducts(Constants.password, limit)
    }


    companion object {
        private var instance: HomeClient? = null
        fun getInstance(): HomeClient {
            return instance ?: synchronized(this) {
                val temp = HomeClient()
                instance = temp
                temp
            }
        }
    }
}