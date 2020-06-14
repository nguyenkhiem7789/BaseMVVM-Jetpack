package com.nguyen.tekotest.ui.base

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.nguyen.tekotest.R
import com.nguyen.tekotest.data.model.GenericInterface
import com.nguyen.tekotest.data.remote.network.NetworkState
import kotlinx.android.synthetic.main.item_network_state.view.*

abstract class BaseAdapter<T : GenericInterface> : PagedListAdapter<T, RecyclerView.ViewHolder>(
    object : DiffUtil.ItemCallback<T?>() {
        override fun areItemsTheSame(@NonNull oldItem: T, @NonNull newItem: T): Boolean {
            return oldItem.id == newItem.id
        }
        override fun areContentsTheSame(@NonNull oldItem: T, @NonNull newItem: T): Boolean {
            return oldItem.id == newItem.id
        }
    }
) {
    companion object {
        private const val TYPE_PROGRESS = 0
    }

    private var networkState: NetworkState? = null

    abstract fun onCreateViewHolderItem(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder

    abstract fun onBindViewHolderItem(holder: RecyclerView.ViewHolder, position: Int)

    abstract fun getItemViewTypeItem(): Int

    fun setNetworkState(newNetworkState: NetworkState) {
        var previousState = this.networkState
        var previousExtraRow = hasExtraRow()
        this.networkState = newNetworkState
        var newExtraRow = hasExtraRow()
        if (previousExtraRow != newExtraRow) {
            if (previousExtraRow) {
                notifyItemRemoved(itemCount)
            } else {
                notifyItemInserted(itemCount)
            }
        } else if (newExtraRow && previousState != newNetworkState) {
            notifyItemChanged(itemCount - 1)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            BaseAdapter.TYPE_PROGRESS -> {
                val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_network_state, parent, false)
                NetworkStateItemViewHolder(itemView)
            }
            else -> {
                onCreateViewHolderItem(parent, viewType)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if(holder is BaseAdapter<*>.NetworkStateItemViewHolder) {
            if(networkState != null) {
                holder.bindView(networkState!!)
            }
        } else {
            onBindViewHolderItem(holder, position)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if(hasExtraRow() && position == itemCount - 1) {
            TYPE_PROGRESS
        } else {
            getItemViewTypeItem()
        }
    }

    private fun hasExtraRow() : Boolean {
        return networkState != null && networkState != NetworkState.LOADED
    }

    inner class NetworkStateItemViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun bindView(networkState: NetworkState) {
            if(networkState.status == NetworkState.Status.RUNNING) {
                itemView.progressBar.visibility = View.VISIBLE
            } else {
                itemView.progressBar.visibility = View.GONE
            }
            if(networkState.status == NetworkState.Status.FAILED) {
                itemView.errorTextView.visibility = View.VISIBLE
            } else {
                itemView.errorTextView.visibility = View.GONE
            }
        }
    }

}