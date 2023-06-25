package com.example.shopify.features.category.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.shopify.MainDispatcherRule
import com.example.shopify.features.category.model.fakeCustomCollectionList
import com.example.shopify.features.category.network.CategoryDataSource
import com.example.shopify.features.category.network.FakeCategoryDataSource
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runBlockingTest
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.CoreMatchers.not
import org.hamcrest.CoreMatchers.notNullValue
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class CategoryRepositoryTest{

    private lateinit var categoryRepository : ICategoryRepository
    private lateinit var fakeCategoryDataSource : CategoryDataSource

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val mainCoroutineDispatcher = MainDispatcherRule()

    @Before
    fun setup(){
        fakeCategoryDataSource = FakeCategoryDataSource()
        categoryRepository = CategoryRepository.getInstance(fakeCategoryDataSource)
    }

    @Test
    fun getMainCategories_callRepositoryMethod_returnCategoriesList() = mainCoroutineDispatcher.runBlockingTest {
        //when -> call the method in the repository
        val result = categoryRepository.getMainCategories().first()

        //then -> should return list of categories
        //asser the list not null
        assertThat(result,notNullValue())
        //assert the list not empty
        assertThat(result,not(emptyList()))
    }

    @Test
    fun getMainCategories_callRepositoryMethod_validReturnedList() = mainCoroutineDispatcher.runBlockingTest {
        //when calling the method in the repository
        val result = categoryRepository.getMainCategories().first()

        //then -> should return valid list (my mock list)
        //assert it's the same list
        assertThat(result,`is`(fakeCustomCollectionList))
        //assert it's the same data
        assertThat(result[0].id,`is`(fakeCustomCollectionList[0].id))
        assertThat(result[0].title, `is`(fakeCustomCollectionList[0].title))
    }

}