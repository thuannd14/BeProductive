package com.ndt.beproductive.fragment

import android.graphics.Color
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.ndt.beproductive.App
import com.ndt.beproductive.OnDialogCallBack
import com.ndt.beproductive.PickerDialog
import com.ndt.beproductive.databinding.M002NoteTakingEditFragBinding
import com.ndt.beproductive.viewmodel.M002EditVM

class M002EditFrag : BaseFrag<M002NoteTakingEditFragBinding, M002EditVM>() {
    companion object {
        val TAG: String = M002EditFrag::class.java.name
    }

    private var isUpdate = false
    private var colorValue: Int? = null
    private var idNote: Int = App.instance.getStorage().id!!


    override fun initViews() {
        binding.edContentNoteEdit.setBackgroundColor(App.instance.getStorage().color!!)
        binding.tvDateEdit.text = App.instance.getStorage().dateTime

        isUpdate = true
        val contentCur = App.instance.getStorage().content
        Log.i(TAG, "CONTENT: $contentCur \n $idNote")
        binding.edContentNoteEdit.setText(contentCur)
        binding.edContentNoteEdit.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (s!! == "") {
                    binding.btEditNote.isEnabled = false
                    binding.btEditNote.setBackgroundColor(Color.GRAY)
                }
                binding.btEditNote.isEnabled = true
            }

            override fun afterTextChanged(s: Editable?) {

            }
        })

        val finalUpdate = isUpdate
        binding.btEditNote.setOnClickListener {
            val contentNew = binding.edContentNoteEdit.text.toString().trim()
            Log.i(TAG, "NEW : $contentNew")
            if (finalUpdate) {
                // Th: user khong chon mau thi van de mau cu khong thi loi.
                if (colorValue == null) {
                    colorValue = App.instance.getStorage().color
                    viewModel.getUpdated(idNote, contentNew, colorValue!!)
                }
                viewModel.getUpdated(idNote, contentNew, colorValue!!)
                mCallBack.showFrag(M002TakingEmptyFrag.TAG, null, false)
                showNotify("Updated")
            }
        }

        binding.ivChangeColor.setOnClickListener {
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
            Log.i(M002CreateNoteFrag.TAG, "Data: $colorValue")
            binding.edContentNoteEdit.setBackgroundColor(colorValue!!)
            App.instance.getStorage().color = colorValue
        } else if (key == PickerDialog.IV_Orange) {
            colorValue = ContextCompat.getColor(mContext, data as Int)
            Log.i(M002CreateNoteFrag.TAG, "Data: $colorValue")
            binding.edContentNoteEdit.setBackgroundColor(colorValue!!)
            App.instance.getStorage().color = colorValue
        } else if (key == PickerDialog.IV_LRed) {
            colorValue = ContextCompat.getColor(mContext, data as Int)
            Log.i(M002CreateNoteFrag.TAG, "Data: $colorValue")
            binding.edContentNoteEdit.setBackgroundColor(colorValue!!)
            App.instance.getStorage().color = colorValue
        } else if (key == PickerDialog.IV_LPink) {
            colorValue = ContextCompat.getColor(mContext, data as Int)
            Log.i(M002CreateNoteFrag.TAG, "Data: $colorValue")
            binding.edContentNoteEdit.setBackgroundColor(colorValue!!)
            App.instance.getStorage().color = colorValue
        } else if (key == PickerDialog.IV_Purple) {
            colorValue = ContextCompat.getColor(mContext, data as Int)
            Log.i(M002CreateNoteFrag.TAG, "Data: $colorValue")
            binding.edContentNoteEdit.setBackgroundColor(colorValue!!)
            App.instance.getStorage().color = colorValue
        } else if (key == PickerDialog.IV_Blue) {
            colorValue = ContextCompat.getColor(mContext, data as Int)
            Log.i(M002CreateNoteFrag.TAG, "Data: $colorValue")
            binding.edContentNoteEdit.setBackgroundColor(colorValue!!)
            App.instance.getStorage().color = colorValue
        } else if (key == PickerDialog.IV_HeaveBlue) {
            colorValue = ContextCompat.getColor(mContext, data as Int)
            Log.i(M002CreateNoteFrag.TAG, "Data: $colorValue")
            binding.edContentNoteEdit.setBackgroundColor(colorValue!!)
            App.instance.getStorage().color = colorValue
        } else if (key == PickerDialog.IV_Green) {
            colorValue = ContextCompat.getColor(mContext, data as Int)
            Log.i(M002CreateNoteFrag.TAG, "Data: $colorValue")
            binding.edContentNoteEdit.setBackgroundColor(colorValue!!)
            App.instance.getStorage().color = colorValue
        } else if (key == PickerDialog.IV_LGreen) {
            colorValue = ContextCompat.getColor(mContext, data as Int)
            Log.i(M002CreateNoteFrag.TAG, "Data: $colorValue")
            binding.edContentNoteEdit.setBackgroundColor(colorValue!!)
            App.instance.getStorage().color = colorValue
        } else if (key == PickerDialog.IV_Gray) {
            colorValue = ContextCompat.getColor(mContext, data as Int)
            Log.i(M002CreateNoteFrag.TAG, "Data: $colorValue")
            binding.edContentNoteEdit.setBackgroundColor(colorValue!!)
            App.instance.getStorage().color = colorValue
        } else if (key == PickerDialog.IV_LYellow) {
            colorValue = ContextCompat.getColor(mContext, data as Int)
            Log.i(M002CreateNoteFrag.TAG, "Data: $colorValue")
            binding.edContentNoteEdit.setBackgroundColor(colorValue!!)
            App.instance.getStorage().color = colorValue
        }
    }

    override fun initViewBinding(
        inflater: LayoutInflater, container: ViewGroup?
    ): M002NoteTakingEditFragBinding {
        return M002NoteTakingEditFragBinding.inflate(inflater, container, false)
    }

    override fun getClassVM(): Class<M002EditVM> {
        return M002EditVM::class.java
    }
}