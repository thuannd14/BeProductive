package com.ndt.beproductive.viewmodel

import android.util.Log
import com.ndt.beproductive.App

class M002EditVM : BaseViewModel() {
    companion object {
        val TAG: String = M002EditVM::class.java.name
    }

    private var idNote = App.instance.getStorage().id

    fun getUpdated(contentNew: String) {
        Log.i(TAG, "ID: $idNote content: $contentNew")
        myDB.updateNote(idNote!!, contentNew, "123", 123)
        App.instance.getStorage().content = contentNew
    }
}