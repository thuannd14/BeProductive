package com.ndt.beproductive.fragment

import android.graphics.PorterDuff
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import com.ndt.beproductive.App
import com.ndt.beproductive.R
import com.ndt.beproductive.act.MainActivity
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

        binding.ivAnalys.setOnClickListener {
            mCallBack.showFrag(M007AnalyticsFrag.TAG, null, false)
        }
        changMenu()
    }


    private fun changMenu() {
        binding.includeMenu.ivNotes.setOnClickListener(this)
        binding.includeMenu.ivPomodoro.setOnClickListener(this)
        binding.includeMenu.ivExplore.setOnClickListener(this)
        binding.includeMenu.ivSetting.setOnClickListener(this)
        binding.includeMenu.ivRoom.setOnClickListener(this)

        changeColor(MainActivity.FOCUS_TIME, colorBlue, colorBlack)
    }

    override fun clickViews(v: View?) {
        if (v?.id == R.id.iv_notes) {
            changeColor(MainActivity.ALL_NOTES, colorBlue, colorBlack)
            mCallBack.showFrag(M002TakingEmptyFrag.TAG, null, true)
        } else if (v?.id == R.id.iv_pomodoro) {
            changeColor(MainActivity.FOCUS_TIME, colorBlue, colorBlack)
            mCallBack.showFrag(TAG, null, true)
        } else if (v?.id == R.id.iv_explore) {
            changeColor(MainActivity.EXPLORE, colorBlue, colorBlack)
            mCallBack.showFrag(M004ExploreFrag.TAG, null, true)
        } else if (v?.id == R.id.iv_setting) {
            changeColor(MainActivity.SETTING, colorBlue, colorBlack)
            mCallBack.showFrag(M005SettingFrag.TAG, null, true)
        } else if (v?.id == R.id.iv_room) {
            changeColor(MainActivity.DATA, colorBlue, colorBlack)
            mCallBack.showFrag(M008JoinFrag.TAG, null, true)
        }
    }

    private fun changeColor(key: Int, colorBlue: Int, colorBlack: Int) {
        val alphaNote = if (key == MainActivity.ALL_NOTES) colorBlue else colorBlack
        binding.includeMenu.ivNotes.setColorFilter(alphaNote, PorterDuff.Mode.SRC_IN)

        val alphaLFocus = if (key == MainActivity.FOCUS_TIME) colorBlue else colorBlack
        binding.includeMenu.ivPomodoro.setColorFilter(alphaLFocus, PorterDuff.Mode.SRC_IN)

        val alphaExplore = if (key == MainActivity.EXPLORE) colorBlue else colorBlack
        binding.includeMenu.ivExplore.setColorFilter(alphaExplore, PorterDuff.Mode.SRC_IN)

        val alphaSetting = if (key == MainActivity.SETTING) colorBlue else colorBlack
        binding.includeMenu.ivSetting.setColorFilter(alphaSetting, PorterDuff.Mode.SRC_IN)

        val alphaData = if (key == MainActivity.DATA) colorBlue else colorBlack
        binding.includeMenu.ivRoom.setColorFilter(alphaData, PorterDuff.Mode.SRC_IN)

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