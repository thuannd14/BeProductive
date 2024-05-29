package com.ndt.beproductive.fragment

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import com.ndt.beproductive.databinding.M002NoteTakingEmptyFragBinding
import com.ndt.beproductive.viewmodel.M002TakingEmptyVM

class M002TakingEmptyFrag : BaseFrag<M002NoteTakingEmptyFragBinding, M002TakingEmptyVM>() {
    companion object {
        val TAG: String = M002TakingEmptyFrag::class.java.name
    }

    // xu li adapter.
    override fun initViews() {
        // add notes.
        binding.ivAddNotes.setOnClickListener {
            Log.i(TAG, "Add notes")
            mCallBack.showFrag(M002CreateNoteFrag.TAG, null, true)
        }
    }

    override fun initViewBinding(
        inflater: LayoutInflater, container: ViewGroup?
    ): M002NoteTakingEmptyFragBinding {
        return M002NoteTakingEmptyFragBinding.inflate(inflater, container, false)
    }

    override fun getClassVM(): Class<M002TakingEmptyVM> {
        return M002TakingEmptyVM::class.java
    }
}