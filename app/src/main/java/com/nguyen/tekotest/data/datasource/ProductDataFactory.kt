package com.nguyen.tekotest.data.datasource

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.nguyen.tekotest.data.remote.request.ListProductRequest
import com.nguyen.tekotest.data.remote.response.Product
import com.nguyen.tekotest.data.repository.ListProductRepository
import kotlinx.coroutines.CoroutineScope

class ProductDataFactory(
    private val repository: ListProductRepository,
    private val scope: CoroutineScope
) : DataSource.Factory<Long, Product>() {

    var request: ListProductRequest? = null

    var mutableLiveData: MutableLiveData<ProductDataSource>? = null

    lateinit var productDataSource: ProductDataSource

    init {
        mutableLiveData = MutableLiveData<ProductDataSource>()
    }

    override fun create(): DataSource<Long, Product> {
        productDataSource = ProductDataSource(repository, scope)
        productDataSource.request = this.request
        mutableLiveData?.postValue(productDataSource)
        return productDataSource
    }

}