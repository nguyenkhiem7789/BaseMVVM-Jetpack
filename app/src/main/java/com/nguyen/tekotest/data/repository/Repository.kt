package com.nguyen.tekotest.data.repository

interface Repository {
    // this override property is for saving network loading status.
    var isLoading: Boolean
}