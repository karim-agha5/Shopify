package com.example.shopify.features.products.model

import com.example.shopify.core.common.data.model.ImageX
import com.example.shopify.core.common.data.model.Option
import com.example.shopify.core.common.data.model.Product
import com.example.shopify.core.common.data.model.Variant

val fakeProducts = listOf(
    Product(
        id = 123456L,
        selectedVariantIndex = 0,
        image = ImageX(
            height = 800,
            id = 789,
            position = 1,
            product_id = 123456L,
            src = "https://example.com/image.jpg",
            updated_at = "2023-06-24",
            variant_ids = listOf(1, 2, 3),
            width = 600
        ),
        images = listOf(
            ImageX(
                height = 800,
                id = 789,
                position = 1,
                product_id = 123456L,
                src = "https://example.com/image1.jpg",
                updated_at = "2023-06-24",
                variant_ids = listOf(1, 2, 3),
                width = 600
            ),
            ImageX(
                height = 800,
                id = 790,
                position = 2,
                product_id = 123456L,
                src = "https://example.com/image2.jpg",
                updated_at = "2023-06-24",
                variant_ids = listOf(4, 5, 6),
                width = 600
            )
        ),
        options = listOf(
            Option(
                id = 1,
                name = "Color",
                position = 1,
                product_id = 123456L,
                values = listOf("Red", "Blue", "Green")
            ),
            Option(
                id = 2,
                name = "Size",
                position = 2,
                product_id = 123456L,
                values = listOf("Small", "Medium", "Large")
            )
        ),
        product_type = "Clothing",
        status = "Active",
        tags = "Tag1,Tag2,Tag3",
        title = "Sample Product",
        variants = listOf(
            Variant(
                fulfillment_service = "Manual",
                grams = 200,
                id = 1,
                image_id = 789,
                inventory_item_id = 123,
                inventory_management = "Shopify",
                inventory_policy = "Deny",
                inventory_quantity = 10,
                old_inventory_quantity = 5,
                option1 = "Red",
                option2 = "Small",
                option3 = null,
                position = 1,
                price = 19.99,
                product_id = 123456L,
                requires_shipping = true,
                sku = "ABC123",
                taxable = true,
                title = "Red - Small",
                updated_at = "2023-06-24",
                weight = 0.2,
                weight_unit = "kg"
            ),
            Variant(
                fulfillment_service = "Manual",
                grams = 250,
                id = 2,
                image_id = 789,
                inventory_item_id = 124,
                inventory_management = "Shopify",
                inventory_policy = "Deny",
                inventory_quantity = 5,
                old_inventory_quantity = 2,
                option1 = "Blue",
                option2 = "Medium",
                option3 = null,
                position = 2,
                price = 24.99,
                product_id = 123456L,
                requires_shipping = true,
                sku = "DEF456",
                taxable = true,
                title = "Blue - Medium",
                updated_at = "2023-06-24",
                weight = 0.25,
                weight_unit = "kg"
            )
        ),
        vendor = "Example Vendor",
        isFav = false,
        rating = "4.5"
    ), Product(
        id = 123456L,
        selectedVariantIndex = 0,
        image = ImageX(
            height = 800,
            id = 789,
            position = 1,
            product_id = 123456L,
            src = "https://example.com/image.jpg",
            updated_at = "2023-06-24",
            variant_ids = listOf(1, 2, 3),
            width = 600
        ),
        images = listOf(
            ImageX(
                height = 800,
                id = 789,
                position = 1,
                product_id = 123456L,
                src = "https://example.com/image1.jpg",
                updated_at = "2023-06-24",
                variant_ids = listOf(1, 2, 3),
                width = 600
            ),
            ImageX(
                height = 800,
                id = 790,
                position = 2,
                product_id = 123456L,
                src = "https://example.com/image2.jpg",
                updated_at = "2023-06-24",
                variant_ids = listOf(4, 5, 6),
                width = 600
            )
        ),
        options = listOf(
            Option(
                id = 1,
                name = "Color",
                position = 1,
                product_id = 123456L,
                values = listOf("Red", "Blue", "Green")
            ),
            Option(
                id = 2,
                name = "Size",
                position = 2,
                product_id = 123456L,
                values = listOf("Small", "Medium", "Large")
            )
        ),
        product_type = "Clothing",
        status = "Active",
        tags = "Tag1,Tag2,Tag3",
        title = "Sample Product",
        variants = listOf(
            Variant(
                fulfillment_service = "Manual",
                grams = 200,
                id = 1,
                image_id = 789,
                inventory_item_id = 123,
                inventory_management = "Shopify",
                inventory_policy = "Deny",
                inventory_quantity = 10,
                old_inventory_quantity = 5,
                option1 = "Red",
                option2 = "Small",
                option3 = null,
                position = 1,
                price = 19.99,
                product_id = 123456L,
                requires_shipping = true,
                sku = "ABC123",
                taxable = true,
                title = "Red - Small",
                updated_at = "2023-06-24",
                weight = 0.2,
                weight_unit = "kg"
            ),
            Variant(
                fulfillment_service = "Manual",
                grams = 250,
                id = 2,
                image_id = 789,
                inventory_item_id = 124,
                inventory_management = "Shopify",
                inventory_policy = "Deny",
                inventory_quantity = 5,
                old_inventory_quantity = 2,
                option1 = "Blue",
                option2 = "Medium",
                option3 = null,
                position = 2,
                price = 24.99,
                product_id = 123456L,
                requires_shipping = true,
                sku = "DEF456",
                taxable = true,
                title = "Blue - Medium",
                updated_at = "2023-06-24",
                weight = 0.25,
                weight_unit = "kg"
            )
        ),
        vendor = "Example Vendor",
        isFav = false,
        rating = "4.5"
    ), Product(
        id = 123456L,
        selectedVariantIndex = 0,
        image = ImageX(
            height = 800,
            id = 789,
            position = 1,
            product_id = 123456L,
            src = "https://example.com/image.jpg",
            updated_at = "2023-06-24",
            variant_ids = listOf(1, 2, 3),
            width = 600
        ),
        images = listOf(
            ImageX(
                height = 800,
                id = 789,
                position = 1,
                product_id = 123456L,
                src = "https://example.com/image1.jpg",
                updated_at = "2023-06-24",
                variant_ids = listOf(1, 2, 3),
                width = 600
            ),
            ImageX(
                height = 800,
                id = 790,
                position = 2,
                product_id = 123456L,
                src = "https://example.com/image2.jpg",
                updated_at = "2023-06-24",
                variant_ids = listOf(4, 5, 6),
                width = 600
            )
        ),
        options = listOf(
            Option(
                id = 1,
                name = "Color",
                position = 1,
                product_id = 123456L,
                values = listOf("Red", "Blue", "Green")
            ),
            Option(
                id = 2,
                name = "Size",
                position = 2,
                product_id = 123456L,
                values = listOf("Small", "Medium", "Large")
            )
        ),
        product_type = "Clothing",
        status = "Active",
        tags = "Tag1,Tag2,Tag3",
        title = "Sample Product",
        variants = listOf(
            Variant(
                fulfillment_service = "Manual",
                grams = 200,
                id = 1,
                image_id = 789,
                inventory_item_id = 123,
                inventory_management = "Shopify",
                inventory_policy = "Deny",
                inventory_quantity = 10,
                old_inventory_quantity = 5,
                option1 = "Red",
                option2 = "Small",
                option3 = null,
                position = 1,
                price = 19.99,
                product_id = 123456L,
                requires_shipping = true,
                sku = "ABC123",
                taxable = true,
                title = "Red - Small",
                updated_at = "2023-06-24",
                weight = 0.2,
                weight_unit = "kg"
            ),
            Variant(
                fulfillment_service = "Manual",
                grams = 250,
                id = 2,
                image_id = 789,
                inventory_item_id = 124,
                inventory_management = "Shopify",
                inventory_policy = "Deny",
                inventory_quantity = 5,
                old_inventory_quantity = 2,
                option1 = "Blue",
                option2 = "Medium",
                option3 = null,
                position = 2,
                price = 24.99,
                product_id = 123456L,
                requires_shipping = true,
                sku = "DEF456",
                taxable = true,
                title = "Blue - Medium",
                updated_at = "2023-06-24",
                weight = 0.25,
                weight_unit = "kg"
            )
        ),
        vendor = "Example Vendor",
        isFav = false,
        rating = "4.5"
    ),Product(
        id = 123456L,
        selectedVariantIndex = 0,
        image = ImageX(
            height = 800,
            id = 789,
            position = 1,
            product_id = 123456L,
            src = "https://example.com/image.jpg",
            updated_at = "2023-06-24",
            variant_ids = listOf(1, 2, 3),
            width = 600
        ),
        images = listOf(
            ImageX(
                height = 800,
                id = 789,
                position = 1,
                product_id = 123456L,
                src = "https://example.com/image1.jpg",
                updated_at = "2023-06-24",
                variant_ids = listOf(1, 2, 3),
                width = 600
            ),
            ImageX(
                height = 800,
                id = 790,
                position = 2,
                product_id = 123456L,
                src = "https://example.com/image2.jpg",
                updated_at = "2023-06-24",
                variant_ids = listOf(4, 5, 6),
                width = 600
            )
        ),
        options = listOf(
            Option(
                id = 1,
                name = "Color",
                position = 1,
                product_id = 123456L,
                values = listOf("Red", "Blue", "Green")
            ),
            Option(
                id = 2,
                name = "Size",
                position = 2,
                product_id = 123456L,
                values = listOf("Small", "Medium", "Large")
            )
        ),
        product_type = "Clothing",
        status = "Active",
        tags = "Tag1,Tag2,Tag3",
        title = "Sample Product",
        variants = listOf(
            Variant(
                fulfillment_service = "Manual",
                grams = 200,
                id = 1,
                image_id = 789,
                inventory_item_id = 123,
                inventory_management = "Shopify",
                inventory_policy = "Deny",
                inventory_quantity = 10,
                old_inventory_quantity = 5,
                option1 = "Red",
                option2 = "Small",
                option3 = null,
                position = 1,
                price = 19.99,
                product_id = 123456L,
                requires_shipping = true,
                sku = "ABC123",
                taxable = true,
                title = "Red - Small",
                updated_at = "2023-06-24",
                weight = 0.2,
                weight_unit = "kg"
            ),
            Variant(
                fulfillment_service = "Manual",
                grams = 250,
                id = 2,
                image_id = 789,
                inventory_item_id = 124,
                inventory_management = "Shopify",
                inventory_policy = "Deny",
                inventory_quantity = 5,
                old_inventory_quantity = 2,
                option1 = "Blue",
                option2 = "Medium",
                option3 = null,
                position = 2,
                price = 24.99,
                product_id = 123456L,
                requires_shipping = true,
                sku = "DEF456",
                taxable = true,
                title = "Blue - Medium",
                updated_at = "2023-06-24",
                weight = 0.25,
                weight_unit = "kg"
            )
        ),
        vendor = "Example Vendor",
        isFav = false,
        rating = "4.5"
    ),Product(
        id = 123456L,
        selectedVariantIndex = 0,
        image = ImageX(
            height = 800,
            id = 789,
            position = 1,
            product_id = 123456L,
            src = "https://example.com/image.jpg",
            updated_at = "2023-06-24",
            variant_ids = listOf(1, 2, 3),
            width = 600
        ),
        images = listOf(
            ImageX(
                height = 800,
                id = 789,
                position = 1,
                product_id = 123456L,
                src = "https://example.com/image1.jpg",
                updated_at = "2023-06-24",
                variant_ids = listOf(1, 2, 3),
                width = 600
            ),
            ImageX(
                height = 800,
                id = 790,
                position = 2,
                product_id = 123456L,
                src = "https://example.com/image2.jpg",
                updated_at = "2023-06-24",
                variant_ids = listOf(4, 5, 6),
                width = 600
            )
        ),
        options = listOf(
            Option(
                id = 1,
                name = "Color",
                position = 1,
                product_id = 123456L,
                values = listOf("Red", "Blue", "Green")
            ),
            Option(
                id = 2,
                name = "Size",
                position = 2,
                product_id = 123456L,
                values = listOf("Small", "Medium", "Large")
            )
        ),
        product_type = "Clothing",
        status = "Active",
        tags = "Tag1,Tag2,Tag3",
        title = "Sample Product",
        variants = listOf(
            Variant(
                fulfillment_service = "Manual",
                grams = 200,
                id = 1,
                image_id = 789,
                inventory_item_id = 123,
                inventory_management = "Shopify",
                inventory_policy = "Deny",
                inventory_quantity = 10,
                old_inventory_quantity = 5,
                option1 = "Red",
                option2 = "Small",
                option3 = null,
                position = 1,
                price = 19.99,
                product_id = 123456L,
                requires_shipping = true,
                sku = "ABC123",
                taxable = true,
                title = "Red - Small",
                updated_at = "2023-06-24",
                weight = 0.2,
                weight_unit = "kg"
            ),
            Variant(
                fulfillment_service = "Manual",
                grams = 250,
                id = 2,
                image_id = 789,
                inventory_item_id = 124,
                inventory_management = "Shopify",
                inventory_policy = "Deny",
                inventory_quantity = 5,
                old_inventory_quantity = 2,
                option1 = "Blue",
                option2 = "Medium",
                option3 = null,
                position = 2,
                price = 24.99,
                product_id = 123456L,
                requires_shipping = true,
                sku = "DEF456",
                taxable = true,
                title = "Blue - Medium",
                updated_at = "2023-06-24",
                weight = 0.25,
                weight_unit = "kg"
            )
        ),
        vendor = "Example Vendor",
        isFav = false,
        rating = "4.5"
    )
)
