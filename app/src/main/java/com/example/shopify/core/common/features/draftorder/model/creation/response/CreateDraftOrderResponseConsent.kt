package com.example.shopify.core.common.features.draftorder.model.creation.response

import com.google.gson.annotations.SerializedName

data class CreateDraftOrderResponseConsent(
    val state: String?,
    @SerializedName("opt_in_level")
    val optInLevel: String?,
    @SerializedName("consent_updated_at")
    val consentUpdatedAt: Any?,
    @SerializedName("consent_collected_from")
    val consentCollectedFrom: String?
)