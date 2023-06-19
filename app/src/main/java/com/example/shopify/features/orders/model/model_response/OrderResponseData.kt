package com.example.shopify.features.orders.model.model_response

data class OrderResponseData(
    val cancel_reason: Any,
    val cancelled_at: Any,
    val cart_token: Any,
    val confirmed: Boolean,
    val contact_email: String,
    val created_at: String, //needed for timestamp
    val currency: String,
    val current_total_price: String,
    val orderCustomer: OrderCustomer,
    val discount_codes: List<DiscountCode>,  //maybe needed at order details screen
    val email: String,
    val financial_status: String,
    val fulfillment_status: Any,
    val fulfillments: List<Any>,
    val id: Long,
    val line_items: List<LineItem>, //items ordered -> needed
    val name: String,
    val order_number: Int,  //needed
    val phone: Any,
    val presentment_currency: String,
    val processed_at: String,
    val total_price: String, //needed
)

//data class OrderCardDat(val order_number: Int,val order_id : Long, val total_price: String)