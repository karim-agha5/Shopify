package com.example.shopify.features.orders_details.repository

import com.example.shopify.features.orders.model.model_response.LineItem

interface IOrdersDetailsRepository {

  suspend fun getImagesByProductsIds(variants : List<LineItem>) : List<String>

}