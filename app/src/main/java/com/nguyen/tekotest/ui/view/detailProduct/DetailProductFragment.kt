package com.nguyen.tekotest.ui.view.detailProduct

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import com.nguyen.tekotest.R
import com.nguyen.tekotest.data.remote.network.NetworkState
import com.nguyen.tekotest.data.remote.request.DetailProductRequest
import com.nguyen.tekotest.data.remote.response.Product
import kotlinx.android.synthetic.main.fragment_detail_product.*
import kotlinx.android.synthetic.main.fragment_list_product.view.*
import org.koin.android.viewmodel.ext.android.viewModel

class DetailProductFragment: Fragment() {

    private val viewModel: DetailProductViewModel by viewModel()

    var product: Product? = null

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
        var args = DetailProductFragmentArgs.fromBundle(requireArguments())
        this.product = args.product

        view.backButton.setOnClickListener {
            Navigation.findNavController(view).popBackStack()
        }
    }

    private fun getDetailData() {
        if(product?.arrayPromotionPrice?.count() == 0) {
            return
        }
        var request = DetailProductRequest(
            product?.id,product?.arrayPromotionPrice?.get(0)?.channel, product?.arrayPromotionPrice?.get(0)?.terminal
        )
        viewModel.requestDetail.value = request
        viewModel.getDetail()
    }

    ///binding data
    private fun bindingData() {
        viewModel.productLiveData.observe(viewLifecycleOwner, Observer {
            nameProductText.text = it.name
            codeTextView.text = it.id
        })
        viewModel.networkState?.observe(viewLifecycleOwner, Observer<NetworkState> {
        })
    }

}