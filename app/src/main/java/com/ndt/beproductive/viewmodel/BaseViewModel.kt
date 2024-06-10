package com.ndt.beproductive.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.ndt.beproductive.App
import com.ndt.beproductive.OnCallbackAPI
import com.ndt.beproductive.R
import com.ndt.beproductive.api.API
import com.ndt.beproductive.fragment.BaseFrag
import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

abstract class BaseViewModel : ViewModel() {
    companion object {
        const val BASE_URL = "https://newsapi.org/v2/"
        val TAG: String = BaseViewModel::class.java.name
    }

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

    protected var myDB = App.instance.getDB()
    protected lateinit var mActionCallBackAPI: OnCallbackAPI // call back cho Api

    fun setActionCallBack(actionCallBack: OnCallbackAPI?) {
        this.mActionCallBackAPI = actionCallBack!!
    }


    protected open fun getAPI(): API {
        val retrofit: Retrofit = Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(
                // cau hinh OkHttpClient quan li cac request va response.
                OkHttpClient.Builder().callTimeout(30, TimeUnit.SECONDS).build()
            ).build()
        // anh xa api.
        return retrofit.create(API::class.java)
    }

    protected open fun <T> initHandleRespone(key: String?): Callback<T>? {
        return object : Callback<T> {
            override fun onResponse(call: Call<T>, response: Response<T>) {
                if (response.code() == 200 || response.code() == 201) {
                    handleSuccess(key, response.body())
                    Log.i(
                        TAG,
                        "Thanh cong!-----" + response.code() + response.body()
                    )
                } else {
                    handleFail(key!!, response.code(), response.errorBody())
                }
            }

            override fun onFailure(call: Call<T>, t: Throwable) {
                // Loi ngoai le thi xu li trong day.
                Log.e(TAG, "onFailure-----" + t.message)
                mActionCallBackAPI.apiError(key!!, 999, t.message!!)
            }
        }
    }

    protected open fun handleFail(key: String, code: Int, requestBody: ResponseBody?) {
        Log.e(
            TAG, "handleFail-----" + key + " " + code + "data: " + requestBody
        )
        mActionCallBackAPI.apiError(key, code, requestBody!!)
    }

    protected open fun handleException(key: String?, t: Throwable) {
        Log.e(
            TAG, "handleException-----" + "data" + t.message
        )
    }

    protected open fun handleSuccess(key: String?, body: Any?) {
        mActionCallBackAPI.apiSuccess(key!!, body!!)
    }

}