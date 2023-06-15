package com.example.shopify.core.common.data.model

import com.google.gson.annotations.SerializedName

data class CustomerRegistrationInfo(
    @SerializedName("first_name") val firstName: String?,
    @SerializedName("last_name") val lastName: String? = "",
    val email: String?,
    val phone: String? = null,
    @SerializedName("verified_email") val verifiedEmail: Boolean? = true,
    val addresses: List<CustomerRegistrationAddress>? = null,
    val password: String?,
    @SerializedName("password_confirmation") val passwordConfirmation: String?,
    @SerializedName("send_email_welcome") val sendEmailWelcome: Boolean? = false
)
