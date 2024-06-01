package com.ndt.beproductive.fragment

import android.graphics.Color
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import com.ndt.beproductive.databinding.M002NoteTakingEditFragBinding
import com.ndt.beproductive.viewmodel.M002EditVM

class M002EditFrag : BaseFrag<M002NoteTakingEditFragBinding, M002EditVM>() {
    companion object {
        val TAG: String = M002EditFrag::class.java.name
    }

    private var isUpdate = false

    override fun initViews() {
        if (mdata == null) return
        isUpdate = true
        val contentCur = mdata as String
        Log.i(TAG, "CONTENT: $contentCur")
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
                viewModel.getUpdated(contentNew)
                showNotify("Updated")
            }
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