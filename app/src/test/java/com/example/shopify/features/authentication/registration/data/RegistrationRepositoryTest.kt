package com.example.shopify.features.authentication.registration.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.shopify.MainDispatcherRule
import com.example.shopify.core.common.data.model.CustomerId
import com.example.shopify.core.common.data.model.CustomerRegistration
import com.example.shopify.core.common.data.model.CustomerRegistrationInfo
import com.example.shopify.core.common.data.model.DiscountRegistration
import com.example.shopify.core.common.data.model.DraftOrderBody
import com.example.shopify.core.common.data.model.DraftOrderRegistration
import com.example.shopify.core.common.data.model.LineItemRegistration
import com.example.shopify.features.authentication.registeration.data.remote.FakeCreationDraftOrderRemoteSource
import com.example.shopify.features.authentication.registeration.data.remote.FakeRegistrationRemoteSource
import com.example.shopify.features.authentication.registration.data.remote.ICreationDraftOrderRemoteSource
import com.example.shopify.features.authentication.registration.data.remote.IRegistrationRemoteSource
import com.stripe.android.model.Customer
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.hamcrest.CoreMatchers
import org.hamcrest.CoreMatchers.notNullValue
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class RegistrationRepositoryTest{
    private lateinit var registrationRepository: IRegistrationRepository
    private lateinit var fakeCreationRemoteSource: ICreationDraftOrderRemoteSource
    private lateinit var fakeIRegistrationRemoteSource: IRegistrationRemoteSource

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val mainCoroutineDispatcher = MainDispatcherRule()

    @Before
    fun setup(){
        fakeIRegistrationRemoteSource = FakeRegistrationRemoteSource()
        fakeCreationRemoteSource = FakeCreationDraftOrderRemoteSource()
        registrationRepository = RegistrationRepository(fakeIRegistrationRemoteSource,fakeCreationRemoteSource)
    }

    @Test
    fun registerCustomer_givenCustomerInfo_returnCustomerResponse(){
        mainCoroutineDispatcher.runBlockingTest {
            val result = registrationRepository.registerCustomer(
                CustomerRegistration(
                    CustomerRegistrationInfo(
                        "ahmed",
                        email = "ahmed",
                        password = "dafsdf",
                        passwordConfirmation = "dafsdf"
                    )
                )
            )

            assertThat(result, notNullValue())
        }
    }

    @Test
    fun createDraft_givenDraftRegistration_returnDraftResponse() {
        val lineItems = listOf(
            LineItemRegistration(
                title = "Product 1",
                price = "10.00",
                quantity = 2
            ),
            LineItemRegistration(
                title = "Product 2",
                price = "15.00",
                quantity = 1
            )
        )

        val appliedDiscount = DiscountRegistration(
            description = "Discount Description",
            valueType = "Fixed",
            value = "5.00",
            amount = "5.00",
            title = "Discount Title"
        )

        val customerId = CustomerId(1234)

        val draftOrderBody = DraftOrderBody(
            lineItems = lineItems,
            appliedDiscount = appliedDiscount,
            customer = customerId,
            useCustomerDefaultAddress = true
        )



        mainCoroutineDispatcher.runBlockingTest {
            val result = registrationRepository.createDraftOrder(DraftOrderRegistration(draftOrderBody))

            assertThat(result, notNullValue())

        }
    }
}