package net.mobiquity.feature.home

import io.reactivex.Single
import net.mobiquity.modules.category.data.CategoryRepositoryInterface
import net.mobiquity.modules.category.domain.Category
import net.mobiquity.modules.category.usecase.GetCategoriesListUseCase
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class GetCategoriesUseCaseTest {

    @Mock
    lateinit var categoryRepository: CategoryRepositoryInterface

    lateinit var getCategoriesUseCase: GetCategoriesListUseCase

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        getCategoriesUseCase = GetCategoriesListUseCase(categoryRepository)
    }

    @Test
    fun getCategories() {
        Mockito.`when`(categoryRepository.getCategories())
            .thenReturn(Single.just(ArrayList<Category>()))

        getCategoriesUseCase.invoke()
            .test()
            .assertNoErrors()
            .assertComplete()
    }
}