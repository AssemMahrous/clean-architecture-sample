package net.mobiquity.feature.home

import android.os.Build
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import net.mobiquity.MyTestApplication
import net.mobiquity.getOrAwaitValue
import net.mobiquity.modules.category.usecase.GetCategoriesListUseCase
import net.mobiquity.modules.product.usecase.GetProductListByCategoryIdUseCase
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.robolectric.annotation.Config
import org.mockito.Mockito.mock


@Config(sdk = [Build.VERSION_CODES.O_MR1], application = MyTestApplication::class)
@RunWith(AndroidJUnit4::class)
class HomeViewModelTest {
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()
    // Subject under test
    private lateinit var homeViewModel: HomeViewModel

    @Before
    fun setupViewModel() {
        val getCategoriesListUseCase = mock(GetCategoriesListUseCase::class.java)
        val getProductListByCategoryIdUseCase =
            mock(GetProductListByCategoryIdUseCase::class.java)
        homeViewModel = HomeViewModel(getCategoriesListUseCase, getProductListByCategoryIdUseCase)
    }

    @Test
    fun fetchCategories_setsCategoriesValue() {
        homeViewModel.getCategories()
        val value = homeViewModel.categories.getOrAwaitValue()
        MatcherAssert.assertThat(
            value.getContentIfNotHandled(),
            (CoreMatchers.not(CoreMatchers.nullValue()))
        )
    }

    @Test
    fun fetchproducts_setsProdcutValue() {
        homeViewModel.getProducts("1")
        val value = homeViewModel.products.getOrAwaitValue()
        MatcherAssert.assertThat(
            value.getContentIfNotHandled(),
            (CoreMatchers.not(CoreMatchers.nullValue()))
        )
    }
}