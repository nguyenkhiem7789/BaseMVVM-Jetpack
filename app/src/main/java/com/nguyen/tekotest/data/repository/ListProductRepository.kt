package com.nguyen.tekotest.data.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.nguyen.tekotest.data.remote.network.ListProductService
import com.nguyen.tekotest.data.remote.request.ListProductRequest
import com.nguyen.tekotest.data.remote.response.Product
import kotlinx.coroutines.*
import retrofit2.HttpException

class ListProductRepository constructor(
    private val listProductService: ListProductService
) : Repository {

    override var isLoading: Boolean = false

    private var arrayProduct = ArrayList<Product>()
    private var mutableLiveData = MutableLiveData<ArrayList<Product>>()
    val completableJob = Job()
    private val coroutineScope = CoroutineScope(Dispatchers.IO + completableJob)

    fun getListProduct(request: ListProductRequest) : MutableLiveData<ArrayList<Product>> {
        isLoading = true
        coroutineScope.launch {
            val request = listProductService.getListProductAsync(request.channel,
                request.visitorId, request.query, request.terminal, request.page, request.limit)
            withContext(Dispatchers.Main) {
                try {
                    val response = request.await()
                    val products = response.result?.arrayProduct
                    if(products != null) {
                        arrayProduct = products
                        mutableLiveData.value = arrayProduct
                    } else {
                        Log.e("getListProduct ", "products null")
                    }
                } catch (e: HttpException) {
                    Log.e("getListProduct ", "${e.message}")
                } catch (e: Throwable) {
                    Log.e("getListProduct ", "${e.message}")
                }
            }
        }
        return mutableLiveData
    }

}