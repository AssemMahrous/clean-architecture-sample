package net.mobiquity.modules.product.entities

import com.google.gson.annotations.SerializedName

data class ProductRemoteEntity(
    @SerializedName("id") val id: String = "",
    @SerializedName("categoryId") val categoryId: String = "",
    @SerializedName("name") val name: String = "",
    @SerializedName("url") val url: String = "",
    @SerializedName("description") val description: String = "",
    @SerializedName("salePrice") val salePrice: SalePriceRemoteEntity = SalePriceRemoteEntity()
)