package com.ndt.beproductive.viewmodel

class M008JoinVM: BaseViewModel() {

    fun getUrl(): String{
        return URL_BASE_MEETING
    }

    fun getToken(): String{
        return sampleToken
    }
}