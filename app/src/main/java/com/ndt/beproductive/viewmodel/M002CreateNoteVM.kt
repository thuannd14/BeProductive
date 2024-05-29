package com.ndt.beproductive.viewmodel

import android.util.Log
import com.ndt.beproductive.CommonUtils
import com.ndt.beproductive.db.DBNote
import com.ndt.beproductive.model.Note

class M002CreateNoteVM : BaseViewModel() {
    companion object {
        val TAG: String = M002CreateNoteVM::class.java.name
        const val SAVE_CONTENT = "SAVE_CONTENT"
    }

    private lateinit var myDB: DBNote

    fun saveNote(content: String){
        Log.i(TAG, "saveContent: $content")
        var note = Note()
        note.setContent(content)
        myDB.insertNote(note)
    }
}