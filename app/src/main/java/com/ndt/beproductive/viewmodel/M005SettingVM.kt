package com.ndt.beproductive.viewmodel

import android.net.Uri
import com.ndt.beproductive.CommonUtils
import com.ndt.beproductive.fragment.M005SettingFrag


class M005SettingVM : BaseViewModel() {

    companion object {
        val TAG: String = M005SettingVM::class.java.name
    }

    fun saveUri(uri: Uri) {
        CommonUtils.savePref(M005SettingFrag.URI_PATH, uri.toString())
    }

    fun getUri(): Uri {
        val pathStr = CommonUtils.getPref(M005SettingFrag.URI_PATH)
        return Uri.parse(pathStr)
    }
}