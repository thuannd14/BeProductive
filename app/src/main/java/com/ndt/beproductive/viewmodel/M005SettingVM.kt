package com.ndt.beproductive.viewmodel

import android.net.Uri
import android.util.Log

class M005SettingVM : BaseViewModel() {

    companion object {
        val TAG: String = M005SettingVM::class.java.name
    }

    private var imgUri: Uri? = null

    fun setImgUri(uri: Uri) {
        this.imgUri = uri
    }

    fun getImgUri(): Uri {
        Log.i(TAG, "Uri: $imgUri")
        return imgUri!!
    }
}