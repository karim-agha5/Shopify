package com.example.shopify.features.category.network

import com.example.shopify.core.common.data.model.MainCategoriesResponse
import com.example.shopify.core.common.data.remote.retrofit.RetrofitHelper
import com.example.shopify.core.util.Constants

class CategoryClient private constructor() : CategoryDataSource {

    private val categoryServices: CategoryServices by lazy {
        RetrofitHelper.getInstance().create(CategoryServices::class.java)
    }


    override suspend fun downloadCategories(): MainCategoriesResponse {
        return categoryServices.getMainCategories(Constants.password)
    }


    companion object {
        private var instance: CategoryClient? = null
        fun getInstance(): CategoryClient {
            return instance ?: synchronized(this) {
                val temp = CategoryClient()
                instance = temp
                temp
            }
        }
    }
}