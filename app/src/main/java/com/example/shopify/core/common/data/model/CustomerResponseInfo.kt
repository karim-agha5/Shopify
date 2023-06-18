package com.example.shopify.core.common.data.model

import com.google.gson.annotations.SerializedName

data class CustomerResponseInfo(
    var id: Long?,
    var cartId: Long?,
    var wishListId: Long?,
    var coupon: String?,
    var email: String?,
    @SerializedName("accepts_marketing") var acceptsMarketing: Boolean? = null,
    @SerializedName("created_at") var createdAt: String? = null,
    @SerializedName("updated_at") var updatedAt: String? = null,
    @SerializedName("first_name") var firstName: String? = null,
    @SerializedName("last_name") var lastName: String? = null,
    @SerializedName("orders_count") var ordersCount: Int? = null,
    var state: String? = null,
    @SerializedName("total_spent") var totalSpent: String? = null,
    @SerializedName("last_order_id") var lastOrderId: Long? = null,
    @SerializedName("verified_email") var verifiedEmail: Boolean? = null,
    @SerializedName("multipass_identifier") var multipassIdentifier: String? = null,
    @SerializedName("last_order_name") var lastOrderName: String? = null,
    var currency: String? = null,
    var phone: String? = null,
    var addresses: List<CustomerResponseAddress>? = null,
    @SerializedName("accepts_marketing_updated_at") var acceptsMarketingUpdatedAt: String? = null,
    @SerializedName("marketing_opt_in_level") var marketingOptInLevel: String? = null,
    @SerializedName("tax_exemptions") var taxExemptions: List<String>? = null,
    var defaultAddress: CustomerResponseAddress? = null
)

