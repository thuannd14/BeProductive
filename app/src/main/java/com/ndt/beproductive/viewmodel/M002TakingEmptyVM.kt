package com.ndt.beproductive.viewmodel

import android.util.Log
import com.ndt.beproductive.App
import com.ndt.beproductive.CommonUtils
import com.ndt.beproductive.fragment.BaseFrag.Companion.USER_NAME
import com.ndt.beproductive.model.Note

class M002TakingEmptyVM : BaseViewModel() {
    companion object {
        val TAG: String = M002TakingEmptyVM::class.java.name
    }

    private lateinit var noteListDB: MutableList<Note>

    fun getNoteList(): MutableList<Note> {
        noteListDB = ArrayList()
        noteListDB = myDB.showNote(CommonUtils.getPref(USER_NAME) ?: "")
        Log.i(TAG, "Size ${noteListDB.size}")
        return noteListDB
    }

    fun saveDetail(note: Note) {
        App.instance.getStorage().content = note.getContent()
        App.instance.getStorage().color = note.getColor()
        App.instance.getStorage().dateTime = note.getDateTime()
        Log.i(
            TAG, "Save Note:${App.instance.getStorage().content}" +
                    "\n${App.instance.getStorage().color} " +
                    "\n${App.instance.getStorage().dateTime}"
        )
    }
}