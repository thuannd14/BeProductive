package com.ndt.beproductive.viewmodel

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64
import android.util.Log
import com.ndt.beproductive.CommonUtils
import com.ndt.beproductive.fragment.M005SettingFrag


class M005SettingVM : BaseViewModel() {

    companion object {
        val TAG: String = M005SettingVM::class.java.name
    }

    fun saveImgPath(imgPath: Bitmap) {
        CommonUtils.savePref(M005SettingFrag.IMG_PATH, imgPath.toString())
        Log.i(TAG, "IMG: $imgPath")
    }

    private fun getImgPath(): String {
        if (CommonUtils.getPref(M005SettingFrag.IMG_PATH) == null) {
            return "Null"
        }
        return CommonUtils.getPref(M005SettingFrag.IMG_PATH)!!
    }

    fun convertToBitMap(): Bitmap {
        val decodedString: ByteArray = Base64.decode(getImgPath(), Base64.URL_SAFE)
        return BitmapFactory.decodeByteArray(decodedString, 0, decodedString.size)
    }
}