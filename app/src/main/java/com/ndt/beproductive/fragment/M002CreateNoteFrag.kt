package com.ndt.beproductive.fragment

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.ndt.beproductive.OnDialogCallBack
import com.ndt.beproductive.PickerDialog
import com.ndt.beproductive.R
import com.ndt.beproductive.databinding.M002NoteTakingCreateANoteFragBinding
import com.ndt.beproductive.viewmodel.M002CreateNoteVM

class M002CreateNoteFrag : BaseFrag<M002NoteTakingCreateANoteFragBinding, M002CreateNoteVM>() {
    companion object {
        val TAG: String = M002CreateNoteFrag::class.java.name
    }

    private var colorValue: Int? = null

    override fun initViews() {
        binding.btCreateNote.setOnClickListener {
            var contentNote = binding.edContentNote.text.toString().trim()
            if (colorValue == null) {
                colorValue = ContextCompat.getColor(mContext, R.color.gray_pick)
                viewModel.saveNoteColor(contentNote, colorValue!!)
            }
            viewModel.saveNoteColor(contentNote, colorValue!!)
            Log.i(TAG, "Content: $contentNote $colorValue")
            mCallBack.showFrag(M002TakingEmptyFrag.TAG, null, false)
        }

        binding.changeColorBg.setOnClickListener {
            val pickerColor = PickerDialog(mContext, object : OnDialogCallBack {
                override fun callBack(key: String?, data: Any?) {
                    changeBackGround(key, data)
                }
            })
            pickerColor.show()
        }
    }

    private fun changeBackGround(key: String?, data: Any?) {
        if (key == PickerDialog.IV_Yellow) {
            // Chuyen int thanh ma mau.
            colorValue = ContextCompat.getColor(mContext, data as Int)
            Log.i(TAG, "Data: $colorValue")
            binding.edContentNote.setBackgroundColor(colorValue!!)
        } else if (key == PickerDialog.IV_Orange) {
            colorValue = ContextCompat.getColor(mContext, data as Int)
            Log.i(TAG, "Data: $colorValue")
            binding.edContentNote.setBackgroundColor(colorValue!!)
        } else if (key == PickerDialog.IV_LRed) {
            colorValue = ContextCompat.getColor(mContext, data as Int)
            Log.i(TAG, "Data: $colorValue")
            binding.edContentNote.setBackgroundColor(colorValue!!)
        } else if (key == PickerDialog.IV_LPink) {
            colorValue = ContextCompat.getColor(mContext, data as Int)
            Log.i(TAG, "Data: $colorValue")
            binding.edContentNote.setBackgroundColor(colorValue!!)
        } else if (key == PickerDialog.IV_Purple) {
            colorValue = ContextCompat.getColor(mContext, data as Int)
            Log.i(TAG, "Data: $colorValue")
            binding.edContentNote.setBackgroundColor(colorValue!!)
        } else if (key == PickerDialog.IV_Blue) {
            colorValue = ContextCompat.getColor(mContext, data as Int)
            Log.i(TAG, "Data: $colorValue")
            binding.edContentNote.setBackgroundColor(colorValue!!)
        } else if (key == PickerDialog.IV_HeaveBlue) {
            colorValue = ContextCompat.getColor(mContext, data as Int)
            Log.i(TAG, "Data: $colorValue")
            binding.edContentNote.setBackgroundColor(colorValue!!)
        } else if (key == PickerDialog.IV_Green) {
            colorValue = ContextCompat.getColor(mContext, data as Int)
            Log.i(TAG, "Data: $colorValue")
            binding.edContentNote.setBackgroundColor(colorValue!!)
        } else if (key == PickerDialog.IV_LGreen) {
            colorValue = ContextCompat.getColor(mContext, data as Int)
            Log.i(TAG, "Data: $colorValue")
            binding.edContentNote.setBackgroundColor(colorValue!!)
        } else if (key == PickerDialog.IV_Gray) {
            colorValue = ContextCompat.getColor(mContext, data as Int)
            Log.i(TAG, "Data: $colorValue")
            binding.edContentNote.setBackgroundColor(colorValue!!)
        } else if (key == PickerDialog.IV_LYellow) {
            colorValue = ContextCompat.getColor(mContext, data as Int)
            Log.i(TAG, "Data: $colorValue")
            binding.edContentNote.setBackgroundColor(colorValue!!)
        }
    }

    override fun initViewBinding(
        inflater: LayoutInflater, container: ViewGroup?
    ): M002NoteTakingCreateANoteFragBinding {
        return M002NoteTakingCreateANoteFragBinding.inflate(inflater, container, false)
    }

    override fun getClassVM(): Class<M002CreateNoteVM> {
        return M002CreateNoteVM::class.java
    }
}