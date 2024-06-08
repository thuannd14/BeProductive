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

    // tao ra 1 bien de kiem day co phai la lan dau vao app hay ko.
    // Neu false thi la lan dau: di het M001.
    // Neu true nghia la khong phai lan dau vao app.
    // nhay tu splash den thang M002TalkingEmpty luon.
    private var isFlag: Boolean = false

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