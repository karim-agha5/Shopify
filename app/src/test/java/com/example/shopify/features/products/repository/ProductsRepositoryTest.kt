package com.example.shopify.features.products.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.shopify.MainDispatcherRule
import com.example.shopify.features.products.model.fakeProducts
import com.example.shopify.features.products.network.FakeProductsDataSource
import com.example.shopify.features.products.network.ProductsDataSource
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runBlockingTest
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.CoreMatchers.not
import org.hamcrest.CoreMatchers.notNullValue
import org.junit.Assert.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class ProductsRepositoryTest {

    private lateinit var productsRepository : IProductsRepository
    private lateinit var fakeProductsDataSource : ProductsDataSource

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val mainCoroutineDispatcher = MainDispatcherRule()

    @Before
    fun setup(){
        fakeProductsDataSource = FakeProductsDataSource()
        productsRepository = ProductsRepository.getInstance(fakeProductsDataSource)
    }

    @Test
    fun getProductsByCollection_givenCollectionId_returnProductsList() = mainCoroutineDispatcher.runBlockingTest {
        //when -> call the method in the repository
        val result = productsRepository.getProductsByCollection(35136536361).first()

        //then -> should return list of products
        //asser the list not null
        assertThat(result,notNullValue())
        //assert the list not empty
        assertThat(result,not(emptyList()))
    }

    @Test
    fun getProductsByCollection_givenCollectionId_validReturnedList() = mainCoroutineDispatcher.runBlockingTest {
        //when calling the method in the repository
        val result = productsRepository.getProductsByCollection(135135434343).first()

        //then -> should return valid list (my mock list)
        //assert it's the same list
        assertThat(result,`is`(fakeProducts))
        //assert it's the same data
        assertThat(result[0].id,`is`(fakeProducts[0].id))
        assertThat(result[0].title, `is`(fakeProducts[0].title))
    }

    @Test
    fun getFilterOptions_givenCollectionId_returnOptionsList() = mainCoroutineDispatcher.runBlockingTest {
        //when calling the method in the repository
        val result = productsRepository.getFilterOptions(35164684)

        //then -> should return list of options
        //assert the list not null
        assertThat(result, notNullValue())
        //assert the list isn't empty
        assertThat(result,not(emptyList()))
    }


}