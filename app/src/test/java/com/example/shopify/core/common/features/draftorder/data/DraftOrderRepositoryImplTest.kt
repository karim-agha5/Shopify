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
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class DraftOrderRepositoryImplTest{
    private lateinit var draftRepo: IDraftOrderRepository
    private lateinit var fakeDraftOrderRemoteSourceImpl: IDraftOrderRemoteSource

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

}