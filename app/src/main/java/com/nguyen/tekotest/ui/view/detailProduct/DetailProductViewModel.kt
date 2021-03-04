package com.nguyen.tekotest.ui.view.detailProduct

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.nguyen.tekotest.data.remote.request.DetailProductRequest
import com.nguyen.tekotest.data.remote.response.Product
import com.nguyen.tekotest.data.repository.DetailProductRepository
import com.nguyen.tekotest.ui.base.BaseViewModel
import kotlinx.coroutines.launch

class DetailProductViewModel(private val repository: DetailProductRepository): BaseViewModel() {

    var requestDetail = MutableLiveData<DetailProductRequest>()

    var productLiveData: MutableLiveData<Product>? = null

    init {
        scope.launch {
            Log.e("AAAAABBBBB", "--->call api get detail 1111")
            productLiveData?.value = repository.getProductDetail(request = requestDetail.value!!)?.result?.product
        }
    }
}