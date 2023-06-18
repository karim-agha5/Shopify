package com.example.shopify.core.common.features.draftorder.model.creation.response

import com.google.gson.annotations.SerializedName

data class CreateDraftOrderResponseAddress(
    val first_name: String?,
    val address1: String?,
    val phone: String?,
    val city: String?,
    val zip: String?,
    val province: String?,
    val country: String?,
    @SerializedName("last_name")
    val lastName: String?,
    val address2: Any?,
    val company: Any?,
    val latitude: Any?,
    val longitude: Any?,
    val name: String?,
    @SerializedName("country_code")
    val countryCode: String?,
    @SerializedName("province_code")
    val provinceCode: String?
)
