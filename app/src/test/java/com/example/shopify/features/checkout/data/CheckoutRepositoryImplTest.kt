package com.example.shopify.features.checkout.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.shopify.MainDispatcherRule
import com.example.shopify.core.common.data.model.CheckoutOrder
import com.example.shopify.core.common.features.draftorder.data.DraftOrderRepositoryImpl
import com.example.shopify.core.common.features.draftorder.data.IDraftOrderRepository
import com.example.shopify.core.common.features.draftorder.data.remote.FakeDraftOrderRemoteSource
import com.example.shopify.core.common.features.draftorder.data.remote.IDraftOrderRemoteSource
import com.example.shopify.core.common.features.draftorder.model.fakeCheckoutOrderRequest
import com.example.shopify.features.checkout.data.remote.FakeCheckoutRemoteSource
import com.example.shopify.features.checkout.data.remote.ICheckoutRemoteSource
import com.example.shopify.features.checkout.model.CheckoutOrderRequest
import com.example.shopify.features.checkout.model.CheckoutOrderResponse
import com.example.shopify.features.checkout.model.CheckoutOrderResponseBody
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class CheckoutRepositoryImplTest{

    private lateinit var checkoutRepository: ICheckoutRepository
    private lateinit var fakeCheckoutRemoteSource: ICheckoutRemoteSource

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @Before
    fun setup(){
        fakeCheckoutRemoteSource = FakeCheckoutRemoteSource()
        checkoutRepository = CheckoutRepositoryImpl(fakeCheckoutRemoteSource)
    }

    @Test
    fun createOrder_fakeCheckoutOrderRequestWithFiveAsTheId_returnTheSameOutput() = mainDispatcherRule.runBlockingTest {
        // Given -> fake checkout with fake data
        val fakeCheckoutOrderRequest = fakeCheckoutOrderRequest

        // When -> call createOrder()
        val responseFlow = checkoutRepository.createOrder(fakeCheckoutOrderRequest)
        var response: CheckoutOrderResponse? = null
        responseFlow.collect{
            response = it
        }

        // then
        assertEquals(5L,response?.order?.id)
    }
}