package com.nguyen.tekotest.data.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class DetailProductRepository constructor(): Repository {

    override var isLoading: Boolean = false

    suspend fun getProductDetail(productSku: String) = withContext(Dispatchers.IO) {

    }

}