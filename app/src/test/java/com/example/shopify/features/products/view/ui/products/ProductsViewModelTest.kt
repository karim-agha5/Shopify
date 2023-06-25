package com.example.shopify.features.products.view.ui.products

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.shopify.MainDispatcherRule
import com.example.shopify.core.common.data.model.Product
import com.example.shopify.core.util.ApiState
import com.example.shopify.features.products.model.fakeProducts
import com.example.shopify.features.products.repository.FakeProductsRepository
import com.example.shopify.features.products.repository.IProductsRepository
import com.example.shopify.getOrAwaitValue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.pauseDispatcher
import kotlinx.coroutines.test.resumeDispatcher
import kotlinx.coroutines.test.runBlockingTest
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.CoreMatchers.not
import org.hamcrest.CoreMatchers.notNullValue
import org.junit.Assert.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class ProductsViewModelTest {

    private lateinit var productsViewModel : ProductsViewModel
    private lateinit var fakeProductsRepository : IProductsRepository

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val mainCoroutineDispatcher = MainDispatcherRule()

    @Before
    fun setup(){
        fakeProductsRepository = FakeProductsRepository()
        productsViewModel = ProductsViewModel(fakeProductsRepository)
    }

    @Test
    fun getProductsByCollection_givenCollectionId_returnProductsList() = mainCoroutineDispatcher.runBlockingTest {
        //when -> call the method in the view model
        productsViewModel.getProductsByCollection(5343456443)

        val apiState = productsViewModel.products.getOrAwaitValue {  } as ApiState.Success<*>
        val result = apiState.myData as List<Product>

        //then should return list of products
        //assert the list not null
        assertThat(result, notNullValue())
        //assert the list not empty
        assertThat(result, not(emptyList()))
    }

    @Test
    fun getProductsByCollection_givenCollectionId_returnValidProductsList() = mainCoroutineDispatcher.runBlockingTest{

        //when call the method in the view model
        productsViewModel.getProductsByCollection(4537353453)

        val apiState = productsViewModel.products.getOrAwaitValue {  } as ApiState.Success<*>
        val result = apiState.myData as List<Product>

        //then -> should return valid list (my mock products list)
        //assert it's the same list
        assertThat(result, `is`(fakeProducts))
        //assert it's the same data
        assertThat(result[0].id, `is`(fakeProducts[0].id))
        assertThat(result[0].title, `is`(fakeProducts[0].title))
    }

    @Test
    fun getProductsByCollection_giveCollectionId_stateChangeFromLoadingToSuccess () = mainCoroutineDispatcher.runBlockingTest{
        //pause the thread
        mainCoroutineDispatcher.pauseDispatcher()
        //assert that ApiState is loading
        assertThat(productsViewModel.products.value, `is`(ApiState.Loading))
        //resume the thread
        mainCoroutineDispatcher.resumeDispatcher()
        //when call the method in the view model
        productsViewModel.getProductsByCollection(5136836836)
        //then the state should change to success
        val apiState = productsViewModel.products.getOrAwaitValue {  }
        apiState as ApiState.Success<Product>
        //assert that the state is success and data is retrieved
        assertThat(apiState.myData, `is`(ApiState.Success(fakeProducts).myData))
    }


}