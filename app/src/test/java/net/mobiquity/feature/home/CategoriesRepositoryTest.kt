package net.mobiquity.feature.home

import io.reactivex.Single
import net.mobiquity.core.data.remote.RemoteDataSource
import net.mobiquity.core.data.remote.requests.ApiRequests
import net.mobiquity.data.source.TestLocalDataSource
import net.mobiquity.modules.category.data.CategoryRepository
import net.mobiquity.modules.category.entities.CategoriesResponse
import net.mobiquity.modules.product.domain.Product
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class CategoriesRepositoryTest {
    @Mock
    private lateinit var remoteDataSource: RemoteDataSource

    private lateinit var localDataSource: TestLocalDataSource
    private lateinit var categoryRepository: CategoryRepository

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        localDataSource = TestLocalDataSource()
        categoryRepository = CategoryRepository(remoteDataSource, localDataSource)
    }

    @Test
    fun `check categories fetched`() {
        Mockito.`when`(remoteDataSource.apiRequests)
            .thenReturn(Mockito.mock(ApiRequests::class.java))

        Mockito.`when`(remoteDataSource.apiRequests.getCategories())
            .thenReturn(Single.just(ArrayList<CategoriesResponse>()))

        categoryRepository
            .getCategories()
            .test()
            .assertNoErrors()

        Assert.assertEquals(
            HashMap<String, List<Product>>(),
            localDataSource.getCategoryProductsMap()
        )
    }
}