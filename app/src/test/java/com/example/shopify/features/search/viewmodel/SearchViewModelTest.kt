import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.shopify.common.features.draftorder.model.products
import com.example.shopify.core.common.data.model.Product
import com.example.shopify.features.search.viewmodel.SearchViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class SearchViewModelTest {

    private lateinit var viewModel: SearchViewModel
    private val testDispatcher = TestCoroutineDispatcher()

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setup() {
        viewModel = SearchViewModel(products)
    }

    @Test
    fun filterProducts_shouldFilterProductsBasedOnSearchQuery() = testDispatcher.runBlockingTest {
        val searchQuery = "Vendor 1"
        val filteredListFlow = MutableStateFlow<List<Product>?>(null)
        viewModel.filteredProductsLiveData.observeForever { filteredList ->
            filteredListFlow.value = filteredList
        }

        viewModel.filterProducts(searchQuery)

        val filteredList = filteredListFlow.value
        assertEquals(1, filteredList?.size)
        assertEquals(1L, filteredList?.get(0)?.id)
    }
}
