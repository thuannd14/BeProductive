package com.ndt.beproductive.fragment

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import com.ndt.beproductive.databinding.M001OnBoarding3FragBinding
import com.ndt.beproductive.viewmodel.CommonVM

class M001OnBoarding3Frag : BaseFrag<M001OnBoarding3FragBinding, CommonVM>() {
    companion object {
        val TAG: String = M001OnBoarding3Frag::class.java.name
    }

    override fun initViews() {
        Log.i(TAG, "On boarding 1")
        binding.btNextOnBoarding3.setOnClickListener {
            goToOnBoardingFour()
        }
    }

    private fun goToOnBoardingFour() {
        mCallBack.showFrag(M001OnBoarding4Frag.TAG, null, false)
    }

    override fun initViewBinding(
        inflater: LayoutInflater, container: ViewGroup?
    ): M001OnBoarding3FragBinding {
        return M001OnBoarding3FragBinding.inflate(inflater, container, false)
    }

    override fun getClassVM(): Class<CommonVM> {
        return CommonVM::class.java
    }
}