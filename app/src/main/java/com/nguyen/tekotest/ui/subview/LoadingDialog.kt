package com.nguyen.tekotest.ui.subview

import android.app.Dialog
import android.content.Context
import android.view.WindowManager
import androidx.annotation.NonNull
import com.nguyen.tekotest.R
import com.nguyen.tekotest.utils.SingletonHolder

class LoadingDialog private constructor(context: Context){

    init {
    }

    companion object : SingletonHolder<LoadingDialog, Context>(::LoadingDialog)

    private val progressDialog: ProgressDialog by lazy {
        ProgressDialog(context)
    }

    fun show() {
        dismiss()
        progressDialog.show()
    }

    fun dismiss() {
        if(progressDialog.isShowing) {
            progressDialog.dismiss()
        }
    }

    class ProgressDialog(@NonNull context: Context) : Dialog(context, R.style.SmoothProgressDialog) {
        init {
            setContentView(R.layout.layout_loading)
            val lp = window!!.attributes
            lp.width = WindowManager.LayoutParams.WRAP_CONTENT
            lp.height = WindowManager.LayoutParams.WRAP_CONTENT
            lp.dimAmount = 0.0f
            window!!.attributes = lp
            setCancelable(true)
            setCanceledOnTouchOutside(false)
        }
    }
}