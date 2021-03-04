package com.nguyen.tekotest.ui.view.listProduct

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nguyen.tekotest.R
import com.nguyen.tekotest.data.remote.response.Product
import com.nguyen.tekotest.ui.base.BaseAdapter
import com.nguyen.tekotest.utils.extensions.formatCurrency
import com.nguyen.tekotest.utils.extensions.loadImageFromUrl
import com.nguyen.tekotest.utils.extensions.loadPlaceHolder
import com.nguyen.tekotest.utils.extensions.strikeThrough
import kotlinx.android.synthetic.main.item_list_product.view.*

class ListProductAdapter(arrayProduct: ArrayList<Product>) : BaseAdapter<Product>() {

    var listener: ((Product)->Unit)? = null

    companion object {
        private const val TYPE_ITEM = 1
    }

    override fun onCreateViewHolderItem(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_list_product, parent, false)
        return ListProductViewHolder(itemView)
    }

    override fun onBindViewHolderItem(holder: RecyclerView.ViewHolder, position: Int) {
        if(holder is ListProductViewHolder) {
            if(getItem(position) != null) {
                holder.bindTo(getItem(position)!!)
            }
        }
    }

    override fun getItemViewTypeItem(): Int {
        return TYPE_ITEM
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
            itemView.setOnClickListener {
                listener?.invoke(product);
            }
        }
    }
}

