package com.ndt.beproductive.viewmodel

import android.util.Log
import com.ndt.beproductive.model.Note

class M002TakingEmptyVM : BaseViewModel() {
    companion object {
        val TAG: String = M002TakingEmptyVM::class.java.name
    }

    private lateinit var noteListDB: MutableList<Note>

    fun getNoteList(): MutableList<Note> {
        noteListDB = ArrayList<Note>()
        noteListDB = myDB.showNote()
        Log.i(TAG, "Size ${noteListDB.size}")
        return noteListDB
    }
}