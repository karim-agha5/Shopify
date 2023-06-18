package com.example.shopify.core.common.features.draftorder.model.modification.request

import com.google.gson.annotations.SerializedName

data class ModifyDraftOrderRequestDraftOrder(
    val note: String?,
    val email: String,
    @SerializedName("tax_exempt")
    val isTaxExempted: Boolean,
    @SerializedName("line_items")
    val lineItems: List<ModifyDraftOrderRequestLineItem>,
    @SerializedName("applied_discount")
    val appliedDiscount: ModifyDraftOrderRequestDiscount?
    )
