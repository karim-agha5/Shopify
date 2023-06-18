package com.example.shopify.core.common.features.draftorder.model.modification.request

data class ModifyDraftOrderRequestDiscount(
    val description: String,
    val value: String,
    val title: String,
    val amount: String,
    val value_type: String = "fixed_amount"
)
