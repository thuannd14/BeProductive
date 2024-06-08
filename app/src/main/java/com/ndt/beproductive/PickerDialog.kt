package com.ndt.beproductive

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.Window
import android.widget.ImageView

class PickerDialog(private var context: Context, private var callBack: OnDialogCallBack) :
    Dialog(context) {

    /*
    class ExitDialog(private var context: Context, private var callBack: OnMainCallBack) :
    Dialog(context) thi OnMainCallBack mang hinh hai MainAct do callBack() duoc ghi de o MainAct
     */
    companion object {
        val TAG: String = PickerDialog::class.java.name
        const val IV_Yellow = "IV_Yellow"
        const val IV_Orange = "IV_Orange"
        const val IV_LRed = "IV_LRed"
        const val IV_LPink = "IV_LPink"
        const val IV_Purple = "IV_Purple"
        const val IV_Blue = "IV_Blue"
        const val IV_HeaveBlue = "IV_HeaveBlue"
        const val IV_Green = "IV_Green"
        const val IV_LGreen = "IV_LGreen"
        const val IV_LYellow = "IV_LYellow"
        const val IV_Gray = "IV_Gray"
        const val EXIT = "EXIT"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setCancelable(false)
        setCanceledOnTouchOutside(false)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.picker_color)
        initViews()
    }

    private fun initViews() {
        // xu lí nút exit trong dialog.
        val btExit = findViewById<ImageView>(R.id.iv_reject_color)
        btExit.setOnClickListener {
            Log.i(TAG, "Exit")
            callBack.callBack(EXIT, null)
            setCanceledOnTouchOutside(true)
            dismiss()
        }
        Log.i(TAG, "Context: $context")
        val ivYellow = findViewById<ImageView>(R.id.iv_yellow)
        val ivOrange = findViewById<ImageView>(R.id.iv_orange)
        val ivLRed = findViewById<ImageView>(R.id.iv_light_red)
        val ivLPink = findViewById<ImageView>(R.id.iv_light_pink)
        val ivPurple = findViewById<ImageView>(R.id.iv_purple)
        val ivBlue = findViewById<ImageView>(R.id.iv_blue)
        val ivHeaveBlue = findViewById<ImageView>(R.id.iv_heave_blue)
        val ivGreen = findViewById<ImageView>(R.id.iv_green)
        val ivLGreen = findViewById<ImageView>(R.id.iv_light_green)
        val ivLYellow = findViewById<ImageView>(R.id.iv_light_yellow)
        val ivGray = findViewById<ImageView>(R.id.iv_gray)

        ivYellow.setOnClickListener {
            callBack.callBack(IV_Yellow, R.color.yellow_pick)
            dismiss()
        }
        ivOrange.setOnClickListener {
            callBack.callBack(IV_Orange, R.color.orange_pick)
            dismiss()
        }
        ivLRed.setOnClickListener {
            callBack.callBack(IV_Orange, R.color.light_red_pick)
            dismiss()
        }
        ivLPink.setOnClickListener {
            callBack.callBack(IV_Orange, R.color.light_pink_pick)
            dismiss()
        }
        ivPurple.setOnClickListener {
            callBack.callBack(IV_Orange, R.color.light_purple_pick)
            dismiss()
        }
        ivBlue.setOnClickListener {
            callBack.callBack(IV_Orange, R.color.light_blue)
            dismiss()
        }
        ivHeaveBlue.setOnClickListener {
            callBack.callBack(IV_Orange, R.color.heaven_blue_pick)
            dismiss()
        }
        ivGreen.setOnClickListener {
            callBack.callBack(IV_Orange, R.color.green_pick)
            dismiss()
        }
        ivLGreen.setOnClickListener {
            callBack.callBack(IV_Orange, R.color.light_green_pick)
            dismiss()
        }
        ivLYellow.setOnClickListener {
            callBack.callBack(IV_Orange, R.color.light_yellow_pick)
            dismiss()
        }
        ivGray.setOnClickListener {
            callBack.callBack(IV_Orange, R.color.gray_pick)
            dismiss()
        }
    }
}