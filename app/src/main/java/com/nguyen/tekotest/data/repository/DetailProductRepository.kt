package com.nguyen.tekotest.data.repository

import com.nguyen.tekotest.data.remote.network.DetailProductService
import com.nguyen.tekotest.data.remote.request.DetailProductRequest
import com.nguyen.tekotest.data.remote.response.ProductDetailResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class DetailProductRepository constructor(
    private val detailProductService: DetailProductService
): BaseRepository() {

    suspend fun getProductDetail(request: DetailProductRequest) : ProductDetailResponse? {
        return safeApiCall(
            call = {
                detailProductService.getDetailProduct(request.channel,
                    request.terminal).await()},
            errorMessage = "Error Fetching List Products")
    }

}