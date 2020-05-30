package com.nguyen.tekotest.ui.view.listProduct

import androidx.lifecycle.LiveData
import com.nguyen.tekotest.data.remote.request.ProductListingRequest
import com.nguyen.tekotest.data.remote.response.Product
import com.nguyen.tekotest.data.repository.ListProductRepository
import com.nguyen.tekotest.ui.base.LiveCoroutinesViewModel

class ListProductViewModel(private val listProductRepo: ListProductRepository): LiveCoroutinesViewModel() {

    fun getListProduct(request: ProductListingRequest) : LiveData<ArrayList<Product>> {
        return listProductRepo.getListProduct(request)
    }

    override fun onCleared() {
        super.onCleared()
        listProductRepo.completableJob.cancel()
    }

}