package com.ndt.beproductive.fragment

import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import com.ndt.beproductive.App
import com.ndt.beproductive.databinding.M00SplashFragBinding
import com.ndt.beproductive.viewmodel.CommonVM

class M00SplashFrag : BaseFrag<M00SplashFragBinding, CommonVM>() {
    companion object {
        val TAG: String = M00SplashFrag::class.java.name
    }

    override fun initViews() {
        Log.i(TAG, "m00")
        Handler(Looper.getMainLooper()).postDelayed({
            goToMain()
        }, 2000)

    }

    private fun goToMain() {
        if (!App.instance.getStorage().isFirstTime) {
            mCallBack.showFrag(M006SignUpFrag.TAG, null, false)
            App.instance.getStorage().setIsFirstTime(true)
        } else if (App.instance.getStorage().isFirstTime) {
            mCallBack.showFrag(M006LoginFrag.TAG, null, false)
            App.instance.getStorage().setIsFirstTime(true)
        }
    }

    override fun initViewBinding(
        inflater: LayoutInflater, container: ViewGroup?
    ): M00SplashFragBinding {
        return M00SplashFragBinding.inflate(inflater, container, false)
    }

    override fun getClassVM(): Class<CommonVM> {
        return CommonVM::class.java
    }
}