package com.ndt.beproductive

import android.content.Context
import android.content.SharedPreferences

class CommonUtils {
    companion object {
        private val PREF_FILE = "pref_saving" // noi luu
        var instance: CommonUtils? = null
    }


    fun getInstance(): CommonUtils {
        if (instance == null){
            instance = CommonUtils()
        }
        return instance as CommonUtils
    }

    fun savePref(key: String?, value: String?) {
        val sharedPreferences: SharedPreferences =
            App.instance.getSharedPreferences(PREF_FILE, Context.MODE_PRIVATE)
        sharedPreferences.edit().putString(key, value).apply()
    }

    fun getPref(key: String?): String? {
        val sharedPreferences: SharedPreferences =
            App.instance.getSharedPreferences(PREF_FILE, Context.MODE_PRIVATE)
        return sharedPreferences.getString(key, null)
    }

    fun removePref(key: String?) {
        val sharedPreferences: SharedPreferences =
            App.instance.getSharedPreferences(PREF_FILE, Context.MODE_PRIVATE)
        sharedPreferences.edit().remove(key).apply()
    }
}