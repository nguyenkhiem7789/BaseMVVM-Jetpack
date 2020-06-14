package com.nguyen.tekotest.ui.view.listProduct

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.nguyen.tekotest.data.datasource.ProductDataFactory
import com.nguyen.tekotest.data.datasource.ProductDataSource
import com.nguyen.tekotest.data.remote.request.ListProductRequest
import com.nguyen.tekotest.data.remote.response.Product
import com.nguyen.tekotest.data.repository.ListProductRepository
import com.nguyen.tekotest.ui.base.BaseViewModel

class ListProductViewModel(private val repository: ListProductRepository) : BaseViewModel() {

    var requestSearch = MutableLiveData<ListProductRequest>()

    var arrayProductLiveData: LiveData<PagedList<Product>>? = null

    var productDataFactory = ProductDataFactory(repository, scope)

    init {
        if(productDataFactory.mutableLiveData != null) {
            networkState = Transformations.switchMap(productDataFactory.mutableLiveData!!) {
                it.networkState
            }
        }

        arrayProductLiveData = Transformations.switchMap(requestSearch) {
            productDataFactory.request = it
            val pagedListConfig = PagedList.Config.Builder()
                .setEnablePlaceholders(false)
                .setInitialLoadSizeHint(10)
                .setPageSize(20)
                .build()
            LivePagedListBuilder(productDataFactory, pagedListConfig)
                .setFetchExecutor(executor)
                .build()
        }
    }
}