package com.ndt.beproductive.fragment

import android.graphics.Bitmap
import android.graphics.PorterDuff
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ndt.beproductive.CommonUtils
import com.ndt.beproductive.R
import com.ndt.beproductive.act.MainActivity
import com.ndt.beproductive.databinding.M005ProfileFragBinding
import com.ndt.beproductive.viewmodel.M005SettingVM


class M005SettingFrag : BaseFrag<M005ProfileFragBinding, M005SettingVM>() {
    companion object {
        val TAG: String = M005SettingFrag::class.java.name
        const val IMG_PATH = "IMG_PATH"
    }

    private var userName = CommonUtils.getPref(USER_NAME)
    private var email = CommonUtils.getPref(EMAIL)

    override fun initViews() {

        binding.ciUsername.setOnClickListener {
            val bitmap: Bitmap = MediaStore.Images.Media.getBitmap(
                requireActivity().contentResolver, viewModel.getUri()
            )
            binding.ciUsername.setImageBitmap(bitmap)
        }
        changMenu()
        setInfo()

        // log out.
        binding.ivLogOut.setOnClickListener {
            CommonUtils.removePref(USER_NAME)
            CommonUtils.removePref(PASSWORD)
            mCallBack.showFrag(M006LoginFrag.TAG, null, false)
        }
    }


    private fun setInfo() {
        binding.tvEmail.text = email
        binding.tvUsername.text = userName
        binding.tvUserNameAcc.text = userName
    }

    private fun changMenu() {
        binding.includeMenu.ivNotes.setOnClickListener(this)
        binding.includeMenu.ivPomodoro.setOnClickListener(this)
        binding.includeMenu.ivExplore.setOnClickListener(this)
        binding.includeMenu.ivSetting.setOnClickListener(this)
        binding.includeMenu.ivRoom.setOnClickListener(this)

        changeColor(MainActivity.SETTING, colorBlue, colorBlack)
    }

    override fun clickViews(v: View?) {
        if (v?.id == R.id.iv_notes) {
            changeColor(MainActivity.ALL_NOTES, colorBlue, colorBlack)
            mCallBack.showFrag(M002TakingEmptyFrag.TAG, null, true)
        } else if (v?.id == R.id.iv_pomodoro) {
            changeColor(MainActivity.FOCUS_TIME, colorBlue, colorBlack)
            mCallBack.showFrag(M003MainFocusFrag.TAG, null, true)
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
    ): M005ProfileFragBinding {
        return M005ProfileFragBinding.inflate(inflater, container, false)
    }

    override fun getClassVM(): Class<M005SettingVM> {
        return M005SettingVM::class.java
    }
}