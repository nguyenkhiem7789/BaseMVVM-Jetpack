package com.nguyen.tekotest.data.remote.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.nguyen.tekotest.data.model.GenericInterface
import kotlinx.android.parcel.Parcelize

data class ListProductResponse(
    @SerializedName("code") val code: String? = null,
    @SerializedName("error_msg") val errorMsg: String? = null,
    @SerializedName("result") val result: ProductResult? = null
)

data class ProductResult(
    @SerializedName("next") val page: Long? = null,
    @SerializedName("products") val arrayProduct: ArrayList<Product>? = null
)

@Parcelize
data class Product (
    @SerializedName("sku") override var id: String,
    @SerializedName("name") val name: String? = null,
    @SerializedName("price") val price: ProductPrice? = null,
    @SerializedName("images") val arrayImage: ArrayList<ProductImage>? = null
) : GenericInterface(), Parcelable {
    @Parcelize
    data class ProductPrice (
        @SerializedName("supplierSalePrice") val supplierSalePrice: Double? = null,
        @SerializedName("sellPrice") val sellPrice: Double? = null
    ) : Parcelable

    @Parcelize
    data class ProductImage(
        @SerializedName("url") val url: String? = null
    ) : Parcelable
}