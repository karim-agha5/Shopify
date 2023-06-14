package com.example.shopify.core.common.data.model

data class CustomerResponseInfo(
    var id: Long?,
    var email: String?,
    var acceptsMarketing: Boolean?,
    var createdAt: String?,
    var updatedAt: String?,
    var firstName: String?,
    var lastName: String?,
    var ordersCount: Int?,
    var state: String?,
    var totalSpent: String?,
    var lastOrderId: Long?,
    var note: String?,
    var verifiedEmail: Boolean?,
    var multipassIdentifier: String?,
    var taxExempt: Boolean?,
    var tags: String?,
    var lastOrderName: String?,
    var currency: String?,
    var phone: String?,
    var addresses: List<CustomerResponseAddress>?,
    var acceptsMarketingUpdatedAt: String?,
    var marketingOptInLevel: String?,
    var taxExemptions: List<String>?,
    var adminGraphqlApiId: String?,
    var defaultAddress: CustomerResponseAddress?
)

