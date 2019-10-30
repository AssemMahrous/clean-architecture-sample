package net.mobiquity.modules.product

import net.mobiquity.BuildConfig
import net.mobiquity.modules.product.domain.Product
import net.mobiquity.modules.product.entities.ProductRemoteEntity
import net.mobiquity.modules.product.entities.ProductView

object Mapper {
    fun mapToProductList(list: List<ProductRemoteEntity>): List<Product> =
        list.map { mapToProduct(it) }

    fun mapToProduct(productRemoteEntity: ProductRemoteEntity): Product = Product(
        productRemoteEntity.categoryId,
        productRemoteEntity.description,
        productRemoteEntity.id,
        productRemoteEntity.name,
        productRemoteEntity.url,
        productRemoteEntity.salePrice.amount,
        productRemoteEntity.salePrice.currency
    )

    fun mapToProductViewList(list: List<Product>): List<ProductView> {
        return list.map { mapToProductView(it) }
    }

    private fun mapToProductView(product: Product): ProductView =
        ProductView(
            product.id,
            product.name,
            product.description,
            product.categoryId,
            product.url?.let { BuildConfig.SERVER_URL + product.url },
            "${product.amount} ${product.currency}"
        )
}