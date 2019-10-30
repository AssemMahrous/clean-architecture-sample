package net.mobiquity.modules.product.data

import io.reactivex.Single
import net.mobiquity.modules.product.domain.Product

interface ProductRepositoryInterface {

    fun getProductsByCategoryId(categoryId: String): Single<List<Product>>
}