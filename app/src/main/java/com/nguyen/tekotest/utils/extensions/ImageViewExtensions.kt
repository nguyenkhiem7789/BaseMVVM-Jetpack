package com.nguyen.tekotest.utils.extensions

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.nguyen.tekotest.R
import kotlinx.android.synthetic.main.item_list_product.view.*

fun ImageView.loadImageFromUrl(url: String) {
    Glide
        .with(context!!)
        .load(url)
        .centerCrop()
        .placeholder(R.drawable.empty)
        .into(this)
}

fun ImageView.loadPlaceHolder() {
    Glide
        .with(context!!)
        .load(R.drawable.empty)
        .centerCrop()
        .placeholder(R.drawable.empty)
        .into(this)
}