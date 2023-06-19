package com.example.shopify.core.common.features.draftorder.model.modification.response

import com.example.shopify.core.common.features.draftorder.model.Discount
import com.google.gson.annotations.SerializedName

data class ModifyDraftOrderResponseBody(
    @SerializedName("id") val id: Long?,
    @SerializedName("note") val note: String?,
    @SerializedName("email") val email: String?,
    @SerializedName("taxes_included") val taxesIncluded: Boolean?,
    @SerializedName("currency") val currency: String?,
    @SerializedName("invoice_sent_at") val invoiceSentAt: String?,
    @SerializedName("created_at") val createdAt: String?,
    @SerializedName("updated_at") val updatedAt: String?,
    @SerializedName("tax_exempt") val taxExempt: Boolean?,
    @SerializedName("completed_at") val completedAt: String?,
    @SerializedName("name") val name: String?,
    @SerializedName("status") val status: String?,
    @SerializedName("line_items") val lineItems: List<ModifyDraftOrderResponseLineItem>?,
    @SerializedName("shipping_CreateDraftOrderResponse_address") val shippingAddress: ModifyDraftOrderResponseAddress?,
    @SerializedName("billing_CreateDraftOrderResponse_address") val billingAddress: ModifyDraftOrderResponseAddress?,
    @SerializedName("invoice_url") val invoiceUrl: String?,
    @SerializedName("applied_discount") val appliedDiscount: Any?,
    @SerializedName("order_id") val orderId: Any?,
    @SerializedName("shipping_line") val shippingLine: Any?,
    @SerializedName("tax_lines") val taxLines: List<Any?>?,
    @SerializedName("tags") val tags: String?,
    @SerializedName("note_attributes") val noteAttributes: List<Any?>?,
    @SerializedName("total_price") val totalPrice: String?,
    @SerializedName("subtotal_price") val subtotalPrice: String?,
    @SerializedName("total_tax") val totalTax: String?,
    @SerializedName("payment_terms") val paymentTerms: Any?,
    @SerializedName("admin_graphql_api_id") val adminGraphqlApiId: String?,
    @SerializedName("customer") val customer: ModifyDraftOrderResponseCustomer?
)


data class ModifyDraftOrderResponseLineItem(
    @SerializedName("id") val id: Long?,
    @SerializedName("variant_id") val variantId: Long?,
    @SerializedName("product_id") val productId: Long?,
    @SerializedName("title") val title: String?,
    @SerializedName("variant_title") val variantTitle: String?,
    @SerializedName("sku") val sku: String?,
    @SerializedName("vendor") val vendor: String?,
    //@SerializedName("quantity") val quantity: Int?,
    @SerializedName("quantity") val requestedQuantity: Int?,
    @SerializedName("requires_shipping") val requiresShipping: Boolean?,
    @SerializedName("taxable") val taxable: Boolean?,
    @SerializedName("gift_card") val giftCard: Boolean?,
    @SerializedName("fulfillment_service") val fulfillmentService: String?,
    @SerializedName("grams") val grams: Int?,
    @SerializedName("tax_lines") val taxLines: List<Any?>?,
    @SerializedName("applied_discount") val appliedDiscount: Discount?,
    //@SerializedName("name") val name: String?,
    @SerializedName("properties") val properties: List<Any?>?,
    @SerializedName("custom") val custom: Boolean?,
    @SerializedName("price") val price: String?,
    //@SerializedName("admin_graphql_api_id") val adminGraphqlApiId: String?
)

data class ModifyDraftOrderResponseAddress(
    @SerializedName("first_name") val firstName: String?,
    @SerializedName("address1") val address1: String?,
    @SerializedName("phone") val phone: String?,
    @SerializedName("city") val city: String?,
    @SerializedName("zip") val zip: String?,
    @SerializedName("province") val province: String?,
    @SerializedName("country") val country: String?,
    @SerializedName("last_name") val lastName: String?,
    @SerializedName("address2") val address2: String?,
    @SerializedName("company") val company: String?,
    @SerializedName("latitude") val latitude: Double?,
    @SerializedName("longitude") val longitude: Double?,
    @SerializedName("name") val name: String?,
    @SerializedName("country_code") val countryCode: String?,
    @SerializedName("province_code") val provinceCode: String?
)

data class ModifyDraftOrderResponseCustomer(
    @SerializedName("id") val id: Long?,
    @SerializedName("email") val email: String?,
    @SerializedName("accepts_marketing") val acceptsMarketing: Boolean?,
    @SerializedName("created_at") val createdAt: String?,
    @SerializedName("updated_at") val updatedAt: String?,
    @SerializedName("first_name") val firstName: String?,
    @SerializedName("last_name") val lastName: String?,
    @SerializedName("orders_count") val ordersCount: Int?,
    @SerializedName("state") val state: String?,
    @SerializedName("total_spent") val totalSpent: String?,
    @SerializedName("last_order_id") val lastOrderId: Long?,
    @SerializedName("note") val note: String?,
    @SerializedName("verified_email") val verifiedEmail: Boolean?,
    @SerializedName("multipass_identifier") val multipassIdentifier: String?,
    @SerializedName("tax_exempt") val taxExempt: Boolean?,
    @SerializedName("tags") val tags: String?,
    @SerializedName("last_order_name") val lastOrderName: String?,
    @SerializedName("currency") val currency: String?,
    @SerializedName("phone") val phone: String?,
    @SerializedName("accepts_marketing_updated_at") val acceptsMarketingUpdatedAt: String?,
    @SerializedName("marketing_opt_in_level") val marketingOptInLevel: String?,
    @SerializedName("tax_exemptions") val taxExemptions: List<Any?>?,
    @SerializedName("email_marketing_CreateDraftOrderResponse_consent") val emailMarketingConsent: ModifyDraftOrderResponseConsent?,
    @SerializedName("sms_marketing_CreateDraftOrderResponse_consent") val smsMarketingConsent: ModifyDraftOrderResponseConsent?,
    @SerializedName("admin_graphql_api_id") val adminGraphqlApiId: String?,
    @SerializedName("default_CreateDraftOrderResponse_address") val defaultAddress: ModifyDraftOrderResponseAddress?
)

data class ModifyDraftOrderResponseConsent(
    @SerializedName("state") val state: String?,
    @SerializedName("opt_in_level") val optInLevel: String?,
    @SerializedName("consent_updated_at") val consentUpdatedAt: String?,
    @SerializedName("consent_collected_from") val consentCollectedFrom: String?
)
