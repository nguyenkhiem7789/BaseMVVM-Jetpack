package com.nguyen.tekotest.ui.view.listProduct

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nguyen.tekotest.data.remote.request.ListProductRequest
import com.nguyen.tekotest.data.remote.response.ListProductResponse
import com.nguyen.tekotest.data.repository.ListProductRepository
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class ListProductViewModel(private val repository: ListProductRepository) : ViewModel() {

    private val parentJob = Job()

    private val coroutineContext: CoroutineContext
        get() = parentJob + Dispatchers.Default

    private val scope = CoroutineScope(coroutineContext)

    val response = MutableLiveData<ListProductResponse>()

    fun getListProduct(request: ListProductRequest) {
        scope.launch {
            val listProductResponse = repository.getListProduct(request)
            response.postValue(listProductResponse)
        }
    }

    fun cancelAllRequests() = coroutineContext.cancel()
}