package com.example.shopify.core.common.features.draftorder.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.shopify.MainDispatcherRule
import com.example.shopify.common.features.draftorder.data.FakeDraftOrderRepositoryImpl
import com.example.shopify.common.features.draftorder.data.remote.FakeDraftOrderRemoteSourceImpl
import com.example.shopify.common.features.draftorder.model.draftOrder
import com.example.shopify.core.common.features.draftorder.data.remote.IDraftOrderRemoteSource
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.hamcrest.CoreMatchers
import org.hamcrest.CoreMatchers.notNullValue
import com.example.shopify.core.common.features.draftorder.data.remote.FakeDraftOrderRemoteSource
import com.example.shopify.core.common.features.draftorder.data.remote.IDraftOrderRemoteSource
import com.example.shopify.core.common.features.draftorder.model.RequestCustomer
import com.example.shopify.core.common.features.draftorder.model.appliedDiscount
import com.example.shopify.core.common.features.draftorder.model.billingAddress
import com.example.shopify.core.common.features.draftorder.model.creation.request.CreateDraftOrderRequestBody
import com.example.shopify.core.common.features.draftorder.model.creation.request.CreateDraftOrderRequestDraftOrder
import com.example.shopify.core.common.features.draftorder.model.creation.response.CreateDraftOrderResponse
import com.example.shopify.core.common.features.draftorder.model.creation.response.CreateDraftOrderResponseDraftOrder
import com.example.shopify.core.common.features.draftorder.model.customer
import com.example.shopify.core.common.features.draftorder.model.fakeModifyDraftOrderRequestBody
import com.example.shopify.core.common.features.draftorder.model.fakeModifyDraftOrderResponseBody
import com.example.shopify.core.common.features.draftorder.model.lineItems
import com.example.shopify.core.common.features.draftorder.model.modification.request.ModifyDraftOrderRequestBody
import com.example.shopify.core.common.features.draftorder.model.modification.response.ModifyDraftOrderResponse
import com.example.shopify.core.common.features.draftorder.model.shippingAddress
import com.example.shopify.getOrAwaitValueApiState2
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class DraftOrderRepositoryImplTest{
    private lateinit var draftRepo: IDraftOrderRepository
    private lateinit var fakeDraftOrderRemoteSourceImpl: IDraftOrderRemoteSource\
  
    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule

    val mainCoroutineDispatcher = MainDispatcherRule()

    @Before
    fun setup(){
        fakeDraftOrderRemoteSourceImpl = FakeDraftOrderRemoteSourceImpl()
        draftRepo = DraftOrderRepositoryImpl(fakeDraftOrderRemoteSourceImpl)
    }

    @Test
    fun getShoppingCartByDraftOrderId_givenDraftOrderId_returnTrue(){
        mainCoroutineDispatcher.runBlockingTest {
            val result = draftRepo.getShoppingCart("1234")

            assertThat(result, notNullValue())
        }
    }
 
    val mainDispatcherRule = MainDispatcherRule()

    @Before
    fun setup(){
        fakeDraftOrderRemoteSource = FakeDraftOrderRemoteSource()
        draftOrderRepository = DraftOrderRepositoryImpl(fakeDraftOrderRemoteSource)
    }

    @Test
    fun createShoppingCart_correctRequestBody_retrieveCreatedDraftOrderInfo() = mainDispatcherRule.runBlockingTest{

        val fakeCreateDraftOrderResponseDraftOrder = CreateDraftOrderResponseDraftOrder(
            id = 575,
            note = null,
            email = "",
            taxes_included = true,
            currency = "",
            invoice_sent_at = null,
            created_at = "",
            updated_at = "",
            tax_exempt = false,
            completed_at = null,
            name = "",
            status = "",
            line_items = lineItems,
            shipping_CreateDraftOrderResponse_address = shippingAddress,
            billing_CreateDraftOrderResponse_address = billingAddress,
            invoice_url = "",
            applied_discount = appliedDiscount,
            order_id = null,
            shipping_line = null,
            tax_lines = null,
            tags = "",
            note_attributes = null,
            total_price = "",
            subtotal_price = "",
            total_tax = "",
            payment_terms = null,
            admin_graphql_api_id = "",
            customer = customer
        )
        val fakeCreateDraftOrderResponse = CreateDraftOrderResponse(fakeCreateDraftOrderResponseDraftOrder)

        // Given -> A valid draft order request body
        val draftOrder = CreateDraftOrderRequestDraftOrder(
            listOf(),
            null,
            RequestCustomer(12125)
        )
        val createDraftOrderRequestBody = CreateDraftOrderRequestBody(draftOrder)

        // When -> Call the create shoppingCart() method that should be returning the same shopping cart
        // provided in the fake source
        val fakeResponse = draftOrderRepository.createShoppingCart(createDraftOrderRequestBody)

        // Then -> Assert that a draft id is returned with the specified id
        assertEquals(fakeCreateDraftOrderResponse.draftOrder.id,fakeResponse.draftOrder.id)
    }

    @Test
    fun getShoppingCart_givenAnyDraftOrderId_retrieveValidDraftWithPredeterminedId() = mainDispatcherRule.runBlockingTest {
        // Given -> Any draft order id
        val draftOrderId = "8654151"

        // When -> call the getShoppingCart() method with the random draftOrderId and retrieve a flow
        val fakeResponseFlow = draftOrderRepository.getShoppingCart(draftOrderId)
        var response: ModifyDraftOrderResponse? = null
        fakeResponseFlow.collect{
            response = it
        }

        // Then -> the response should contain a flow that collects a predetermined draft order id
        assertEquals(575L,response?.draftOrder?.id)
    }


    @Test
    fun modifyShoppingCart_givenAnyDraftOrderIdAndAnyDraftRequestBody_retrieveValidDraftWithPredeterminedId() = mainDispatcherRule.runBlockingTest {
        // Given -> Any draft order id and any draft order request body
        val draftOrderId = "8654151"
        val body = fakeModifyDraftOrderRequestBody

        // When -> call the modifyShoppingCart() method with an invalid draft order id
        // and draft order request body to retrieve a flow
        val fakeResponseFlow = draftOrderRepository.modifyShoppingCart(draftOrderId, body)
        var response: ModifyDraftOrderResponse? = null
        fakeResponseFlow.collect{
            response = it
        }

        // Then -> the response should contain a flow that collects a predetermined draft order id
        assertEquals(575L,response?.draftOrder?.id)
    }
}