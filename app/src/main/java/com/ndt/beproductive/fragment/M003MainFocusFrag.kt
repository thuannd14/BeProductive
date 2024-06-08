package com.ndt.beproductive.fragment

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import com.ndt.beproductive.App
import com.ndt.beproductive.databinding.M003FocusMainFragBinding
import com.ndt.beproductive.viewmodel.M003MainFocusVM

class M003MainFocusFrag : BaseFrag<M003FocusMainFragBinding, M003MainFocusVM>() {
    companion object {
        val TAG: String = M003MainFocusFrag::class.java.name
    }

    override fun initViews() {
        var indexImg = viewModel.getIndexImg()
        Log.i(TAG, "Img: $indexImg")
        binding.ivSelectThemeOnFocus.setOnClickListener {
            mCallBack.showFrag(M003ChangeThemeFrag.TAG, null, true)
        }

        binding.clMain.setBackgroundResource(viewModel.ARR_IMG[indexImg])

        // start focus.
        binding.btStartTime.setOnClickListener {
            var textFocus = binding.edContentFocus.text.toString().trim()
            Log.i(TAG, "Content: $textFocus")
            it.startAnimation(
                AnimationUtils.loadAnimation(
                    mContext, androidx.appcompat.R.anim.abc_popup_enter
                )
            )
            App.instance.getStorage().contentText = textFocus
            mCallBack.showFrag(M003StartTimeFrag.TAG, null, true)
        }

        binding.includeMenu.ivNotes.setOnClickListener {
            mCallBack.showFrag(M002TakingEmptyFrag.TAG, null, false)
        }

        binding.includeMenu.ivSetting.setOnClickListener {
            mCallBack.showFrag(M005SettingFrag.TAG, null, true)
        }
    }

    override fun initViewBinding(
        inflater: LayoutInflater, container: ViewGroup?
    ): M003FocusMainFragBinding {
        return M003FocusMainFragBinding.inflate(inflater, container, false)
    }

    override fun getClassVM(): Class<M003MainFocusVM> {
        return M003MainFocusVM::class.java
    }
}