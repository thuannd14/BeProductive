package com.ndt.beproductive.fragment

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import com.ndt.beproductive.databinding.M002NoteTakingEditFragBinding
import com.ndt.beproductive.viewmodel.M002EditVM

class M002EditFrag : BaseFrag<M002NoteTakingEditFragBinding, M002EditVM>() {
    companion object {
        val TAG: String = M002EditFrag::class.java.name
    }

    override fun initViews() {
        if(mdata == null) return
        val contentCur = mdata as String
        Log.i(TAG, "CONTENT: $contentCur")
        binding.edContentNoteEdit.setText(contentCur)
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