package com.example.shopify.core.common.features.draftorder.model.modification.response

import com.google.gson.annotations.SerializedName

data class GetShoppingCartResponse(
    @SerializedName("draft_order")
    val draftOrder: GetDraftOrderResponse
)
