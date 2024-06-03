package com.ndt.beproductive.act

import android.util.Log
import com.ndt.beproductive.databinding.ActivityMainBinding
import com.ndt.beproductive.fragment.M003ChangeThemeFrag
import com.ndt.beproductive.fragment.M003MainFocusFrag
import com.ndt.beproductive.fragment.M00SplashFrag
import com.ndt.beproductive.viewmodel.CommonVM

class MainActivity : BaseAct<ActivityMainBinding, CommonVM>() {
    companion object {
        val TAG: String = MainActivity::class.java.name
    }

    override fun initViews() {
        Log.i(TAG, "Main act")
        showFrag(M003ChangeThemeFrag.TAG, null, false)
    }

    override fun initViewBinding(): ActivityMainBinding {
        return ActivityMainBinding.inflate(layoutInflater)
    }

    override fun getClassVM(): Class<CommonVM> {
        return CommonVM::class.java
    }
}