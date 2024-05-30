package com.ndt.beproductive.viewmodel

import android.util.Log
import com.ndt.beproductive.db.DBNote
import com.ndt.beproductive.model.Note

class M002CreateNoteVM : BaseViewModel() {
    companion object {
        val TAG: String = M002CreateNoteVM::class.java.name
        const val SAVE_CONTENT = "SAVE_CONTENT"
    }

    fun saveNote(content: String){
        Log.i(TAG, "saveContent: $content")
        val note = Note()
        note.setContent(content)
        myDB.insertNote(note)
    }
}