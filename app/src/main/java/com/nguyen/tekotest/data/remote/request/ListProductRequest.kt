package com.nguyen.tekotest.data.remote.request

import com.google.gson.annotations.SerializedName

data class ListProductRequest(
    @SerializedName("channel") val channel: String?,
    @SerializedName("visitorId") val visitorId: String?,
    @SerializedName("q") val query: String?,
    @SerializedName("terminal") val terminal: String?,
    @SerializedName("_page") var page: Long,
    @SerializedName("_limit") val limit: Int
)