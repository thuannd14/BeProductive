package com.ndt.beproductive.viewmodel

import android.util.Log
import com.ndt.beproductive.App

class M003MainFocusVM: BaseViewModel() {
    companion object {
        val TAG: String = M003MainFocusVM::class.java.name
    }

    fun getIndexImg(): Int {
        val index = App.instance.getStorage().indexImg!!
        Log.i(M003ChangeThemeVM.TAG, "Index: $index")
        return index
    }
}