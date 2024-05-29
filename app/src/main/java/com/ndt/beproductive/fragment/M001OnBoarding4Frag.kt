package com.ndt.beproductive.fragment

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import com.ndt.beproductive.databinding.M001OnBoarding4FragBinding
import com.ndt.beproductive.viewmodel.CommonVM

class M001OnBoarding4Frag : BaseFrag<M001OnBoarding4FragBinding, CommonVM>() {
    companion object {
        val TAG: String = M001OnBoarding4Frag::class.java.name
    }

    override fun initViews() {
        Log.i(TAG, "On boarding 1")
        binding.btNextOnBoarding4.setOnClickListener {
            goToOnBoardingFour()
        }
    }

    private fun goToOnBoardingFour() {
        mCallBack.showFrag(M002TakingEmptyFrag.TAG, null, false)
    }

    override fun initViewBinding(
        inflater: LayoutInflater, container: ViewGroup?
    ): M001OnBoarding4FragBinding {
        return M001OnBoarding4FragBinding.inflate(inflater, container, false)
    }

    override fun getClassVM(): Class<CommonVM> {
        return CommonVM::class.java
    }
}