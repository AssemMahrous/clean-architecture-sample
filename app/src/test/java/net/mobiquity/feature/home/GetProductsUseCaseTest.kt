package net.mobiquity.feature.home

import io.reactivex.Single
import net.mobiquity.modules.product.data.ProductRepositoryInterface
import net.mobiquity.modules.product.domain.Product
import net.mobiquity.modules.product.usecase.GetProductListByCategoryIdUseCase
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class GetProductsUseCaseTest {

    @Mock
    lateinit var productRepository: ProductRepositoryInterface

    lateinit var getProductsUseCase: GetProductListByCategoryIdUseCase

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        getProductsUseCase = GetProductListByCategoryIdUseCase(productRepository)
    }

    @Test
    fun getCategories() {
        Mockito.`when`(productRepository.getProductsByCategoryId("1"))
            .thenReturn(Single.just(ArrayList<Product>()))

        getProductsUseCase.invoke("1")
            .test()
            .assertNoErrors()
            .assertComplete()
    }
}