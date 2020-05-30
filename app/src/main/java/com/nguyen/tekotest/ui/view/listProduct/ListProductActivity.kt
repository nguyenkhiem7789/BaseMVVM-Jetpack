package com.nguyen.tekotest.ui.view.listProduct

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.nguyen.tekotest.R
import org.koin.android.viewmodel.ext.android.viewModel

class ListProductActivity: AppCompatActivity() {

    private val listProductViewModel: ListProductViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_product)
    }

    fun getListProduct() {

    }
}