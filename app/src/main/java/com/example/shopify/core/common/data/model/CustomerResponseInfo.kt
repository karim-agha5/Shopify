package com.example.shopify.core.common.data.model

import com.google.gson.annotations.SerializedName

data class CustomerResponseInfo(
    var id: Long?,
    var email: String?,
    @SerializedName("accepts_marketing") var acceptsMarketing: Boolean?,
    @SerializedName("created_at") var createdAt: String?,
    @SerializedName("updated_at") var updatedAt: String?,
    @SerializedName("first_name") var firstName: String?,
    @SerializedName("last_name") var lastName: String?,
    @SerializedName("orders_count") var ordersCount: Int?,
    var state: String?,
    @SerializedName("total_spent") var totalSpent: String?,
    @SerializedName("last_order_id") var lastOrderId: Long?,
    var note: String?,
    @SerializedName("verified_email") var verifiedEmail: Boolean?,
    @SerializedName("multipass_identifier") var multipassIdentifier: String?,
    @SerializedName("tax_exempt") var taxExempt: Boolean?,
    var tags: String?,
    @SerializedName("last_order_name") var lastOrderName: String?,
    var currency: String?,
    var phone: String?,
    var addresses: List<CustomerResponseAddress>?,
    @SerializedName("accepts_marketing_updated_at") var acceptsMarketingUpdatedAt: String?,
    @SerializedName("marketing_opt_in_level") var marketingOptInLevel: String?,
    @SerializedName("tax_exemptions") var taxExemptions: List<String>?,
    var defaultAddress: CustomerResponseAddress?
)

