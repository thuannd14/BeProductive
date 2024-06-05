package com.ndt.beproductive

import android.net.Uri

class Storage {
    companion object {
        val TAG: String = Storage::class.java.name
    }

    var id: Int? = null
    lateinit var content: String

    //lateinit var dateTime: String
    var color: Int? = null

    // Luu vi tri Int cua hinh anh trong list.
    var indexImg: Int? = null

    // Luu noi dung ghi chu o main focus screen.
    var contentText: String? = null

}