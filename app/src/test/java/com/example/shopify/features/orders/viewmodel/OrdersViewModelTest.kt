package com.example.shopify.features.orders.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.shopify.MainDispatcherRule
import com.example.shopify.core.common.data.model.CustomerId
import com.example.shopify.core.util.ApiState
import com.example.shopify.features.orders.model.fakeOrderResponseDataList
import com.example.shopify.features.orders.model.model_response.OrderResponseData
import com.example.shopify.features.orders.repository.FakeOrdersRepository
import com.example.shopify.features.orders.repository.IOrdersRepository
import com.example.shopify.getOrAwaitValue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.pauseDispatcher
import kotlinx.coroutines.test.resumeDispatcher
import kotlinx.coroutines.test.runBlockingTest
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.CoreMatchers.not
import org.hamcrest.CoreMatchers.notNullValue
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class OrdersViewModelTest {

    private lateinit var ordersViewModel: OrdersViewModel
    private lateinit var fakeOrderRepository: IOrdersRepository

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val mainCoroutineDispatcher = MainDispatcherRule()

    @Before
    fun setup() {
        fakeOrderRepository = FakeOrdersRepository()
        ordersViewModel = OrdersViewModel(fakeOrderRepository)
    }

    @Test
    fun getFlowOrders_givenCustomerId_returnOrdersList() = mainCoroutineDispatcher.runBlockingTest {

        //when -> call the method in the view model
        ordersViewModel.getFlowOrders(CustomerId(51351351351))

       val apiState = ordersViewModel.orders.getOrAwaitValue{} as ApiState.Success<*>

        val result = apiState.myData as List<OrderResponseData>

        //then -> should return list of orders
        //assert the list not null
        assertThat(result, notNullValue())
        //assert the list not empty
        assertThat(result, not(emptyList()))
    }

    @Test
    fun getFlowOrders_givenCustomerId_validReturnedList() =
        mainCoroutineDispatcher.runBlockingTest {

            //when call the method in the view model
            ordersViewModel.getFlowOrders(CustomerId(51351351351))

            val apiState = ordersViewModel.orders.getOrAwaitValue {  } as ApiState.Success<*>
            val result = apiState.myData as List<OrderResponseData>

            //then -> should return valid list (my mock list)
            //assert it's the same list
            assertThat(result, `is`(fakeOrderResponseDataList))
            //assert that the order id is the same
            assertThat(result[0].id, `is`(fakeOrderResponseDataList[0].id))
        }

    @Test
    fun getFlowOrders_givenCustomerId_stateChangeFromLoadingToSuccess() =
        mainCoroutineDispatcher.runBlockingTest {
            //pause the thread
            mainCoroutineDispatcher.pauseDispatcher()
            //assert that ApiState is loading
            assertThat(ordersViewModel.orders.value, `is`(ApiState.Loading))
            //resume the thread
            mainCoroutineDispatcher.resumeDispatcher()
            //when call the method in the view model
            ordersViewModel.getFlowOrders(CustomerId(51351351351))
            //then the state should change to success

            val apiState = ordersViewModel.orders.getOrAwaitValue {  }
            
            apiState as ApiState.Success<OrderResponseData>
            //assert that the state is success and data is retrieved
            assertThat(apiState.myData, `is`(ApiState.Success(fakeOrderResponseDataList).myData))
        }

}