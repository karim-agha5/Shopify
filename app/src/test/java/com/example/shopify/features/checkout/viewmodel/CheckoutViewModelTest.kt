package com.example.shopify.features.checkout.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.shopify.MainDispatcherRule
import com.example.shopify.core.common.features.draftorder.data.FakeShoppingCartRepository
import com.example.shopify.core.common.features.draftorder.data.IDraftOrderRepository
import com.example.shopify.core.common.features.draftorder.model.fakeCheckoutOrderRequest
import com.example.shopify.features.checkout.data.FakeCheckoutRepository
import com.example.shopify.features.checkout.data.ICheckoutRepository
import com.example.shopify.features.checkout.model.CheckoutOrderResponse
import com.example.shopify.features.shoppingcart.domain.DraftOrder
import com.example.shopify.features.shoppingcart.viewmodel.ShoppingCartListItemsViewModel
import com.example.shopify.getOrAwaitValueApiState2
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class CheckoutViewModelTest{

    private lateinit var fakeCheckoutRepository : ICheckoutRepository
    private lateinit var checkoutViewModel: CheckoutViewModel

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @Before
    fun setupViewModel(){
        fakeCheckoutRepository = FakeCheckoutRepository()
        checkoutViewModel = CheckoutViewModel(fakeCheckoutRepository)
    }


    @Test
    fun createOrder_fakeCheckoutRequest_returnCheckoutResponseWith5AsId() = mainDispatcherRule.runBlockingTest {
        // Given -> fake checkout request
        val fakeCheckoutRequest = fakeCheckoutOrderRequest

        // When -> call the create order method and collect on the flow
        checkoutViewModel.createOrder(fakeCheckoutRequest)
        val response = checkoutViewModel.orderCheckoutState.getOrAwaitValueApiState2 {  } as CheckoutOrderResponse
        val result = response.order

        assertEquals(5L,result.id)
    }
}