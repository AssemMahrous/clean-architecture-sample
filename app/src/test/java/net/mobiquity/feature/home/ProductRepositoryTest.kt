package net.mobiquity.feature.home

import io.reactivex.Single
import net.mobiquity.core.data.remote.RemoteDataSource
import net.mobiquity.core.data.remote.requests.ApiRequests
import net.mobiquity.data.source.TestLocalDataSource
import net.mobiquity.modules.category.entities.CategoriesResponse
import net.mobiquity.modules.product.data.ProductRepository
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
    private lateinit var productRepository: ProductRepository

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        localDataSource = TestLocalDataSource()
        productRepository = ProductRepository(remoteDataSource, localDataSource)
    }

    @Test
    fun `check products cached`() {
        `when`(remoteDataSource.apiRequests)
            .thenReturn(mock(ApiRequests::class.java))

        `when`(remoteDataSource.apiRequests.getCategories())
            .thenReturn(Single.just(ArrayList<CategoriesResponse>()))

        productRepository
            .getProductsByCategoryId("1")
            .test()
            .assertNoErrors()

        Assert.assertEquals(
            ArrayList<CategoriesResponse>(),
            localDataSource.getProductsByCategoryId("1")
        )
    }
}