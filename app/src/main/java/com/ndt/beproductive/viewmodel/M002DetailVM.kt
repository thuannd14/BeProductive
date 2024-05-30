package com.ndt.beproductive.viewmodel

import com.ndt.beproductive.App

class M002DetailVM: BaseViewModel() {
    companion object{
        val TAG: String = M002DetailVM::class.java.name
    }

    private var contentNote = App.instance.getStorage().content
    //private var dateTimeNote = App.instance.getStorage().dateTime
}