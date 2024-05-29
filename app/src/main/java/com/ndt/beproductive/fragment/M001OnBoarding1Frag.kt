package com.ndt.beproductive.fragment

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import com.ndt.beproductive.databinding.M001OnBoarding1FragBinding
import com.ndt.beproductive.viewmodel.CommonVM

class M001OnBoarding1Frag : BaseFrag<M001OnBoarding1FragBinding, CommonVM>() {
    companion object {
        val TAG: String = M001OnBoarding1Frag::class.java.name
    }

    override fun initViews() {
        Log.i(TAG, "On boarding 1")
        binding.btNextOnBoarding1.setOnClickListener {
            goToOnBoardingTwo()
        }
    }

    private fun goToOnBoardingTwo() {
        mCallBack.showFrag(M001OnBoarding2Frag.TAG, null, false)
    }

    override fun initViewBinding(
        inflater: LayoutInflater, container: ViewGroup?
    ): M001OnBoarding1FragBinding {
        return M001OnBoarding1FragBinding.inflate(inflater, container, false)
    }

    override fun getClassVM(): Class<CommonVM> {
        return CommonVM::class.java
    }
}