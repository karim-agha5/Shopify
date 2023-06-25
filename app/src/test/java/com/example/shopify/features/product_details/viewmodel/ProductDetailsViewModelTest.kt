package com.example.shopify.features.product_details.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.shopify.MainDispatcherRule
import com.example.shopify.common.features.draftorder.data.FakeDraftOrderRepositoryImpl
import com.example.shopify.core.common.data.model.Product
import com.example.shopify.core.common.features.draftorder.data.IDraftOrderRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.hamcrest.CoreMatchers.`is`
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class ProductDetailsViewModelTest{
    private lateinit var productDetailsViewModel: ProductDetailsViewModel
    private lateinit var fakeDraftOrderRepo: IDraftOrderRepository

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val mainCoroutineDispatcher = MainDispatcherRule()

    @Before
    fun setup(){
        fakeDraftOrderRepo = FakeDraftOrderRepositoryImpl()
        productDetailsViewModel = ProductDetailsViewModel(123,fakeDraftOrderRepo)
    }

    @Test
    fun addToCart_givenProduct_returnTrue() = mainCoroutineDispatcher.runBlockingTest{
        productDetailsViewModel.addToCart(Product(
            id = 123,
            selectedVariantIndex = null,
            image = null,
            images = listOf(),
            options = listOf(),
            product_type = null,
            status = null,
            tags = null,
            title = null,
            variants = listOf(),
            vendor = null,
            isFav = null,
            rating = null
        ),0){
            assertThat(it, `is`(true))
        }
    }
}