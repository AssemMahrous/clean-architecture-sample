package net.mobiquity.feature.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import io.reactivex.Single
import net.mobiquity.TestLocalScheduler
import net.mobiquity.core.data.remote.RemoteDataSource
import net.mobiquity.core.data.remote.requests.ApiRequests
import net.mobiquity.core.utils.IConnectivityUtils
import net.mobiquity.data.source.TestLocalDataSource
import net.mobiquity.feature.home.FakeData.getCategoriesResponse
import net.mobiquity.getOrAwaitValue
import net.mobiquity.modules.category.data.CategoryRepository
import net.mobiquity.modules.category.data.CategoryRepositoryInterface
import net.mobiquity.modules.category.usecase.GetCategoriesListUseCase
import net.mobiquity.modules.product.data.ProductRepository
import net.mobiquity.modules.product.data.ProductRepositoryInterface
import net.mobiquity.modules.product.usecase.GetProductListByCategoryIdUseCase
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

//@Config(sdk = [Build.VERSION_CODES.O_MR1], application = MyTestApplication::class)
//@RunWith(AndroidJUnit4::class)
class HomeViewModelTest {
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    // Subject under test
    private lateinit var homeViewModel: HomeViewModel

    private lateinit var getCategoriesListUseCase: GetCategoriesListUseCase
    private lateinit var getProductListByCategoryIdUseCase: GetProductListByCategoryIdUseCase

    @Mock
    private lateinit var connectivityUtils: IConnectivityUtils

    @Mock
    private lateinit var remoteDataSource: RemoteDataSource

    private lateinit var localDataSource: TestLocalDataSource

    private lateinit var productRepository: ProductRepositoryInterface
    private lateinit var categoryRepository: CategoryRepositoryInterface

    @Before
    fun setupViewModel() {
        MockitoAnnotations.initMocks(this)

        localDataSource = TestLocalDataSource()

        productRepository = ProductRepository(remoteDataSource, localDataSource)
        categoryRepository = CategoryRepository(remoteDataSource, localDataSource)

        getCategoriesListUseCase = GetCategoriesListUseCase(categoryRepository)
        getProductListByCategoryIdUseCase = GetProductListByCategoryIdUseCase(productRepository)

        homeViewModel = HomeViewModel(getCategoriesListUseCase, getProductListByCategoryIdUseCase)
        homeViewModel.connectivityUtils = connectivityUtils
        homeViewModel.localScheduler = TestLocalScheduler()

        Mockito.`when`(connectivityUtils.isNetworkConnected).thenReturn(true)

        Mockito.`when`(remoteDataSource.apiRequests)
            .thenReturn(Mockito.mock(ApiRequests::class.java))

        Mockito.`when`(remoteDataSource.apiRequests.getCategories())
            .thenReturn(Single.just(getCategoriesResponse()))
    }

    @Test
    fun `fetch all categories test`() {
        homeViewModel.getCategories()

        val value = homeViewModel.categories.getOrAwaitValue()

        val list = value.getContentIfNotHandled()

        Assert.assertNotNull(list)
        Assert.assertFalse(list!!.isEmpty())
        Assert.assertEquals(list.first().id, "1")
    }

    @Test
    fun `fetch products by category id`() {
        homeViewModel.getCategories()

        homeViewModel.getProducts("1")

        val value = homeViewModel.products.getOrAwaitValue()

        val list = value.getContentIfNotHandled()

        Assert.assertNotNull(list)
        Assert.assertFalse(list!!.isEmpty())
        Assert.assertEquals(list.first().name, "test1")
    }
}