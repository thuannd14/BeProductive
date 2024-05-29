package com.ndt.beproductive.model

import android.annotation.SuppressLint

class Note {
    private var id: Int = 0
    private lateinit var content: String
    private lateinit var dateTime: String
    private var color: Int = 0

    fun Note(id: Int, content: String, dateTime: String, color: Int) {
        this.id = id
        this.content = content
        this.dateTime = dateTime
        this.color = color
    }

    companion object {
        val TAG: String = Note::class.java.name
    }

    fun getContent(): String {
        return content
    }

    fun setContent(content: String) {
        this.content = content
    }

    fun getDateTime(): String {
        return dateTime
    }

    fun setDateTime(datetime: String) {
        this.dateTime = datetime
    }

    fun getID(): Int {
        return id
    }

    fun setID(id: Int) {
        this.id = id
    }

    fun getColor(): Int {
        return color
    }

    fun setColor(color: Int) {
        this.color = color
    }


    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Note

        if (id != other.id) return false
        if (content != other.content) return false
        if (dateTime != other.dateTime) return false
        if (color != other.color) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id
        result = 31 * result + content.hashCode()
        result = 31 * result + dateTime.hashCode()
        result = 31 * result + color
        return result
    }

    override fun toString(): String {
        return "ContentNote(id=$id, content='$content', dateTime='$dateTime', color=$color)"
    }
}