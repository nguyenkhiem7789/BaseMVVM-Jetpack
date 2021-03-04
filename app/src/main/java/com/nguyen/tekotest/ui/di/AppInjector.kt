package com.nguyen.tekotest.ui.di

import com.nguyen.tekotest.data.remote.network.DetailProductService
import com.nguyen.tekotest.data.remote.network.ListProductService
import org.koin.dsl.module
import retrofit2.Retrofit

private val retrofit: Retrofit = createNetworkClient()

private val listProductService: ListProductService = retrofit.create(ListProductService::class.java)

private val detailProductService: DetailProductService = retrofit.create(DetailProductService::class.java);

val networkModule = module {
    single { listProductService }
    single { detailProductService }
}