package com.ndt.beproductive.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.ndt.beproductive.AddNewNote
import com.ndt.beproductive.R
import com.ndt.beproductive.db.DBNote
import com.ndt.beproductive.model.Note

class NoteAdapter(
    private var context: Context,
    private var noteList: MutableList<Note>,
    private var myDB: DBNote
) : RecyclerView.Adapter<NoteAdapter.NoteHolder>() {

    companion object{
        const val ID = "ID"
        const val CONTENT = "CONTENT"
        const val DATE_TIME = "CONTENT"
        const val COLOR = "COLOR"

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_note, parent, false)
        return NoteHolder(view)
    }

    override fun onBindViewHolder(holder: NoteHolder, position: Int) {
        val note: Note = noteList[position]
        holder.tvContent.text = note.getContent()
        holder.tvDateTime.text = note.getDateTime()
    }

    override fun getItemCount(): Int {
        return noteList.size
    }

    // add task moi.
    @SuppressLint("NotifyDataSetChanged")
    fun addNote(mList: MutableList<Note>){
        noteList = mList
        notifyDataSetChanged()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun deleteNote(pos: Int){
        val noteItem = noteList[pos]
        myDB.deleteNote(noteItem.getID())
        noteList.removeAt(pos)
        notifyDataSetChanged()
    }

    fun editNote(pos: Int){
        val itemNote = noteList[pos]
        val contentNoteBundle = Bundle()
        contentNoteBundle.putInt(ID, itemNote.getID()) // id cua note can edit.
        contentNoteBundle.putString(CONTENT, itemNote.getContent())
        contentNoteBundle.putString(DATE_TIME, itemNote.getDateTime())
        contentNoteBundle.putInt(COLOR, itemNote.getColor())

        val note = AddNewNote()
        note.arguments = contentNoteBundle
        // yeu cau la 1 fragmentmanagement.
        if (context is FragmentActivity) {
            note.show((context as FragmentActivity).supportFragmentManager, AddNewNote.TAG)
        }
    }

    fun checkStatus(isFlag: Int): Boolean{
        return isFlag != 0
    }

    inner class NoteHolder(view: View) : ViewHolder(view) {
        val tvContent: TextView = view.findViewById(R.id.tv_content_note)
        val tvDateTime: TextView = view.findViewById(R.id.tv_date)
    }
}