package com.example.shopify.features.orders_details.viewmodel

import androidx.lifecycle.ViewModel
import com.example.shopify.features.orders.model.model_response.LineItem
import com.example.shopify.features.orders_details.repository.IOrdersDetailsRepository

class OrdersDetailsViewModel (private val repositoryInterface: IOrdersDetailsRepository) : ViewModel() {

    suspend fun getProductsImages(items: List<LineItem>): List<String> {
        return repositoryInterface.getImagesByProductsIds(items)
    }

}