package com.nguyen.tekotest.data.remote.network

class NetworkState(
    val status: Status,
    var msg: String? = null
) {
    enum class Status {
        RUNNING,
        SUCCESS,
        FAILED
    }

    companion object {
        var LOADED: NetworkState? = null
        var LOADING: NetworkState? = null

        init {
            LOADED = NetworkState(
                Status.SUCCESS,
                "Success"
            )
            LOADING = NetworkState(
                Status.RUNNING,
                "Running"
            )
        }
    }
}