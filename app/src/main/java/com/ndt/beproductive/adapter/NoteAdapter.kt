package com.ndt.beproductive.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.ndt.beproductive.App
import com.ndt.beproductive.R
import com.ndt.beproductive.db.DBNote
import com.ndt.beproductive.model.Note

class NoteAdapter(
    private var context: Context, private var noteList: MutableList<Note>, private var myDB: DBNote
) : RecyclerView.Adapter<NoteAdapter.NoteHolder>() {

    companion object {
        val TAG: String = NoteAdapter::class.java.name
        const val ID = "ID"
        const val CONTENT = "CONTENT"
        const val DATE_TIME = "CONTENT"
        const val COLOR = "COLOR"
    }

    fun getContext(): Context {
        return context
    }

    private var liveDataNote: MutableLiveData<String> = MutableLiveData<String>()
    private var liveDataID: MutableLiveData<Int> = MutableLiveData<Int>()

    fun getLiveDataNote(): MutableLiveData<String> {
        return liveDataNote
    }

    fun getLiveDataID(): MutableLiveData<Int> {
        return liveDataID
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_note, parent, false)
        return NoteHolder(view)
    }

    override fun onBindViewHolder(holder: NoteHolder, position: Int) {
        val note: Note = noteList[position]
        holder.tvContent.text = note.getContent()
        //holder.tvDateTime.text = note.getDateTime()
        holder.itemView.tag = note.getContent()
        App.instance.getStorage().id = note.getID()

        Log.i(TAG, "${note.getContent()} \n ${note.getDateTime()} \n ${note.getID()} ")
    }


    override fun getItemCount(): Int {
        Log.i(TAG, "SIze: ${noteList.size}")
        return noteList.size
    }

    // add task moi.
    @SuppressLint("NotifyDataSetChanged")
    fun addNote(mList: MutableList<Note>) {
        noteList = mList
        notifyDataSetChanged()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun deleteNote(pos: Int) {
        val noteItem = noteList[pos]
        myDB.deleteNote(noteItem.getID())
        noteList.removeAt(pos)
        notifyDataSetChanged()
    }

    private var id: Int = 0

    fun editNote(pos: Int) {
        val itemNote = noteList[pos]
        val idNote = itemNote.getID()
        App.instance.getStorage().id = idNote
        Log.i(TAG, "id note: $idNote")
    }

    fun checkStatus(isFlag: Int): Boolean {
        return isFlag != 0
    }

    inner class NoteHolder(view: View) : ViewHolder(view) {
        val tvContent: TextView = view.findViewById(R.id.tv_content_note)
        //val tvDateTime: TextView = view.findViewById(R.id.tv_date)

        init {
            view.setOnClickListener {
                Log.i(TAG, "${view.tag}")
                goToDetailNote(view.tag as String)
            }
        }
    }

    private fun goToDetailNote(noteContent: String) {
        getLiveDataNote().postValue(noteContent)
        Log.i(TAG, "Success ${noteContent}!")
    }
}