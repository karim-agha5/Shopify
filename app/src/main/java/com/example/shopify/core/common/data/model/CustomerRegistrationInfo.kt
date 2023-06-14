package com.example.shopify.core.common.data.model

data class CustomerRegistrationInfo(
    val firstName: String?,
    val lastName: String?,
    val email: String?,
    val phone: String?,
    val verifiedEmail: Boolean?,
    val addresses: List<CustomerRegistrationAddress>?,
    val password: String?,
    val passwordConfirmation: String?,
    val sendEmailWelcome: Boolean?
)
