package com.example.shopify.core.common.features.draftorder.model.creation.response


data class ResponseCustomer(
    val id: Long?,
    val email: String?,
    val accepts_marketing: Boolean?,
    val created_at: String?,
    val updated_at: String?,
    val first_name: String?,
    val last_name: String?,
    val orders_count: Int?,
    val state: String?,
    val total_spent: String?,
    val last_order_id: Any?,
    val note: Any?,
    val verified_email: Boolean?,
    val multipass_identifier: Any?,
    val tax_exempt: Boolean?,
    val tags: String?,
    val last_order_name: Any?,
    val currency: String?,
    val phone: String?,
    val accepts_marketing_updated_at: String?,
    val marketing_opt_in_level: Any?,
    val tax_exemptions: List<Any?>?,
    val email_marketing_consent: Consent?,
    val sms_marketing_consent: Consent?,
    val admin_graphql_api_id: String?,
    val default_address: Address?
)