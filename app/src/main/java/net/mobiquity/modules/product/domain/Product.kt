package net.mobiquity.modules.product.domain

data class Product(
    val categoryId: String,
    val description: String,
    val id: String,
    val name: String,
    val url: String?,
    val amount: String,
    val currency: String
)