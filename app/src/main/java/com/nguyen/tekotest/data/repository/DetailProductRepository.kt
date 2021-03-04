package com.nguyen.tekotest.data.repository

import android.util.Log
import com.nguyen.tekotest.data.remote.network.DetailProductService
import com.nguyen.tekotest.data.remote.request.DetailProductRequest
import com.nguyen.tekotest.data.remote.response.ProductDetailResponse

class DetailProductRepository constructor(
    private val detailProductService: DetailProductService
): BaseRepository() {

    suspend fun getProductDetail(request: DetailProductRequest) : ProductDetailResponse? {
        return safeApiCall(
            call = {
                Log.e("AAAAABBBBB", "--->call api get detail 2222")
                detailProductService.getDetailProductAsync(request.channel,
                    request.terminal).await()},
            errorMessage = "Error Fetching List Products")
    }

}