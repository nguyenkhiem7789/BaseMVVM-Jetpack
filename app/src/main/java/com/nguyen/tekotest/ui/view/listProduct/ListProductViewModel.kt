package com.nguyen.tekotest.ui.view.listProduct

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.nguyen.tekotest.data.datasource.ProductDataFactory
import com.nguyen.tekotest.data.remote.network.NetworkState
import com.nguyen.tekotest.data.remote.request.ListProductRequest
import com.nguyen.tekotest.data.remote.response.Product
import com.nguyen.tekotest.data.repository.ListProductRepository
import kotlinx.coroutines.*
import java.util.concurrent.Executor
import java.util.concurrent.Executors
import kotlin.coroutines.CoroutineContext

class ListProductViewModel(private val repository: ListProductRepository) : ViewModel() {

    private val parentJob = Job()

    private val coroutineContext: CoroutineContext
        get() = parentJob + Dispatchers.Default

    private val scope = CoroutineScope(coroutineContext)

    private val executor: Executor by lazy {
        Executors.newFixedThreadPool(5)
    }

    var requestSearch = MutableLiveData<ListProductRequest>()

    var networkState: LiveData<NetworkState>? = null

    var arrayProductLiveData: LiveData<PagedList<Product>>? = null

    init {
        arrayProductLiveData = Transformations.switchMap(requestSearch) {
            val productDataFactory = ProductDataFactory(repository, scope, it)
            networkState = Transformations.switchMap(productDataFactory.mutableLiveData) {
                it.networkState
            }
            val pagedListConfig = PagedList.Config.Builder()
                .setEnablePlaceholders(false)
                .setInitialLoadSizeHint(10)
                .setPageSize(20)
                .build()
            return@switchMap LivePagedListBuilder(productDataFactory, pagedListConfig)
                .setFetchExecutor(executor)
                .build()
        }
    }

    fun cancelAllRequests() = coroutineContext.cancel()
}