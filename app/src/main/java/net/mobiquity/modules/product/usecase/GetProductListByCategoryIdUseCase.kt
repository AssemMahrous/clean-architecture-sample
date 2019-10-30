package net.mobiquity.modules.product.usecase

import io.reactivex.Single
import net.mobiquity.modules.product.data.ProductRepositoryInterface
import net.mobiquity.modules.product.domain.Product
import javax.inject.Inject

class GetProductListByCategoryIdUseCase @Inject constructor(val repository: ProductRepositoryInterface) {

    operator fun invoke(categoryId: String): Single<List<Product>> {
        return repository.getProductsByCategoryId(categoryId)
    }
}