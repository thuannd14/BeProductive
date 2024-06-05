package com.ndt.beproductive

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.Window
import android.widget.TextView

class ExitDialog(private var context: Context, private var callBack: OnDialogCallBack) :
    Dialog(context) {

    /*
    class ExitDialog(private var context: Context, private var callBack: OnMainCallBack) :
    Dialog(context) thi OnMainCallBack mang hinh hai MainAct do callBack() duoc ghi de o MainAct
     */
    companion object {
        val TAG: String = ExitDialog::class.java.name
        const val KEY_YES = "Yes" // not exit
        const val KEY_NO = "No" // exit.
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setCancelable(false)
        setCanceledOnTouchOutside(false)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.exit_dialog)
        initViews()
    }

    private fun initViews() {
        Log.i(TAG, "Context: $context")
        val btYes = findViewById<TextView>(R.id.tv_yes_back)
        btYes.setOnClickListener {
            Log.i(TAG, "Yes back")
            callBack.callBack(KEY_YES, null)
            dismiss()
        }

        val btNo = findViewById<TextView>(R.id.tv_no_stay)
        btNo.setOnClickListener {
            Log.i(TAG, "No stay")
            callBack.callBack(KEY_NO, null)
            setCanceledOnTouchOutside(true)
            dismiss()
        }
    }
}