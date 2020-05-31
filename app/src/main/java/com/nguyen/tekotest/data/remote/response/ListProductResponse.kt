package com.nguyen.tekotest.data.remote.response

import com.google.gson.annotations.SerializedName

data class ListProductResponse(
    @SerializedName("code") val code: String? = null,
    @SerializedName("error_msg") val errorMsg: String? = null,
    @SerializedName("result") val result: ProuductResult? = null
)

data class ProuductResult(
    @SerializedName("products") val arrayProduct: ArrayList<Product>? = null
)

data class Product(
    @SerializedName("sku") val sku: String? = null,
    @SerializedName("name") val name: String? = null,
    @SerializedName("price") val price: ProductPrice? = null,
    @SerializedName("images") val arrayImage: ArrayList<ProductImage>? = null
)

data class ProductImage(
    @SerializedName("url") val url: String? = null
)

data class ProductPrice(
    @SerializedName("supplierSalePrice") val supplierSalePrice: Double? = null,
    @SerializedName("sellPrice") val sellPrice: Double? = null
)