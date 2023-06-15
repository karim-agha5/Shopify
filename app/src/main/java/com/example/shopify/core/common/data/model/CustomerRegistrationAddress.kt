package com.example.shopify.core.common.data.model

import com.google.gson.annotations.SerializedName

data class CustomerRegistrationAddress(
    val address1: String?,
    val city: String?,
    val province: String?,
    val phone: String?,
    val zip: String?,
    @SerializedName("last_name") val lastName: String?,
    @SerializedName("first_name") val firstName: String?,
    val country: String?
)
