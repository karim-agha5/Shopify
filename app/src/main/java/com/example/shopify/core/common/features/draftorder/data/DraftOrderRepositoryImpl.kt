package com.example.shopify.core.common.features.draftorder.data

import android.util.Log
import com.example.shopify.core.common.features.draftorder.data.remote.IDraftOrderRemoteSource
import com.example.shopify.core.common.features.draftorder.model.creation.request.CreateDraftOrderRequestBody
import com.example.shopify.core.common.features.draftorder.model.creation.response.CreateDraftOrderResponse
import com.example.shopify.core.common.features.draftorder.model.modification.request.ModifyDraftOrderRequestBody
import com.example.shopify.core.common.features.draftorder.model.modification.response.ModifyDraftOrderResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
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

    override suspend fun getShoppingCart(draftOrderId: String): Flow<ModifyDraftOrderResponse> {
//        Log.d(TAG, "getShoppingCart: hi")
        return flow {
            emit(draftOrderRemoteSourceImpl.getShoppingCart(draftOrderId))
        }
    }

    override suspend fun modifyShoppingCart(
        draftOrderId: String,
        body: ModifyDraftOrderRequestBody
    ): Flow<ModifyDraftOrderResponse> {
        return flow {
            emit(draftOrderRemoteSourceImpl.modifyShoppingCart(draftOrderId, body))
        }
    }
}
