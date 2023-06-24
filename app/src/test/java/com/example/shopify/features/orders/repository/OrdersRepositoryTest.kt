package com.example.shopify.features.orders.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.shopify.MainDispatcherRule
import com.example.shopify.core.common.data.model.CustomerId
import com.example.shopify.features.orders.model.fakeOrderResponseDataList
import com.example.shopify.features.orders.network.FakeOrdersDataSource
import com.example.shopify.features.orders.network.OrdersDataSource
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
class OrdersRepositoryTest {

    private lateinit var ordersRepository: IOrdersRepository
    private lateinit var fakeOrdersDataSource: OrdersDataSource

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val mainCoroutineDispatcher = MainDispatcherRule()

    @Before
    fun setup() {
        fakeOrdersDataSource = FakeOrdersDataSource()
        ordersRepository = OrdersRepository.getInstance(fakeOrdersDataSource)
    }

    @Test
    fun getOrdersByCustomerId_givenCustomerId_returnOrdersList() =
        mainCoroutineDispatcher.runBlockingTest {
            //when -> call the method in the repository
            val result = ordersRepository.getOrdersByCustomerId(CustomerId(5113135521226)).first()

            //then -> should return list of orders
            //assert the list not null
            assertThat(result, notNullValue())
            //assert the list not empty
            assertThat(result, not(emptyList()))
        }

    @Test
    fun getOrdersByCustomerId_givenCustomerId_validReturnedList() =
        mainCoroutineDispatcher.runBlockingTest {
            //when calling the method in the repository
            val result = ordersRepository.getOrdersByCustomerId(CustomerId(135136136813)).first()

            //then -> should return valid list (my mock list)
            //assert it's the same list
            assertThat(result, `is`(fakeOrderResponseDataList))
            //assert it's the same data
            assertThat(result[0].id, `is`(fakeOrderResponseDataList[0].id))
            assertThat(result[0].customer, `is`(fakeOrderResponseDataList[0].customer))
        }
}