package com.nguyen.tekotest.data.repository

import android.util.Log
import com.nguyen.tekotest.data.remote.network.ListProductService
import com.nguyen.tekotest.data.remote.request.ListProductRequest
import com.nguyen.tekotest.data.remote.response.ListProductResponse

class ListProductRepository constructor(
    private val listProductService: ListProductService
) : BaseRepository() {
    suspend fun getListProduct(request: ListProductRequest) : ListProductResponse? {
        return safeApiCall(
        call = {
            Log.e("AAAAABBBBB", "--->call api get list 3333")
            listProductService.getListProductAsync(request.channel,
            request.visitorId, request.query, request.terminal, request.page, request.limit).await()},
        errorMessage = "Error Fetching List Products")
    }
}