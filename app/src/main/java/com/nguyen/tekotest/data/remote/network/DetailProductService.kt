package com.nguyen.tekotest.data.remote.network

import com.nguyen.tekotest.data.remote.response.Product
import com.nguyen.tekotest.data.remote.response.ProductDetailResponse
import kotlinx.coroutines.Deferred
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface DetailProductService {

    @GET("products/{sku}")
    fun getDetailProduct(
//        @Path("sku") productSku: String?,
        @Query("channel") channel: String?,
        @Query("terminal") terminal: String?
    ): Deferred<Response<ProductDetailResponse>>

}