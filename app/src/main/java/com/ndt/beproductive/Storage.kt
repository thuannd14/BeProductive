package com.ndt.beproductive

class Storage {
    companion object {
        val TAG: String = Storage::class.java.name
    }

    var id: Int? = null
    lateinit var content: String
    //lateinit var dateTime: String
    var color: Int? = null
}