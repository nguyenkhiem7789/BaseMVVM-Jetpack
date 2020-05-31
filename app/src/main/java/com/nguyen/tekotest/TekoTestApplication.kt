package com.nguyen.tekotest

import android.app.Application
import com.nguyen.tekotest.ui.di.networkModule
import com.nguyen.tekotest.ui.di.repositoryModule
import com.nguyen.tekotest.ui.di.viewModelModule
import com.nguyen.tekotest.ui.subview.loading.LoadingDialog
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class TekoTestApplication : Application() {

    init {
        instance = this
    }

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@TekoTestApplication)
            modules(listOf(networkModule, repositoryModule, viewModelModule))
        }
    }

    companion object {

        private var instance: TekoTestApplication? = null

        private fun applicationContext(): TekoTestApplication {
            return instance as TekoTestApplication
        }
    }

}