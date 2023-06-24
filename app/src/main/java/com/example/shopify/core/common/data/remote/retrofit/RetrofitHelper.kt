package com.example.shopify.core.common.data.remote.retrofit

import android.util.Log
import com.example.shopify.core.util.Constants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitHelper {
    private val retrofit: Retrofit.Builder =
        Retrofit
        .Builder()
        /*.baseUrl(Constants.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
            .build()*/
    fun getInstance (isDefaultPath: Boolean = true): Retrofit {
            return if(isDefaultPath){
                retrofit.baseUrl(Constants.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }else{
                Log.d("TAG", "${Constants.API_KEY}:${Constants.PASSWORD}@${Constants.HOSTNAME}")
                retrofit.baseUrl("${Constants.API_KEY}:${Constants.PASSWORD}@${Constants.HOSTNAME}")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }
    }
}