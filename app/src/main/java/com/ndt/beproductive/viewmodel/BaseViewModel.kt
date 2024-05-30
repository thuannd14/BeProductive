package com.ndt.beproductive.viewmodel

import androidx.lifecycle.ViewModel
import com.ndt.beproductive.App

abstract class BaseViewModel : ViewModel() {
    companion object {
        val TAG: String = BaseViewModel::class.java.name
    }

    protected var myDB = App.instance.getDB()
}