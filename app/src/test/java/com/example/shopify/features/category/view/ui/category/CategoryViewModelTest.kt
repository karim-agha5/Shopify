package com.example.shopify.features.category.view.ui.category

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.shopify.MainDispatcherRule
import com.example.shopify.core.common.data.model.CustomCollection
import com.example.shopify.core.util.ApiState
import com.example.shopify.features.category.model.fakeCustomCollectionList
import com.example.shopify.features.category.repository.FakeCategoryRepository
import com.example.shopify.features.category.repository.ICategoryRepository
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
class CategoryViewModelTest{

    private lateinit var categoryViewModel : CategoryViewModel
    private lateinit var fakeCategoryRepository : ICategoryRepository

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val mainCoroutineDispatcher = MainDispatcherRule()

    @Before
    fun setup(){
        fakeCategoryRepository = FakeCategoryRepository()
        categoryViewModel = CategoryViewModel(fakeCategoryRepository)
    }

    @Test
    fun getStateFlowProducts_callViewModelMethod_returnCategoriesList() = mainCoroutineDispatcher.runBlockingTest {
        //when -> call the method in the view model
        categoryViewModel.getStateFlowProducts()

        val apiState = categoryViewModel.mainCategories.getOrAwaitValue {  } as ApiState.Success<*>
        val result = apiState.myData as List<CustomCollection>

        //then should return list of brands
        //assert the list not null
        assertThat(result, notNullValue())
        //assert the list not empty
        assertThat(result, not(emptyList()))
    }

    @Test
    fun getStateFlowProducts_callViewModelMethod_returnValidCategoriesList() = mainCoroutineDispatcher.runBlockingTest{

        //when call the method in the view model
        categoryViewModel.getStateFlowProducts()

        val apiState = categoryViewModel.mainCategories.getOrAwaitValue {  } as ApiState.Success<*>
        val result = apiState.myData as List<CustomCollection>

        //then -> should return valid list (my mock categories list)
        //assert it's the same list
        assertThat(result, `is`(fakeCustomCollectionList))
        //assert it's the same data
        assertThat(result[0].id, `is`(fakeCustomCollectionList[0].id))
        assertThat(result[0].title, `is`(fakeCustomCollectionList[0].title))
    }

    @Test
    fun getStateFlowProducts_callViewModelMethod_stateChangeFromLoadingToSuccess () = mainCoroutineDispatcher.runBlockingTest{
        //pause the thread
        mainCoroutineDispatcher.pauseDispatcher()
        //assert that ApiState is loading
        assertThat(categoryViewModel.mainCategories.value, `is`(ApiState.Loading))
        //resume the thread
        mainCoroutineDispatcher.resumeDispatcher()
        //when call the method in the view model
        categoryViewModel.getStateFlowProducts()
        //then the state should change to success
        val apiState = categoryViewModel.mainCategories.getOrAwaitValue {  }
        apiState as ApiState.Success<CustomCollection>
        //assert that the state is success and data is retrieved
        assertThat(apiState.myData, `is`(ApiState.Success(fakeCustomCollectionList).myData))
    }

}