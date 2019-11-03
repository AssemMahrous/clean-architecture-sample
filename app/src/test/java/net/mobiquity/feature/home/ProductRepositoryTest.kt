package net.mobiquity.feature.home

import io.reactivex.Single
import net.mobiquity.core.data.remote.RemoteDataSource
import net.mobiquity.core.data.remote.requests.ApiRequests
import net.mobiquity.data.source.TestLocalDataSource
import net.mobiquity.feature.home.FakeData.getCategoriesResponse
import net.mobiquity.modules.category.data.CategoryRepository
import net.mobiquity.modules.category.data.CategoryRepositoryInterface
import net.mobiquity.modules.product.data.ProductRepository
import net.mobiquity.modules.product.data.ProductRepositoryInterface
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import org.mockito.MockitoAnnotations

class ProductRepositoryTest {
    @Mock
    private lateinit var remoteDataSource: RemoteDataSource
    private lateinit var localDataSource: TestLocalDataSource

    private lateinit var productRepository: ProductRepositoryInterface
    private lateinit var categoryRepository: CategoryRepositoryInterface

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        localDataSource = TestLocalDataSource()
        productRepository = ProductRepository(remoteDataSource, localDataSource)
        categoryRepository = CategoryRepository(remoteDataSource, localDataSource)
    }

    @Test
    fun `check categories cached`() {
        getMockCategories()

        Assert.assertTrue(localDataSource.getCategoryProductsMap().isNotEmpty())
        Assert.assertTrue(localDataSource.getCategoryProductsMap()["1"]?.isNotEmpty() ?: false)
    }

    @Test
    fun `check get products by category id`() {
        getMockCategories()

        productRepository
            .getProductsByCategoryId("1")
            .test()
            .assertNoErrors()
            .assertValueCount(1)
            .assertValue { it.first().categoryId == "1" }
    }

    private fun getMockCategories() {
        `when`(remoteDataSource.apiRequests)
            .thenReturn(mock(ApiRequests::class.java))

        `when`(remoteDataSource.apiRequests.getCategories())
            .thenReturn(Single.just(getCategoriesResponse()))

        categoryRepository.getCategories()
            .test()
            .assertNoErrors()
    }
}