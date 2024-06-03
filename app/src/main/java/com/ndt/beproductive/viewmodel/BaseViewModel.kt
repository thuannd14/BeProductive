package com.ndt.beproductive.viewmodel

import androidx.lifecycle.ViewModel
import com.ndt.beproductive.App
import com.ndt.beproductive.R

abstract class BaseViewModel : ViewModel() {
    companion object {
        val TAG: String = BaseViewModel::class.java.name
    }

    protected var myDB = App.instance.getDB()

    val ARR_IMG = arrayOf(
        R.mipmap.img_summer_night,
        R.mipmap.img_camping,
        R.mipmap.img_rain,
        R.mipmap.img_ocean,
        R.mipmap.img_forest,
        R.mipmap.img_wind,
        R.mipmap.img_cafe,
        R.mipmap.img_libarary,
        R.mipmap.img_stream,
        R.mipmap.img_storm,
        R.mipmap.img_dream,
        R.mipmap.img_sad_love
    )
}