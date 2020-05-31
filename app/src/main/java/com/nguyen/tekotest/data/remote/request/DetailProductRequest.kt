package com.nguyen.tekotest.data.remote.request

import com.google.gson.annotations.SerializedName

data class DetailProductRequest(
    @SerializedName("channel") val channel: String?,
    @SerializedName("terminal") val terminal: String?
)