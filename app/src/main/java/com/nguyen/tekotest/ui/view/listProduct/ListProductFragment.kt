package com.nguyen.tekotest.ui.view.listProduct

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.nguyen.tekotest.R
import com.nguyen.tekotest.data.remote.network.NetworkState
import com.nguyen.tekotest.data.remote.request.ListProductRequest
import com.nguyen.tekotest.data.remote.response.Product
import com.nguyen.tekotest.ui.subview.LoadingView
import com.nguyen.tekotest.ui.subview.loading.SnackbarView
import kotlinx.android.synthetic.main.fragment_list_product.view.*
import org.koin.android.viewmodel.ext.android.viewModel

class ListProductFragment: Fragment() {

    private val viewModel: ListProductViewModel by viewModel()

    private var currentPage = 1L

    private var PAGE_SIZE = 10

    private lateinit var adapter: ListProductAdapter

    private lateinit var arrayProduct: ArrayList<Product>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        Log.e("ListProductFragment", "> onCreateView")
        val view= inflater.inflate(R.layout.fragment_list_product, container, false)
        initView(view)
        bindingData()
        this.currentPage = 1
        getListProduct("")
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.e("ListProductFragment", "> onDestroyView")
    }

    //binding data
    private fun initView(view: View) {
        arrayProduct = ArrayList<Product>()
        adapter = ListProductAdapter(arrayProduct)
        adapter.listener = {
            val action = ListProductFragmentDirections.actionListProductFragmentToDetailProductFragment(it)
            Navigation.findNavController(view).navigate(action)
        }
        view.productRecyclerView.layoutManager = LinearLayoutManager(activity)
        view.productRecyclerView.adapter = adapter
        view.productRecyclerView.addItemDecoration(
            DividerItemDecoration(
                activity,
                DividerItemDecoration.VERTICAL
            )
        )
    }

    // request api get data list product
    private fun getListProduct(query: String) {
        val request = ListProductRequest(
            "pv_online",
            "",
            query,
            "CP01",
            currentPage,
            PAGE_SIZE
        )
        LoadingView.getInstance(requireContext()).show()
        viewModel.requestSearch.value = request
    }

    ///binding data
    private fun bindingData() {
        viewModel.arrayProductLiveData?.observe(viewLifecycleOwner, Observer {
            LoadingView.getInstance(requireContext()).dismiss()
            this.currentPage += 1
            adapter.submitList(it)
        })
        viewModel.networkState?.observe(viewLifecycleOwner, Observer<NetworkState> {
            adapter.setNetworkState(it)
        })
        adapter.notifyDataSetChanged()
    }
}