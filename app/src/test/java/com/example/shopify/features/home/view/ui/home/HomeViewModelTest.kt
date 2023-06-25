package com.example.shopify.features.home.view.ui.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.shopify.MainDispatcherRule
import com.example.shopify.core.common.data.model.Product
import com.example.shopify.core.common.data.model.SmartCollection
import com.example.shopify.core.util.ApiState
import com.example.shopify.features.home.model.fakeSmartCollectionList
import com.example.shopify.features.home.repository.FakeHomeRepository
import com.example.shopify.features.home.repository.IHomeRepository
import com.example.shopify.features.products.model.fakeProducts
import com.example.shopify.getOrAwaitValue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.pauseDispatcher
import kotlinx.coroutines.test.resumeDispatcher
import kotlinx.coroutines.test.runBlockingTest
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.CoreMatchers.not
import org.hamcrest.CoreMatchers.notNullValue
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class HomeViewModelTest {

    private lateinit var homeViewModel : HomeViewModel
    private lateinit var fakeHomeRepository : IHomeRepository

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val mainCoroutineDispatcher = MainDispatcherRule()

    @Before
    fun setup(){
        fakeHomeRepository = FakeHomeRepository()
        homeViewModel = HomeViewModel(fakeHomeRepository)
    }

    @Test
    fun getStateFlowProducts_callViewModelMethod_returnBrandsList() = mainCoroutineDispatcher.runBlockingTest {
        //when -> call the method in the view model
        homeViewModel.getStateFlowProducts()

        val apiState = homeViewModel.brands.getOrAwaitValue {  } as ApiState.Success<*>
        val result = apiState.myData as List<SmartCollection>

        //then should return list of brands
        //assert the list not null
        assertThat(result, notNullValue())
        //assert the list not empty
        assertThat(result, not(emptyList()))
    }

    @Test
    fun getStateFlowProducts_callViewModelMethod_returnValidBrandsList() = mainCoroutineDispatcher.runBlockingTest{

        //when call the method in the view model
        homeViewModel.getStateFlowProducts()

        val apiState = homeViewModel.brands.getOrAwaitValue {  } as ApiState.Success<*>
        val result = apiState.myData as List<SmartCollection>

        //then -> should return valid list (my mock brands list)
        //assert it's the same list
        assertThat(result, `is`(fakeSmartCollectionList))
        //assert it's the same data
        assertThat(result[0].id, `is`(fakeSmartCollectionList[0].id))
        assertThat(result[0].title, `is`(fakeSmartCollectionList[0].title))
    }

    @Test
    fun getStateFlowProducts_callViewModelMethod_stateChangeFromLoadingToSuccess () = mainCoroutineDispatcher.runBlockingTest{
        //pause the thread
        mainCoroutineDispatcher.pauseDispatcher()
        //assert that ApiState is loading
        assertThat(homeViewModel.brands.value, `is`(ApiState.Loading))
        //resume the thread
        mainCoroutineDispatcher.resumeDispatcher()
        //when call the method in the view model
        homeViewModel.getStateFlowProducts()
        //then the state should change to success
        val apiState = homeViewModel.brands.getOrAwaitValue {  }
        apiState as ApiState.Success<SmartCollection>
        //assert that the state is success and data is retrieved
        assertThat(apiState.myData, `is`(ApiState.Success(fakeSmartCollectionList).myData))
    }

    @Test
    fun getTenProducts_callViewModelMethod_returnProductsList () = mainCoroutineDispatcher.runBlockingTest {
        //when -> call the method in the view model
        homeViewModel.getTenProducts()

        val apiState = homeViewModel.tenProducts.getOrAwaitValue {  } as ApiState.Success<*>
        val result = apiState.myData as List<Product>

        //then should return list of products
        //assert the list not null
        assertThat(result, notNullValue())
        //assert the list not empty
        assertThat(result, not(emptyList()))
    }

    @Test
    fun getTenProducts_callViewModelMethod_stateChangeFromLoadingToSuccess () = mainCoroutineDispatcher.runBlockingTest{
        //pause the thread
        mainCoroutineDispatcher.pauseDispatcher()
        //assert that ApiState is loading
        assertThat(homeViewModel.tenProducts.value, `is`(ApiState.Loading))
        //resume the thread
        mainCoroutineDispatcher.resumeDispatcher()
        //when call the method in the view model
        homeViewModel.getStateFlowProducts()
        //then the state should change to success
        val apiState = homeViewModel.tenProducts.getOrAwaitValue {  }
        apiState as ApiState.Success<Product>
        //assert that the state is success and data is retrieved
        assertThat(apiState.myData, `is`(ApiState.Success(fakeProducts).myData))
    }
}