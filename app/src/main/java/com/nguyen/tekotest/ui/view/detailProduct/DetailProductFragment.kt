package com.nguyen.tekotest.ui.view.detailProduct

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.nguyen.tekotest.R
import com.nguyen.tekotest.data.remote.request.DetailProductRequest
import com.nguyen.tekotest.ui.subview.LoadingView
import com.nguyen.tekotest.ui.view.listProduct.ListProductViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class DetailProductFragment: Fragment() {

    private val viewModel: DetailProductViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        var view = inflater.inflate(R.layout.fragment_detail_product, container, false)
        initView(view)
        bindingData()
        getDetailData()
        return view
    }

    private fun initView(view: View) {

    }

    private fun getDetailData() {
        var request = DetailProductRequest(
            "", ""
        )
        LoadingView.getInstance(requireContext()).show();
        viewModel.requestDetail.value = request
    }

    private fun bindingData() {

    }

}