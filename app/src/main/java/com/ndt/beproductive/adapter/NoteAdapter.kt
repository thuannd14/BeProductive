package com.ndt.beproductive.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.LinearLayout
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
    private var ldNote: MutableLiveData<Note> = MutableLiveData<Note>()
    private var liveDataID: MutableLiveData<Int> = MutableLiveData<Int>()

    fun getLiveDataContentNote(): MutableLiveData<String> {
        return liveDataNote
    }

    fun getNote(): MutableLiveData<Note> {
        return ldNote
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
        holder.tvDateTime.text = note.getDateTime()
        holder.lnContentBg.setBackgroundColor(note.getColor())
        holder.itemView.tag = note
        //App.instance.getStorage().id = note.getID() // Neu de the nao se la lay id cua tk dau ds.
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
        val tvDateTime: TextView = view.findViewById(R.id.tv_date)
        val lnContentBg: LinearLayout = view.findViewById(R.id.ln_content)

        init {
            view.setOnClickListener {
                Log.i(TAG, "${view.tag}")
                it.startAnimation(
                    AnimationUtils.loadAnimation(
                        context, androidx.appcompat.R.anim.abc_popup_enter
                    )
                )
                goToDetailNote(view.tag as Note)
            }
        }
    }

    private fun goToDetailNote(note: Note) {
        getNote().postValue(note)
        // day moi la cap nhat id dung cai can chon.
        App.instance.getStorage().id = note.getID()
        Log.i(TAG, "Success ${note}!")
    }
}