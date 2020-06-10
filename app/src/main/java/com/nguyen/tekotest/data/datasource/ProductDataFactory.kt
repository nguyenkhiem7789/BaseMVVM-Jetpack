package com.nguyen.tekotest.data.datasource

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.nguyen.tekotest.data.remote.request.ListProductRequest
import com.nguyen.tekotest.data.remote.response.Product
import com.nguyen.tekotest.data.repository.ListProductRepository
import kotlinx.coroutines.CoroutineScope

class ProductDataFactory(
    private val repository: ListProductRepository,
    private val scope: CoroutineScope,
    private val request: ListProductRequest
) : DataSource.Factory<Long, Product>() {

    val mutableLiveData: MutableLiveData<ProductDataSource> by lazy {
        MutableLiveData<ProductDataSource>()
    }

    private val productDataSource: ProductDataSource by lazy {
        ProductDataSource(repository, scope, request)
    }

    override fun create(): DataSource<Long, Product> {
        mutableLiveData.postValue(productDataSource)
        return productDataSource
    }

}