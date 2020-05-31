package com.nguyen.tekotest.ui.view.listProduct

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.nguyen.tekotest.R
import com.nguyen.tekotest.data.remote.response.Product
import com.nguyen.tekotest.utils.extensions.formatCurrency
import com.nguyen.tekotest.utils.extensions.loadImageFromUrl
import com.nguyen.tekotest.utils.extensions.loadPlaceHolder
import com.nguyen.tekotest.utils.extensions.strikeThrough
import kotlinx.android.synthetic.main.item_list_product.view.*

class ListProductAdapter(var arrayProduct: ArrayList<Product>): RecyclerView.Adapter<ListProductAdapter.ListProductViewHolder>() {

    private var context: Context? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListProductViewHolder {
        this.context = parent.context
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_list_product, parent, false)
        return ListProductViewHolder(view)
    }

    override fun getItemCount(): Int = arrayProduct.size

    override fun onBindViewHolder(holder: ListProductViewHolder, position: Int) {
        holder.fillData(arrayProduct[position])
    }

   inner class ListProductViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        fun fillData(product: Product) {
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

}

