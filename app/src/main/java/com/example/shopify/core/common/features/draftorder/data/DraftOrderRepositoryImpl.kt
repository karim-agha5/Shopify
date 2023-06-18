package com.example.shopify.core.common.features.draftorder.data

import android.util.Log
import com.example.shopify.core.common.features.draftorder.data.remote.IDraftOrderRemoteSource
import com.example.shopify.core.common.features.draftorder.model.creation.request.CreateDraftOrderRequestBody
import com.example.shopify.core.common.features.draftorder.model.creation.response.CreateDraftOrderResponse
import com.example.shopify.core.common.features.draftorder.model.modification.request.ModifyDraftOrderRequestBody
import com.example.shopify.core.common.features.draftorder.model.modification.response.ModifyDraftOrderResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class DraftOrderRepositoryImpl(
    private val draftOrderRemoteSourceImpl: IDraftOrderRemoteSource
) : IDraftOrderRepository {
    private val TAG = "DraftOrderRepositoryImpl"

    override suspend fun createShoppingCart(
        body: CreateDraftOrderRequestBody
    ): CreateDraftOrderResponse {
        Log.d(TAG, "createShoppingCart: +++++hi")
            return draftOrderRemoteSourceImpl.createShoppingCart(body)

    }

    override suspend fun getShoppingCart(draftOrderId: String): ModifyDraftOrderResponse {
        return withContext(Dispatchers.IO){
            return@withContext draftOrderRemoteSourceImpl.getShoppingCart(draftOrderId)
        }
    }

    override suspend fun modifyShoppingCart(
        draftOrderId: String,
        body: ModifyDraftOrderRequestBody
    ): ModifyDraftOrderResponse {
        return withContext(Dispatchers.IO){
            return@withContext draftOrderRemoteSourceImpl.modifyShoppingCart(draftOrderId,body)
        }
    }

}