package com.ndt.beproductive.fragment

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import com.ndt.beproductive.databinding.M001OnBoarding2FragBinding
import com.ndt.beproductive.viewmodel.CommonVM

class M001OnBoarding2Frag : BaseFrag<M001OnBoarding2FragBinding, CommonVM>() {
    companion object {
        val TAG: String = M001OnBoarding2Frag::class.java.name
    }

    override fun initViews() {
        Log.i(TAG, "On boarding 1")
        binding.btNextOnBoarding2.setOnClickListener {
            goToOnBoardingThree()
        }
    }

    private fun goToOnBoardingThree() {
        mCallBack.showFrag(M001OnBoarding3Frag.TAG, null, false)
    }

    override fun initViewBinding(
        inflater: LayoutInflater, container: ViewGroup?
    ): M001OnBoarding2FragBinding {
        return M001OnBoarding2FragBinding.inflate(inflater, container, false)
    }

    override fun getClassVM(): Class<CommonVM> {
        return CommonVM::class.java
    }
}