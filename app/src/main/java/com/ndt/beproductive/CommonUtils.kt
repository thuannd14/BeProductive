package com.ndt.beproductive

import android.content.Context
import android.content.SharedPreferences

object CommonUtils {
    private const val PREF_FILE = "pref_saving"
    private var pref: SharedPreferences =
        App.instance.getSharedPreferences(PREF_FILE, Context.MODE_PRIVATE)

    fun savePref(key: String?, value: String?) {
        pref.edit().putString(key, value).apply()
    }


    fun getPref(key: String?): String? {
        return pref.getString(key, null)
    }

    fun removePref(key: String?) {
        pref.edit().remove(key).apply()
    }
}
