package com.nguyen.tekotest.ui.view.listProduct

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.nguyen.tekotest.R
import com.nguyen.tekotest.data.remote.request.ListProductRequest
import com.nguyen.tekotest.data.remote.response.Product
import kotlinx.android.synthetic.main.fragment_list_product.view.*
import org.koin.android.viewmodel.ext.android.viewModel

class ListProductFragment: Fragment() {

    private val viewModel: ListProductViewModel by viewModel()

    private var currentPage = 1

    private var PAGE_SIZE = 10

    private lateinit var adapter: ListProductAdapter

    private lateinit var arrayProduct: ArrayList<Product>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val view= inflater.inflate(R.layout.fragment_list_product, container, false)
        bindingData(view)
        getListProduct("")
        return view
    }

    //binding data
    private fun bindingData(view: View) {
        arrayProduct = ArrayList<Product>()
        adapter = ListProductAdapter(arrayProduct)
        view.productRecyclerView.layoutManager = LinearLayoutManager(activity)
        view.productRecyclerView.adapter = adapter
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
        viewModel.getListProduct(request).observe(viewLifecycleOwner, Observer {
            this.arrayProduct.addAll(it)
            this.adapter.notifyDataSetChanged()
        })
    }
}