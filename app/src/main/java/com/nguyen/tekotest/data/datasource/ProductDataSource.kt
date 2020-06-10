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
            val response = repository.getListProduct(request)
            val arrayProduct = response?.result?.arrayProduct
            var message = response?.errorMsg
            when {
                arrayProduct != null -> {
                    callback.onResult(arrayProduct, null, 2)
                    networkState.postValue(NetworkState.LOADED)
                }
                message != null -> {
                    networkState.postValue(NetworkState(NetworkState.Status.FAILED, message))
                }
                else -> {
                    networkState.postValue(NetworkState(NetworkState.Status.FAILED))
                }
            }
        }
    }

    override fun loadAfter(params: LoadParams<Long>, callback: LoadCallback<Long, Product>) {
        Log.i(TAG, "Loading Rang " + params.key.toString() + " Count " + params.requestedLoadSize)
        scope.launch {
            networkState.postValue(NetworkState.LOADING)
            request.page = params.key

            val response = repository.getListProduct(request)
            val arrayProduct = response?.result?.arrayProduct
            var message = response?.errorMsg
            when {
                arrayProduct != null -> {
                    val nextKey = params.key + 1
                    callback.onResult(arrayProduct, nextKey)
                    networkState.postValue(NetworkState.LOADED)
                }
                message != null -> {
                    networkState.postValue(NetworkState(NetworkState.Status.FAILED, message))
                }
                else -> {
                    networkState.postValue(NetworkState(NetworkState.Status.FAILED))
                }
            }
        }
    }

    override fun loadBefore(params: LoadParams<Long>, callback: LoadCallback<Long, Product>) {
    }

}