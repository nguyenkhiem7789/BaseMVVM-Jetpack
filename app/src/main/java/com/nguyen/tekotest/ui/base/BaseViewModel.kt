package com.nguyen.tekotest.ui.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.nguyen.tekotest.data.remote.network.NetworkState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import java.util.concurrent.Executor
import java.util.concurrent.Executors
import kotlin.coroutines.CoroutineContext

public open class BaseViewModel : ViewModel() {

    private val parentJob = Job()

    private val coroutineContext: CoroutineContext
        get() = parentJob + Dispatchers.Default

    val scope = CoroutineScope(coroutineContext)

    val executor: Executor by lazy {
        Executors.newFixedThreadPool(5)
    }

    var networkState: LiveData<NetworkState>? = null

    fun cancelAllRequests() = coroutineContext.cancel()
}