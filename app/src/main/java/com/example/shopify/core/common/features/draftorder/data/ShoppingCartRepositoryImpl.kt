package com.example.shopify.core.common.features.draftorder.data

import com.example.shopify.core.common.features.draftorder.data.remote.IShoppingCartRemoteSource
import com.example.shopify.core.common.features.draftorder.model.creation.request.CreateDraftOrderRequestBody
import com.example.shopify.core.common.features.draftorder.model.creation.response.CreateDraftOrderResponse
import com.example.shopify.core.common.features.draftorder.model.modification.request.ModifyDraftOrderRequestBody
import com.example.shopify.core.common.features.draftorder.model.modification.response.ModifyDraftOrderResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ShoppingCartRepositoryImpl(
    private val shoppingCartRemoteSourceImpl: IShoppingCartRemoteSource
) : IShoppingCartRepository {

    override suspend fun createShoppingCart(
        body: CreateDraftOrderRequestBody
    ): CreateDraftOrderResponse {
        return withContext(Dispatchers.IO){
            return@withContext shoppingCartRemoteSourceImpl.createShoppingCart(body)
        }
    }

    override suspend fun getShoppingCart(draftOrderId: String): ModifyDraftOrderResponse {
        return withContext(Dispatchers.IO){
            return@withContext shoppingCartRemoteSourceImpl.getShoppingCart(draftOrderId)
        }
    }

    override suspend fun modifyShoppingCart(
        draftOrderId: String,
        body: ModifyDraftOrderRequestBody
    ): ModifyDraftOrderResponse {
        return withContext(Dispatchers.IO){
            return@withContext shoppingCartRemoteSourceImpl.modifyShoppingCart(draftOrderId,body)
        }
    }

}