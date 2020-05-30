package com.nguyen.tekotest.ui.di

import com.nguyen.tekotest.ui.view.detailProduct.DetailProductViewModel
import com.nguyen.tekotest.ui.view.listProduct.ListProductViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {

    viewModel { ListProductViewModel(get()) }

    viewModel { DetailProductViewModel() }

}