package com.example.shopify.core.common.data.model

data class Product(
    val id: Long,
    val image: ImageX,
    val images: List<ImageX>,
    val options: List<Option>,
    val product_type: String,
    val status: String,
    val tags: String,
    val title: String,
    val variants: List<Variant>,
    val vendor: String,
    var isFav : Boolean,
)