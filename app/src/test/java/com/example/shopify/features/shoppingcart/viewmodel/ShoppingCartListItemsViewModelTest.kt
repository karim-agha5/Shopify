package com.example.shopify.features.shoppingcart.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.shopify.MainDispatcherRule
import com.example.shopify.core.common.data.model.CustomerResponseInfo
import com.example.shopify.core.common.features.draftorder.data.FakeShoppingCartRepository
import com.example.shopify.core.common.features.draftorder.data.IDraftOrderRepository
import com.example.shopify.core.common.features.draftorder.model.customer
import com.example.shopify.core.common.features.draftorder.model.fakeCustomerResponseInfo
import com.example.shopify.core.common.features.draftorder.model.fakeModifyDraftOrderResponseLineItem
import com.example.shopify.core.common.features.draftorder.model.modification.response.ModifyDraftOrderResponse
import com.example.shopify.core.common.features.draftorder.model.modification.response.ModifyDraftOrderResponseBody
import com.example.shopify.core.common.features.draftorder.model.modification.response.ModifyDraftOrderResponseLineItem
import com.example.shopify.core.util.ApiState2
import com.example.shopify.features.shoppingcart.domain.DraftOrder
import com.example.shopify.getOrAwaitValue
import com.example.shopify.getOrAwaitValueApiState2
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.hamcrest.CoreMatchers.notNullValue
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class ShoppingCartListItemsViewModelTest{

    private lateinit var fakeShoppingCartRepository: IDraftOrderRepository
    private lateinit var draftOrder: DraftOrder
    private lateinit var shoppingCartListItemsViewModel: ShoppingCartListItemsViewModel

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @Before
    fun setupViewModel(){
        fakeShoppingCartRepository = FakeShoppingCartRepository()
        draftOrder = DraftOrder(fakeShoppingCartRepository)
        shoppingCartListItemsViewModel = ShoppingCartListItemsViewModel(fakeShoppingCartRepository,draftOrder)
    }

    @Test
    fun getShoppingCart_NotEmptyDraftOrderId_RetreiveListWithSizeMoreThanOne() = mainDispatcherRule.runBlockingTest {

        // Given -> A correct draft order id
        val draftOrderId = "1128025194815"

        // When -> call the getShoppingCart() method with the correct draft order id and collect the stateflow value
        // The list returned should at the very least contain 1 value
        shoppingCartListItemsViewModel.getShoppingCart(draftOrderId)
        val response = shoppingCartListItemsViewModel.listItemsStateFlow.getOrAwaitValueApiState2 {  } as ModifyDraftOrderResponseBody
        val list = response.lineItems

        // Then -> assert that the list is not null
        assertThat(list, notNullValue())
    }

    @Test
    fun removeOrder_fakeCustomerInfoAndAnyModifyLineItem_retrieveTheSameCustomerInfo() = mainDispatcherRule.runBlockingTest {
        // Given -> fake customer info that contains predetermined customer id and valid draft order id
        val fakeCustomerResponseInfo = fakeCustomerResponseInfo
        val fakeModifyDraftOrderResponseLineItem = fakeModifyDraftOrderResponseLineItem

        // When -> calling the removeOrder() method
        shoppingCartListItemsViewModel.removeOrder(fakeCustomerResponseInfo,fakeModifyDraftOrderResponseLineItem)
        val response = shoppingCartListItemsViewModel.listItemsStateFlow.getOrAwaitValueApiState2 {  } as ModifyDraftOrderResponseBody

        // the new draft order with the same customer data will be retrieved
        assertEquals(fakeCustomerResponseInfo.id,response.customer?.id)
    }


}