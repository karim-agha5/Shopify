package com.example.shopify.core.common.interfaces

interface IMainRemoteDataSource<T> {
    suspend fun fetchData(vararg params: Any): T
}