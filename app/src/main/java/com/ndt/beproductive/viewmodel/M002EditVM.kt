package com.ndt.beproductive.viewmodel

import android.util.Log
import com.ndt.beproductive.App

class M002EditVM : BaseViewModel() {
    companion object {
        val TAG: String = M002EditVM::class.java.name
    }

    fun getUpdated(id: Int, contentNew: String, color: Int) {
        Log.i(TAG, "ID: $id content: $contentNew color: $id")
        myDB.updateNote(id, contentNew, color)
        App.instance.getStorage().content = contentNew
        App.instance.getStorage().color = color
    }
}