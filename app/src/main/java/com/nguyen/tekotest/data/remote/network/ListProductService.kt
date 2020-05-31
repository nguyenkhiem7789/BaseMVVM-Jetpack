package com.nguyen.tekotest.data.remote.network

import com.nguyen.tekotest.data.remote.response.ListProductResponse
import com.nguyen.tekotest.data.remote.response.Product
import kotlinx.coroutines.Deferred
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ListProductService {

    @GET("search/")
    fun getListProductAsync(
        @Query("channel") channel: String?,
        @Query("visitorId") visitorId: String?,
        @Query("q") query: String?,
        @Query("terminal") terminal: String?,
        @Query("_page") page: Int?,
        @Query("_limit") limit: Int?
    ): Deferred<ListProductResponse>

}