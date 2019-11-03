package net.mobiquity.feature.home

import net.mobiquity.modules.category.entities.CategoriesResponse
import net.mobiquity.modules.product.entities.ProductRemoteEntity
import net.mobiquity.modules.product.entities.SalePriceRemoteEntity

object FakeData {
    fun getCategoriesResponse(): ArrayList<CategoriesResponse> {
        return arrayListOf(
            CategoriesResponse(
                "1",
                "test1",
                "",
                listOf(
                    ProductRemoteEntity(
                        "1", "1", "test1", "name",
                        "test", SalePriceRemoteEntity("10", "EUR")
                    )
                )
            ),
            CategoriesResponse(
                "2",
                "test2",
                "",
                listOf(
                    ProductRemoteEntity(
                        "1", "2", "test2", "name",
                        "test", SalePriceRemoteEntity("10", "EUR")
                    )
                )
            )
        )
    }
}