package com.example.shopify.features.home.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.shopify.MainDispatcherRule
import com.example.shopify.features.home.model.fakeSmartCollectionList
import com.example.shopify.features.home.network.FakeHomeDataSource
import com.example.shopify.features.home.network.HomeDataSource
import com.example.shopify.features.products.model.fakeProducts
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runBlockingTest
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.CoreMatchers.not
import org.hamcrest.CoreMatchers.notNullValue
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class HomeRepositoryTest {
    private lateinit var homeRepository : IHomeRepository
    private lateinit var fakeHomeDataSource : HomeDataSource

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val mainCoroutineDispatcher = MainDispatcherRule()

    @Before
    fun setup() {
        fakeHomeDataSource = FakeHomeDataSource()
        homeRepository = HomeRepository.getInstance(fakeHomeDataSource)
    }

    @Test
    fun getBrands_callRepositoryMethod_returnBrandsList () = mainCoroutineDispatcher.runBlockingTest{
        //when -> call the method in the repository
        val result = homeRepository.getBrands().first()

        //then -> should return list of products
        //assert that list not null
        assertThat(result,notNullValue())
        //assert the list not empty
        assertThat(result,not(emptyList()))
    }

    @Test
    fun getBrands_callRepositoryMethod_validReturnedList() = mainCoroutineDispatcher.runBlockingTest {
        //when calling the method in the repository
        val result = homeRepository.getBrands().first()

        //then -> should return valid list (my mock list)
        //assert it's the same list
        assertThat(result,`is`(fakeSmartCollectionList))
        //assert it's the same data
        assertThat(result[0].id,`is`(fakeSmartCollectionList[0].id))
        assertThat(result[0].title, `is`(fakeSmartCollectionList[0].title))
    }

    @Test
    fun getLimitedProducts_limitIsOne_productListOfOne () = mainCoroutineDispatcher.runBlockingTest{
        //when -> call the method in the repository
        val result = homeRepository.getLimitedProducts(1).first()

        //then -> should return list of products
        //assert that list not null
        assertThat(result,notNullValue())
        //assert the list is has only one element
        assertThat(result.size, `is`(1))
    }

    @Test
    fun getAllProducts_callRepositoryMethod_returnAllProducts () = mainCoroutineDispatcher.runBlockingTest {
        //when -> call the method in the repository
        val result = homeRepository.getAllProducts().first()

        //then -> should return all products
        assertThat(result, `is`(fakeProducts))
    }

}