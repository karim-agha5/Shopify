package com.example.shopify.features.checkout.paymentgateway.stripe

import com.example.shopify.BuildConfig
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val STRIPE_BASE_URL = "https://api.stripe.com/v1/"


object StripeOkHttpClient{
     val client: OkHttpClient =
        OkHttpClient
        .Builder()
            .addInterceptor {
                val request =
                    it.request()
                        .newBuilder()
                        .addHeader("Authorization","Bearer ${BuildConfig.STRIPE_SECRET_KEY}")
                        .build()
                it.proceed(request)
            }
            .build()
}


object StripeRetrofitHelper {
    private val retrofit = Retrofit
        .Builder()
        .client(StripeOkHttpClient.client)
        .baseUrl(STRIPE_BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    fun getInstance(): Retrofit = retrofit
}