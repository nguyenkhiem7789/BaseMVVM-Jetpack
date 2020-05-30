package com.nguyen.tekotest.data.remote.request

class ProductListingRequest(val channel: String?, val visitorId: String?, val query: String?,
                            val terminal: String?, val page: Int?, val limit: Int?) {

    fun getParams(): String {
        var part = emptyArray<String>()
        if(channel != null) {
            part.plus(channel)
        }
        if(visitorId != null) {
            part.plus(visitorId)
        }
        if(query != null) {
            part.plus(query)
        }
        if(terminal != null) {
            part.plus(terminal)
        }
        if(page != null) {
            part.plus(page.toString())
        }
        if(limit != null) {
            part.plus(limit.toString())
        }
        return part.joinToString("&")
    }

}