package com.ndt.beproductive.viewmodel

import android.net.Uri
import com.ndt.beproductive.CommonUtils
import com.ndt.beproductive.fragment.M005SettingFrag


class M005SettingVM : BaseViewModel() {

    companion object {
        val TAG: String = M005SettingVM::class.java.name
    }

    private var myUri: Uri? = null

    fun getUri(): Uri {
        myUri = Uri.parse(CommonUtils.getPref(M005SettingFrag.IMG_PATH))
        return myUri!!
    }
}