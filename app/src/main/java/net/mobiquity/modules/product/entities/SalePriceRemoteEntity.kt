package net.mobiquity.modules.product.entities

import com.google.gson.annotations.SerializedName

data class SalePriceRemoteEntity(
    @SerializedName("amount") val amount: String = "",
    @SerializedName("currency") val currency: String = ""
)