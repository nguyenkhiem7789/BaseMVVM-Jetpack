package com.nguyen.tekotest.ui.view.listProduct

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.nguyen.tekotest.R
import com.nguyen.tekotest.data.remote.network.NetworkState
import com.nguyen.tekotest.data.remote.response.Product
import com.nguyen.tekotest.utils.extensions.formatCurrency
import com.nguyen.tekotest.utils.extensions.loadImageFromUrl
import com.nguyen.tekotest.utils.extensions.loadPlaceHolder
import com.nguyen.tekotest.utils.extensions.strikeThrough
import kotlinx.android.synthetic.main.item_list_product.view.*
import kotlinx.android.synthetic.main.item_network_state.view.*

class ListProductAdapter(arrayProduct: ArrayList<Product>) :
    PagedListAdapter<Product, RecyclerView.ViewHolder>(Product.DIFF_CALLBACK) {

    companion object {
        private val TYPE_PROGRESS = 0
        private val TYPE_ITEM = 1
    }

    private var networdState: NetworkState? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return if(viewType == TYPE_PROGRESS) {
            val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_network_state, parent, false)
            NetworkStateItemViewHolder(itemView)
        } else {
            val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_list_product, parent, false)
            ListProductViewHolder(itemView)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if(holder is ListProductViewHolder) {
            if(getItem(position) != null) {
                holder.bindTo(getItem(position)!!)
            } else if(holder is NetworkStateItemViewHolder) {
                if(networdState != null) {
                    holder.bindView(networdState!!)
                }
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        if(hasExtraRow() && position == itemCount - 1) {
            return TYPE_PROGRESS
        } else {
            return TYPE_ITEM
        }
    }

    private fun hasExtraRow() : Boolean {
        return networdState != null && networdState != NetworkState.LOADED
    }

    fun setNetworkState(newNetworkState: NetworkState) {
        var previousState = this.networdState
        var previousExtraRow = hasExtraRow()
        this.networdState = newNetworkState
        var newExtraRow = hasExtraRow()
        if(previousExtraRow != newExtraRow) {
            if(previousExtraRow) {
                notifyItemRemoved(itemCount)
            } else {
                notifyItemInserted(itemCount)
            }
        } else if(newExtraRow && previousState != newNetworkState) {
            notifyItemChanged(itemCount - 1)
        }
    }

    inner class ListProductViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        fun bindTo(product: Product) {
            val arrayImage = product.arrayImage
            if(arrayImage != null && arrayImage.size > 0 && arrayImage[0].url != null) {
                itemView.productImageView.loadImageFromUrl(arrayImage[0].url!!)
            } else {
                itemView.productImageView.loadPlaceHolder()
            }
            itemView.nameTextView.text = product.name
            itemView.finalPriceTextView.text = product.price?.sellPrice?.formatCurrency()
            itemView.totalPriceTextView.text = product.price?.supplierSalePrice?.formatCurrency()?.strikeThrough()
        }
    }

    inner class NetworkStateItemViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun bindView(networdState: NetworkState) {
            if(networdState.status == NetworkState.Status.RUNNING) {
                itemView.progressBar.visibility = View.VISIBLE
            } else {
                itemView.progressBar.visibility = View.GONE
            }
            if(networdState.status == NetworkState.Status.FAILED) {
                itemView.errorTextView.visibility = View.VISIBLE
            } else {
                itemView.errorTextView.visibility = View.GONE
            }
        }
    }

}

