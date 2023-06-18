package com.example.shopify.core.common.features.draftorder.model.creation.response

import com.google.gson.annotations.SerializedName

data class CreateDraftOrderResponse(
    @SerializedName("draft_order")
    val draftOrder: CreateDraftOrderResponseDraftOrder
)