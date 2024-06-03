package com.ndt.beproductive.fragment

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
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
            mCallBack.showFrag(M003ChangeThemeFrag.TAG, null, false)
        }
        // cong them 1 vi viewpager tinh bat dau tu 1.
        binding.clMain.setBackgroundResource(viewModel.ARR_IMG[indexImg])
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