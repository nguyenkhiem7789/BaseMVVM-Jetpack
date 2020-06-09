package com.nguyen.tekotest.data.repository

import com.nguyen.tekotest.data.remote.network.ListProductService
import com.nguyen.tekotest.data.remote.request.ListProductRequest
import com.nguyen.tekotest.data.remote.response.ListProductResponse

class ListProductRepository constructor(
    private val listProductService: ListProductService
) : BaseRepository() {
    suspend fun getListProduct(request: ListProductRequest) : ListProductResponse? {
        return safeApiCall(
        call = {
            listProductService.getListProductAsync(request.channel,
            request.visitorId, request.query, request.terminal, request.page, request.limit).await()},
        errorMessage = "Error Fetching List Products")
    }
}