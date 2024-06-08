package com.ndt.beproductive.viewmodel

import android.util.Log
import com.ndt.beproductive.App

class M003MainFocusVM : BaseViewModel() {
    companion object {
        val TAG: String = M003MainFocusVM::class.java.name
    }

    // Loi khi vao thi indexImg se chua duoc xet.
    // nen de tranh loi thi dat mac dinh la index = 0.

    // cach sua: dat theme mac dinh la anh dau tien cua danh sach.
    // index = 0 tra ve cho fragment hien thi.
    fun getIndexImg(): Int {
        if (App.instance.getStorage().indexImg == null) {
            App.instance.getStorage().indexImg = 0
        }
        val index = App.instance.getStorage().indexImg!!
        Log.i(M003ChangeThemeVM.TAG, "Index: $index")
        return index
    }
}