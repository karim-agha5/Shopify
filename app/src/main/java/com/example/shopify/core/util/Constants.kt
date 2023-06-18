package com.example.shopify.core.util

object Constants {

    const val ADS_TRANSITION_ANIMATION_DELAY = 3000L

    //https://{apikey}:{password}@{hostname}/admin/api/{version}/{resource}.json
    //https://65464e9a325676b2a005d0e7269625bb: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@mad43-alex-and3.myshopify.com/admin/api/2023-04/smart_collections.json
    const val API_KEY = "https://65464e9a325676b2a005d0e7269625bb"
    const val PASSWORD = "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"

    const val HOSTNAME = "mad43-alex-and3.myshopify.com/admin/api/2023-04/"
    const val BASE_URL: String = "https://mad43-alex-and3.myshopify.com/admin/api/2023-04/"

    //HEADERS FOR API CALL
    const val ACCESS_TOKEN_HEADER =  "X-Shopify-Access-Token"
    const val CONTENT_TYPE = "application/json"
    const val CONTENT_TYPE_HEADER_KEY = "Content-Type"

    val promocodes: Array<String> =
        arrayOf(
            "X5ASF5", // 50%
            "D5G73S", // 30%
            "W9R1FH", // 40%
            "Q2G5H9" // 50%
        )
}
