package com.example.shopify.core.common.features.draftorder.model.modification.request

import com.google.gson.annotations.SerializedName

data class ModifyDraftOrderRequestBody(
    @SerializedName("draft_order")
    val draftOrder: ModifyDraftOrderRequestDraftOrder
)
