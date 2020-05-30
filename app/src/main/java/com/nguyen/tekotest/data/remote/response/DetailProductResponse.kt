package com.nguyen.tekotest.data.remote.response

import com.google.gson.annotations.SerializedName

data class ProductDetailResponse(
    @SerializedName("code") val code: String? = null,
    @SerializedName("error_msg") val errorMsg: String? = null,
    @SerializedName("result") val result: ProductDetailResult? = null
)

data class ProductDetailResult(
    @SerializedName("product") val product: Product? = null
)