package net.mobiquity.feature.home

import androidx.lifecycle.MutableLiveData
import io.reactivex.functions.Consumer
import net.mobiquity.core.platform.BaseViewModel
import net.mobiquity.core.utils.Event
import net.mobiquity.modules.category.entities.CategoryView
import net.mobiquity.modules.category.usecase.GetCategoriesListUseCase
import net.mobiquity.modules.product.entities.ProductView
import net.mobiquity.modules.product.usecase.GetProductListByCategoryIdUseCase
import javax.inject.Inject
import net.mobiquity.modules.category.Mapper as categoryMapper
import net.mobiquity.modules.product.Mapper as productMapper

class HomeViewModel @Inject constructor(
    val categoriesListUseCase: GetCategoriesListUseCase,
    val getProductListByCategoryIdUseCase: GetProductListByCategoryIdUseCase
) : BaseViewModel() {

    val categories = MutableLiveData<Event<List<CategoryView>>>()
    val products = MutableLiveData<Event<List<ProductView>>>()

    fun getCategories() {
        singleNetworkCall(
            categoriesListUseCase.invoke(),
            Consumer { categories.postValue(categoryMapper.mapToCategoriesView(it)) },
            showLoading = true
        )
    }

    fun getProducts(categoryId: String) {
        singleNetworkCall(
            getProductListByCategoryIdUseCase.invoke(categoryId),
            Consumer { products.postValue(productMapper.mapToProductViewList(it)) },
            showLoading = false
        )
    }
}
