package net.mobiquity.data.source

import net.mobiquity.core.data.local.LocalDataSource
import net.mobiquity.modules.category.entities.CategoriesResponse
import net.mobiquity.modules.product.Mapper
import net.mobiquity.modules.product.domain.Product

class TestLocalDataSource : LocalDataSource {
    private val categoryProductsMap = hashMapOf<String, List<Product>>()

    override fun saveProducts(list: List<CategoriesResponse>) {
        list.forEach { categoryProductsMap[it.id] = Mapper.mapToProductList(it.products) }
    }

    override fun getProductsByCategoryId(categoryId: String): List<Product> {
        return categoryProductsMap[categoryId] ?: emptyList()
    }

    fun getCategoryProductsMap(): HashMap<String, List<Product>> {
        return categoryProductsMap
    }
}