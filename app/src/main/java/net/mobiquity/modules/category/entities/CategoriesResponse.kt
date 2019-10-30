package net.mobiquity.modules.category.entities

import com.google.gson.annotations.SerializedName
import net.mobiquity.modules.product.entities.ProductRemoteEntity

data class CategoriesResponse(
    @SerializedName("id") val id: String = "",
    @SerializedName("name") val name: String = "",
    @SerializedName("description") val description: String = "",
    @SerializedName("products") val products: List<ProductRemoteEntity> = listOf()
)

