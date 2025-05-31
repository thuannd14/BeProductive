package com.ndt.beproductive.dialog

import android.app.Dialog
import android.content.Context
import android.view.LayoutInflater
import android.view.Window
import com.ndt.beproductive.R

class LoadingDialog(context: Context) {
    private val dialog = Dialog(context)

    init {
        val view = LayoutInflater.from(context).inflate(R.layout.loading_dialog, null)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(view)
        dialog.setCancelable(false)
    }

    fun show() {
        dialog.show()
    }

    fun dismiss() {
        dialog.dismiss()
    }
}
