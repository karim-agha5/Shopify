package com.example.shopify.core.common.features.draftorder.model.modification.response

import com.google.gson.annotations.SerializedName

data class ModifyDraftOrderResponse(
    @SerializedName("draft_order")
    val draftOrder: ModifyDraftOrderResponseBody
)
