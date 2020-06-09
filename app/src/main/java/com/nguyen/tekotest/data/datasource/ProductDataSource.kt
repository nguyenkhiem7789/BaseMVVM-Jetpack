package com.nguyen.tekotest.data.datasource

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.nguyen.tekotest.data.remote.network.NetworkState
import com.nguyen.tekotest.data.remote.request.ListProductRequest
import com.nguyen.tekotest.data.remote.response.Product
import com.nguyen.tekotest.data.repository.ListProductRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class ProductDataSource(
    private val repository: ListProductRepository,
    private val scope: CoroutineScope,
    private val request: ListProductRequest
) : PageKeyedDataSource<Long, Product>() {

    companion object {
        private const val TAG = "ProductDataSource"
    }

    val networkState: MutableLiveData<NetworkState> by lazy {
        MutableLiveData<NetworkState>()
    }

    override fun loadInitial(params: LoadInitialParams<Long>, callback: LoadInitialCallback<Long, Product>) {
        scope.launch {
            networkState.postValue(NetworkState.LOADING)
            val listProductResponse = repository.getListProduct(request)
            val arrayProduct = listProductResponse?.result?.arrayProduct
            if(arrayProduct != null) {
                Log.e("XarrayProduct", "=> 1")
            } else {
                Log.e("XarrayProduct", "=> 2")
            }
            var message = listProductResponse?.errorMsg
            if(arrayProduct != null) {
                callback.onResult(arrayProduct, null, 2)
                networkState.postValue(NetworkState.LOADED)
            } else if(message != null) {
                networkState.postValue(NetworkState(NetworkState.Status.FAILED, message))
            } else {
                networkState.postValue(NetworkState(NetworkState.Status.FAILED))
            }
        }
    }

    override fun loadAfter(params: LoadParams<Long>, callback: LoadCallback<Long, Product>) {
        scope.launch {
            networkState.postValue(NetworkState.LOADING)
            val listProductResponse = repository.getListProduct(request)
            val arrayProduct = listProductResponse?.result?.arrayProduct
            var message = listProductResponse?.errorMsg
            var page = listProductResponse?.result?.page
            if(arrayProduct != null && page != null) {
                callback.onResult(arrayProduct, page)
                networkState.postValue(NetworkState.LOADED)
            } else if(message != null) {
                networkState.postValue(NetworkState(NetworkState.Status.FAILED, message))
            } else {
                networkState.postValue(NetworkState(NetworkState.Status.FAILED))
            }
        }
    }

    override fun loadBefore(params: LoadParams<Long>, callback: LoadCallback<Long, Product>) {
    }

}