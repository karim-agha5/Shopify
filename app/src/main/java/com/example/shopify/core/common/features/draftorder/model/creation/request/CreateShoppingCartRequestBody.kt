package com.example.shopify.core.common.features.draftorder.model.creation.request

import com.google.gson.annotations.SerializedName

data class CreateShoppingCartRequestBody(
    @SerializedName("draft_order")
    val draftOrder: RequestDraftOrder
)