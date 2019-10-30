package net.mobiquity.core.data.local

import net.mobiquity.modules.category.entities.CategoriesResponse
import net.mobiquity.modules.product.domain.Product

interface LocalDataSource {
    fun saveProducts(list: List<CategoriesResponse>)
    fun getProductsByCategoryId(categoryId: String): List<Product>
}
