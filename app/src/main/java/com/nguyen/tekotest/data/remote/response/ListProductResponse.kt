package com.nguyen.tekotest.data.remote.response

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.recyclerview.widget.DiffUtil
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

data class ListProductResponse(
    @SerializedName("code") val code: String? = null,
    @SerializedName("error_msg") val errorMsg: String? = null,
    @SerializedName("result") val result: ProuductResult? = null
)

data class ProuductResult(
    @SerializedName("next") val page: Long? = null,
    @SerializedName("products") val arrayProduct: ArrayList<Product>? = null
)

@Parcelize
data class Product(
    @SerializedName("sku") val id: String? = null,
    @SerializedName("name") val name: String? = null,
    @SerializedName("price") val price: ProductPrice? = null,
    @SerializedName("images") val arrayImage: ArrayList<ProductImage>? = null
) : Parcelable {
    companion object {
        var DIFF_CALLBACK: DiffUtil.ItemCallback<Product?> = object : DiffUtil.ItemCallback<Product?>() {
            override fun areItemsTheSame(
                @NonNull oldItem: Product,
                @NonNull newItem: Product
            ): Boolean {
                return oldItem.id == newItem.id
            }
            override fun areContentsTheSame(
                @NonNull oldItem: Product,
                @NonNull newItem: Product
            ): Boolean {
                return oldItem == newItem
            }
        }
    }

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