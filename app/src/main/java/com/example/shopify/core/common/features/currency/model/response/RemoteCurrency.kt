package com.example.shopify.core.common.features.currency.model.response

import com.google.gson.annotations.SerializedName

data class RemoteCurrency(
    val currency: String?,
    @SerializedName("rate_updated_at")
    val rateUpdatedAt: String?,
    val enabled: Boolean?
)
