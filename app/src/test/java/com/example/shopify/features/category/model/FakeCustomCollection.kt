package com.example.shopify.features.category.model

import com.example.shopify.core.common.data.model.CustomCollection
import com.example.shopify.core.common.data.model.Image

val image = Image(src = "https://example.com/image.jpg")
val fakeCustomCollectionList = listOf(
    CustomCollection(id = 123456789, image = image, title = "My Custom Collection"),
    CustomCollection(id = 123456789, image = image, title = "My Custom Collection"),
    CustomCollection(id = 123456789, image = image, title = "My Custom Collection"),
    CustomCollection(id = 123456789, image = image, title = "My Custom Collection")
)
