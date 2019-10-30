package net.mobiquity.modules.product.entities

data class ProductView(
    val id: String,
    val name: String,
    val description: String,
    val categoryId: String,
    val imageUrl: String?,
    val price: String
)