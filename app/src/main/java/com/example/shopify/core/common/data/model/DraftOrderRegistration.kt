package com.example.shopify.core.common.data.model

import com.google.gson.annotations.SerializedName

data class DraftOrderRegistration(
    @SerializedName("draft_order") val draftOrder: DraftOrderBody
)