package com.ndt.beproductive.fragment

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.ndt.beproductive.OnDialogCallBack
import com.ndt.beproductive.PickerDialog
import com.ndt.beproductive.databinding.M002NoteTakingCreateANoteFragBinding
import com.ndt.beproductive.viewmodel.M002CreateNoteVM

class M002CreateNoteFrag : BaseFrag<M002NoteTakingCreateANoteFragBinding, M002CreateNoteVM>() {
    companion object {
        val TAG: String = M002CreateNoteFrag::class.java.name
    }

    override fun initViews() {
        binding.btCreateNote.setOnClickListener {
            var contentNote = binding.edContentNote.text.toString().trim()
            Log.i(TAG, "Content: $contentNote")
            viewModel.saveNote(contentNote)
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
            val colorValue = ContextCompat.getColor(mContext, data as Int)
            Log.i(TAG, "Data: $colorValue")
            binding.edContentNote.setBackgroundColor(colorValue)
        } else if (key == PickerDialog.IV_Orange) {
            val colorValue = ContextCompat.getColor(mContext, data as Int)
            Log.i(TAG, "Data: $colorValue")
            binding.edContentNote.setBackgroundColor(colorValue)
        } else if (key == PickerDialog.IV_LRed) {
            val colorValue = ContextCompat.getColor(mContext, data as Int)
            Log.i(TAG, "Data: $colorValue")
            binding.edContentNote.setBackgroundColor(colorValue)
        } else if (key == PickerDialog.IV_LPink) {
            val colorValue = ContextCompat.getColor(mContext, data as Int)
            Log.i(TAG, "Data: $colorValue")
            binding.edContentNote.setBackgroundColor(colorValue)
        } else if (key == PickerDialog.IV_Purple) {
            val colorValue = ContextCompat.getColor(mContext, data as Int)
            Log.i(TAG, "Data: $colorValue")
            binding.edContentNote.setBackgroundColor(colorValue)
        } else if (key == PickerDialog.IV_Blue) {
            val colorValue = ContextCompat.getColor(mContext, data as Int)
            Log.i(TAG, "Data: $colorValue")
            binding.edContentNote.setBackgroundColor(colorValue)
        } else if (key == PickerDialog.IV_HeaveBlue) {
            val colorValue = ContextCompat.getColor(mContext, data as Int)
            Log.i(TAG, "Data: $colorValue")
            binding.edContentNote.setBackgroundColor(colorValue)
        } else if (key == PickerDialog.IV_Green) {
            val colorValue = ContextCompat.getColor(mContext, data as Int)
            Log.i(TAG, "Data: $colorValue")
            binding.edContentNote.setBackgroundColor(colorValue)
        } else if (key == PickerDialog.IV_LGreen) {
            val colorValue = ContextCompat.getColor(mContext, data as Int)
            Log.i(TAG, "Data: $colorValue")
            binding.edContentNote.setBackgroundColor(colorValue)
        } else if (key == PickerDialog.IV_Gray) {
            val colorValue = ContextCompat.getColor(mContext, data as Int)
            Log.i(TAG, "Data: $colorValue")
            binding.edContentNote.setBackgroundColor(colorValue)
        } else if (key == PickerDialog.IV_LYellow) {
            val colorValue = ContextCompat.getColor(mContext, data as Int)
            Log.i(TAG, "Data: $colorValue")
            binding.edContentNote.setBackgroundColor(colorValue)
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