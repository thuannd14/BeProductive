package com.ndt.beproductive.fragment

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.ndt.beproductive.App
import com.ndt.beproductive.adapter.ChangeThemeAdapter
import com.ndt.beproductive.databinding.M003OnFocusSelectImgFragBinding
import com.ndt.beproductive.viewmodel.M003ChangeThemeVM

class M003ChangeThemeFrag : BaseFrag<M003OnFocusSelectImgFragBinding, M003ChangeThemeVM>() {
    companion object {
        val TAG: String = M003ChangeThemeFrag::class.java.name
    }

    private lateinit var adapter: ChangeThemeAdapter

    override fun initViews() {
        Log.i(TAG, "SIze: ${viewModel.ARR_IMG.size}")
        adapter = ChangeThemeAdapter(mContext, viewModel.ARR_IMG)
        binding.vpSelectImg.adapter = adapter
        // set vertical chp viewpager2.
        binding.vpSelectImg.orientation = ViewPager2.ORIENTATION_VERTICAL

        // lay vi tri anh hien tai de set len man hing main on focus.
        binding.vpSelectImg.registerOnPageChangeCallback(object :
            ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                Log.i(TAG, "INDEX: $position")
                App.instance.getStorage().indexImg = position
            }
        })

        // chuyen ve main focus khi lay duoc index anh.
        binding.btStartFocus.setOnClickListener {
            mCallBack.showFrag(M003MainFocusFrag.TAG, null, false)
        }
    }

    override fun initViewBinding(
        inflater: LayoutInflater, container: ViewGroup?
    ): M003OnFocusSelectImgFragBinding {
        return M003OnFocusSelectImgFragBinding.inflate(inflater, container, false)
    }

    override fun getClassVM(): Class<M003ChangeThemeVM> {
        return M003ChangeThemeVM::class.java
    }
}