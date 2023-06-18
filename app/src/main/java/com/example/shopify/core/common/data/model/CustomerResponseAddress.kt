package com.example.shopify.core.common.data.model

import com.google.gson.annotations.SerializedName

data class CustomerResponseAddress(
    val id: Long?,
    @SerializedName("customer_id") val customerId: Long?,
    @SerializedName("first_name") val firstName: String?,
    @SerializedName("last_name") val lastName: String?,
    val company: String?,
    val address1: String?,
    val address2: String?,
    val city: String?,
    val province: String?,
    val country: String?,
    val zip: String?,
    val phone: String?,
    val name: String?,
    @SerializedName("province_code") val provinceCode: String?,
    @SerializedName("country_code") val countryCode: String?,
    @SerializedName("country_name") val countryName: String?,
    @SerializedName("default_CreateDraftOrderResponse_address") val default: Boolean?
)
