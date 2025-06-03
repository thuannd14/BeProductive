package com.ndt.beproductive.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.content.ContentValues
import android.util.Log

class DBUser(context: Context) : SQLiteOpenHelper(context, DB_NAME, null, DB_VERSION) {
    companion object {
        const val DB_NAME = "USER_DB"
        const val DB_VERSION = 1
        const val TABLE_NAME = "USER_TABLE"
        const val COL_ID = "ID"            // dùng như username
        const val COL_PASSWORD = "PASSWORD"
        val TAG: String = DBUser::class.java.name
    }

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(
            "CREATE TABLE IF NOT EXISTS $TABLE_NAME (" +
                    "$COL_ID TEXT PRIMARY KEY, " +
                    "$COL_PASSWORD TEXT)"
        )

        // Tạo sẵn tài khoản mặc định
        val defaultUsername = "admin"
        val defaultPassword = "123456"

        val insertUser = "INSERT INTO $TABLE_NAME ($COL_ID, $COL_PASSWORD) VALUES (?, ?)"
        db?.execSQL(insertUser, arrayOf(defaultUsername, defaultPassword))

        Log.i(TAG, "Tài khoản mặc định được tạo: $defaultUsername / $defaultPassword")
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }

    fun insertUser(userName: String,password: String): Boolean {
        if (isUsernameExists(userName)) {
            return false // Không thêm được vì đã tồn tại
        }

        val db = writableDatabase
        val values = ContentValues()
        values.put(COL_ID, userName)
        values.put(COL_PASSWORD, password)
        val result = db.insert(TABLE_NAME, null, values)
        db.close()
        return result != -1L
    }

    fun isValidUser(username: String, password: String): Boolean {
        val db = readableDatabase
        val cursor = db.rawQuery(
            "SELECT * FROM $TABLE_NAME WHERE $COL_ID = ? AND $COL_PASSWORD = ?",
            arrayOf(username, password)
        )
        val isValid = cursor.count > 0
        cursor.close()
        db.close()
        return isValid
    }
    fun isUsernameExists(username: String): Boolean {
        val db = readableDatabase
        val cursor = db.rawQuery(
            "SELECT * FROM $TABLE_NAME WHERE $COL_ID = ?",
            arrayOf(username)
        )
        val exists = cursor.count > 0
        cursor.close()
        db.close()
        return exists
    }

    fun updatePassword(userName: String, newPassword: String): Boolean {
        if( !isUsernameExists(userName) ) {
            return false // ko tồn tại user
        }

        val db = writableDatabase
        val values = ContentValues()
        values.put(COL_PASSWORD, newPassword)

        // Cập nhật password cho username tương ứng
        val result = db.update(TABLE_NAME, values, "$COL_ID = ?", arrayOf(userName))
        db.close()

        // Trả về true nếu có ít nhất 1 dòng được cập nhật
        return result > 0
    }

}
