package com.ndt.beproductive.fragment

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
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