package com.ndt.beproductive.fragment

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import com.ndt.beproductive.App
import com.ndt.beproductive.databinding.M002NoteTakingDetailFragBinding
import com.ndt.beproductive.viewmodel.M002DetailVM

class M002DetailNoteFragment : BaseFrag<M002NoteTakingDetailFragBinding, M002DetailVM>() {
    companion object {
        val TAG: String = M002DetailNoteFragment::class.java.name
    }

    private lateinit var contentNote: String
    private lateinit var dateTimeNote: String
    private var colorBg: Int? = null
    override fun initViews() {
        contentNote = App.instance.getStorage().content
        dateTimeNote = App.instance.getStorage().dateTime
        colorBg = App.instance.getStorage().color

        if (contentNote.isNotEmpty()) {
            binding.tvDetail.text = contentNote
            binding.tvDateDetail.text = dateTimeNote
            binding.cLMainDetail.setBackgroundColor(colorBg!!)
        } else {
            showNotify("Null")
        }

        binding.tvEditDetail.setOnClickListener {
            mCallBack.showFrag(M002EditFrag.TAG, null, true)
            Log.i(TAG, "M002Edit")
        }
        binding.ivBackDetail.setOnClickListener {
            mCallBack.backPrevious()
        }
    }

    override fun initViewBinding(
        inflater: LayoutInflater, container: ViewGroup?
    ): M002NoteTakingDetailFragBinding {
        return M002NoteTakingDetailFragBinding.inflate(inflater, container, false)
    }

    override fun getClassVM(): Class<M002DetailVM> {
        return M002DetailVM::class.java
    }
}