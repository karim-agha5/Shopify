package com.example.shopify.features.product_details.view

import androidx.recyclerview.widget.DiffUtil
import com.example.shopify.core.common.data.model.Product

class DiffUtilProductImage : DiffUtil.ItemCallback<Product>() {
    override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean {
        return oldItem === newItem
    }

    override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean {
        return oldItem == newItem
    }
}