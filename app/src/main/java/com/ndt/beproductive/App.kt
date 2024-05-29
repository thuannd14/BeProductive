package com.ndt.beproductive

import android.app.Application

class App : Application() {
    private lateinit var storage: Storage

    companion object {
        lateinit var instance: App
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        storage = Storage()
    }

    fun getStorage(): Storage {
        return storage
    }
}