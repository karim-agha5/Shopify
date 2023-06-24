package com.example.shopify.features.search.view

import com.example.shopify.core.common.data.model.Product

interface IOnSearchResultClickListener {
    fun delegateProduct(product: Product)
}