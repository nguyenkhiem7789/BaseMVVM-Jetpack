package com.nguyen.tekotest.utils.extensions

import android.text.SpannableString
import android.text.Spanned
import android.text.style.StrikethroughSpan

fun String.strikeThrough(): SpannableString {
    val string = SpannableString(this)
    string.setSpan(StrikethroughSpan(), 0, string.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
    return string
}