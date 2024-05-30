package com.ndt.beproductive

import android.app.Application
import com.ndt.beproductive.db.DBNote

class App : Application() {
    private lateinit var storage: Storage
    private lateinit var myDB: DBNote

    companion object {
        lateinit var instance: App
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        storage = Storage()
        myDB = DBNote(this)
    }

    fun getStorage(): Storage {
        return storage
    }

    fun getDB(): DBNote {
        return myDB
    }
}