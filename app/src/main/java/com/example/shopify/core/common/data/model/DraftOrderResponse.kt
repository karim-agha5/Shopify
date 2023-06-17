package com.example.shopify.core.common.data.model

import com.google.gson.annotations.SerializedName

data class DraftOrderResponse(
    @SerializedName("draft_order")
    val draftOrder: DraftOrder?
)
