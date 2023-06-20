package com.example.shopify.features.orders.model.model_response

import android.os.Parcel
import android.os.Parcelable

data class OrderResponseData(
    val cancel_reason: Any?,
    val cancelled_at: Any?,
    val cart_token: Any?,
    val confirmed: Boolean,
    val contact_email: String,
    val created_at: String, //needed for timestamp
    val currency: String,
    val current_total_price: String,
    val customer: OrderCustomer?,
    val discount_codes: List<DiscountCode>,  //maybe needed at order details screen
    val email: String,
    val financial_status: String,
    val fulfillment_status: Any?,
    val id: Long,
    val discount_applications: List<DiscountApplication>,
    val line_items: List<LineItem>, //items ordered -> needed
    val name: String,
    val order_number: Int,  //needed
    val phone: Any?,
    val presentment_currency: String,
    val processed_at: String,
    val total_price: String, //needed
) : Parcelable {
    constructor(parcel: Parcel) : this(
        cancel_reason = parcel.readValue(Any::class.java.classLoader),
        cancelled_at = parcel.readValue(Any::class.java.classLoader),
        cart_token = parcel.readValue(Any::class.java.classLoader),
        confirmed = parcel.readValue(Boolean::class.java.classLoader) as Boolean,
        contact_email = parcel.readString() ?: "",
        created_at = parcel.readString() ?: "",
        currency = parcel.readString() ?: "",
        current_total_price = parcel.readString() ?: "",
        customer = parcel.readParcelable(OrderCustomer::class.java.classLoader) ,
        discount_applications = parcel.createTypedArrayList(DiscountApplication.CREATOR) ?: listOf(),
        discount_codes = parcel.createTypedArrayList(DiscountCode.CREATOR) ?: listOf(),
        email = parcel.readString() ?: "",
        financial_status = parcel.readString() ?: "",
        fulfillment_status = parcel.readValue(Any::class.java.classLoader),
        id = parcel.readLong(),
        line_items = parcel.createTypedArrayList(LineItem.CREATOR) ?: listOf(),
        name = parcel.readString() ?: "",
        order_number = parcel.readInt(),
        phone = parcel.readValue(Any::class.java.classLoader),
        presentment_currency = parcel.readString() ?: "",
        processed_at = parcel.readString() ?: "",
        total_price = parcel.readString() ?: ""
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeByte(if (confirmed) 1 else 0)
        parcel.writeString(contact_email)
        parcel.writeString(created_at)
        parcel.writeString(currency)
        parcel.writeString(current_total_price)
        parcel.writeString(email)
        parcel.writeString(financial_status)
        parcel.writeLong(id)
        parcel.writeString(name)
        parcel.writeInt(order_number)
        parcel.writeString(presentment_currency)
        parcel.writeString(processed_at)
        parcel.writeString(total_price)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<OrderResponseData> {
        override fun createFromParcel(parcel: Parcel): OrderResponseData {
            return OrderResponseData(parcel)
        }

        override fun newArray(size: Int): Array<OrderResponseData?> {
            return arrayOfNulls(size)
        }
    }
}