package com.ndt.beproductive.db

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import com.ndt.beproductive.model.Note

class DBNote(context: Context) : SQLiteOpenHelper(context, DB_NAME, null, DB_VERSION) {
    companion object {
        val TAG: String = DBNote::class.java.name
        const val DB_NAME = "NOTE_DB"
        const val DB_VERSION = 1
        const val TABLE_NAME = "NOTE_TABLE"
        const val COL_ID = "ID"
        const val COL_NOTE = "NOTE"
        const val COL_STATUS = "STATUS"
        const val COL_DATE = "DATE"
        const val COL_COLOR = "COLOR"

    }

    private lateinit var dbNote: SQLiteDatabase

    // Viết những câu lệnh tạo bảng. Gọi khi db đã đc tạo.
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL("CREATE TABLE IF NOT EXISTS $TABLE_NAME(ID INTEGER PRIMARY KEY AUTOINCREMENT , NOTE TEXT , STATUS INTEGER,DATE TEXT, COLOR INTEGER)")
    }

    // Được gọi khi db được nâng cấp. CRUD.
    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }

    // Them Note.
    fun insertNote(note: Note) {
        dbNote = this.writableDatabase
        val values = ContentValues()
        values.put(COL_NOTE, note.getContent())
        values.put(COL_STATUS, 0)
        values.put(COL_DATE, note.getDateTime())
        values.put(COL_COLOR, note.getColor())
        dbNote.insert(TABLE_NAME, null, values)
    }

    // update note.
    // update noi dung cua note.
    fun updateNote(id: Int, contentNote: String, dateTime: String, color: Int) {
        dbNote = this.writableDatabase
        val values = ContentValues()
        values.put(COL_NOTE, contentNote)
        values.put(COL_DATE, dateTime)
        values.put(COL_COLOR, color)
        dbNote.update(TABLE_NAME, values, "ID=?", arrayOf(id.toString()))
    }

    // update status note
    fun updateStatus(id: Int, status: Int) {
        dbNote = this.writableDatabase
        val values = ContentValues()
        values.put(COL_STATUS, status)
        dbNote.update(TABLE_NAME, values, "ID=?", arrayOf(id.toString()))
    }

    // xoa note.
    fun deleteNote(id: Int) {
        dbNote = this.writableDatabase
        dbNote.delete(TABLE_NAME, "ID=?", arrayOf(id.toString()))
    }

    // Hien thi cac note len RecyclerView.
    // tra ve la ds note lay tu db.
    @SuppressLint("Range")
    fun showNote(): MutableList<Note> {
        dbNote = this.writableDatabase
        var cursor: Cursor? = null
        var noteList: MutableList<Note> = ArrayList<Note>()
        dbNote.beginTransaction()
        try {
            cursor = dbNote.query(TABLE_NAME, null, null, null, null, null, null)
            if (cursor != null) {
                if (cursor.moveToFirst()) {
                    do {
                        val note = Note()
                        note.setID(cursor.getInt(cursor.getColumnIndex(COL_ID)))
                        note.setContent(cursor.getString(cursor.getColumnIndex(COL_NOTE)))
                        note.setDateTime(cursor.getString(cursor.getColumnIndex(COL_STATUS)))
                        note.setColor(cursor.getInt(cursor.getColumnIndex(COL_COLOR)))
                        Log.i(TAG, "DONE!!")
                    } while (cursor.moveToNext())
                }
            }
        } finally {
            dbNote.endTransaction()
            cursor!!.close()
        }
        return noteList
    }
}