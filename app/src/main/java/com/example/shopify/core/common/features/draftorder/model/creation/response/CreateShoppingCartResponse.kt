package com.example.shopify.core.common.features.draftorder.model.creation.response

import com.google.gson.annotations.SerializedName

data class CreateShoppingCartResponse(
    @SerializedName("draft_order")
    val draftOrder: ResponseDraftOrder
)