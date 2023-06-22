package com.example.shopify.features.orders.model.model_response

import android.os.Parcelable
import com.example.shopify.core.common.data.model.CheckoutBillingAddress
import com.example.shopify.core.common.data.model.CheckoutShippingLines
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class OrderResponseData(
    /*val cancel_reason: Any?,
    val cancelled_at: Any?,
    val cart_token: Any?,*/
    val confirmed: Boolean,
    val contact_email: String,
    val created_at: String,
    val currency: String,
    val current_total_price: String,
    val customer: OrderCustomer?,
    val discount_codes: List<DiscountCode>,
    val email: String,
    val financial_status: String,
    //val fulfillment_status: Any?,
    val id: Long,
    val discount_applications: List<DiscountApplication>,
    val line_items: List<LineItem>,
    val name: String,
    val order_number: Int,
    //val phone: Any?,
    val presentment_currency: String,
    val processed_at: String,
    val total_price: String,
    @SerializedName("billing_address")
    val billingAddress: CheckoutBillingAddress?,
    val shipping_lines : List<CheckoutShippingLines>
) : Parcelable