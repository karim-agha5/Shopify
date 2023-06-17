package com.example.shopify.core.common.features.draftorder.data

import com.example.shopify.core.common.features.draftorder.data.remote.IShoppingCartRemoteSource
import com.example.shopify.core.common.features.draftorder.model.creation.request.CreateShoppingCartRequestBody
import com.example.shopify.core.common.features.draftorder.model.creation.response.CreateShoppingCartResponse
import com.example.shopify.core.common.features.draftorder.model.modification.request.ModifyShoppingCartRequestBody
import com.example.shopify.core.common.features.draftorder.model.modification.response.GetShoppingCartResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ShoppingCartRepositoryImpl(
    private val shoppingCartRemoteSourceImpl: IShoppingCartRemoteSource
) : IShoppingCartRepository {

    override suspend fun createShoppingCart(
        body: CreateShoppingCartRequestBody
    ): CreateShoppingCartResponse {
        return withContext(Dispatchers.IO){
            return@withContext shoppingCartRemoteSourceImpl.createShoppingCart(body)
        }
    }

    override suspend fun getShoppingCart(draftOrderId: String): GetShoppingCartResponse {
        return withContext(Dispatchers.IO){
            return@withContext shoppingCartRemoteSourceImpl.getShoppingCart(draftOrderId)
        }
    }

    override suspend fun modifyShoppingCart(
        draftOrderId: String,
        body: ModifyShoppingCartRequestBody
    ): GetShoppingCartResponse {
        return withContext(Dispatchers.IO){
            return@withContext shoppingCartRemoteSourceImpl.modifyShoppingCart(draftOrderId,body)
        }
    }

}