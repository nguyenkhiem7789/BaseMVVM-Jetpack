package com.nguyen.tekotest.ui.di

import com.nguyen.tekotest.data.repository.DetailProductRepository
import com.nguyen.tekotest.data.repository.ListProductRepository
import org.koin.dsl.module

val repositoryModule = module {

    single { ListProductRepository(get()) }

    single { DetailProductRepository(get()) }
}