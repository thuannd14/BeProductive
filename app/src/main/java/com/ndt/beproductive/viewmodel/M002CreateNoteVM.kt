package com.ndt.beproductive.viewmodel

import android.annotation.SuppressLint
import android.util.Log
import com.ndt.beproductive.model.Note
import java.text.SimpleDateFormat
import java.time.Instant
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale

class M002CreateNoteVM : BaseViewModel() {
    companion object {
        val TAG: String = M002CreateNoteVM::class.java.name
        const val SAVE_CONTENT = "SAVE_CONTENT"

        @SuppressLint("ConstantLocale")
        private val sdf = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault())
    }


    fun saveNoteColor(content: String, colorValue: Int) {

        // lay ngay+gio tu thiet bi.
        val instant = Instant.now()
        val zoneId = ZoneId.systemDefault()
        val zonedDateTime = ZonedDateTime.ofInstant(instant, zoneId)
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
        val formattedDateTime = zonedDateTime.format(formatter)

        val note = Note()
        note.setContent(content)
        note.setColor(colorValue)
        note.setDateTime(formattedDateTime)
        myDB.insertNote(note)
        Log.i(TAG, "saveContent: $content \n $colorValue \n $formattedDateTime")
    }
}