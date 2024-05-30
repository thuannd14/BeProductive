package com.ndt.beproductive.fragment

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.ndt.beproductive.App
import com.ndt.beproductive.adapter.NoteAdapter
import com.ndt.beproductive.databinding.M002NoteTakingEmptyFragBinding
import com.ndt.beproductive.model.Note
import com.ndt.beproductive.viewmodel.M002TakingEmptyVM

class M002TakingEmptyFrag : BaseFrag<M002NoteTakingEmptyFragBinding, M002TakingEmptyVM>() {
    companion object {
        val TAG: String = M002TakingEmptyFrag::class.java.name
    }

    private lateinit var adapter: NoteAdapter
    private lateinit var noteList: MutableList<Note> // Luu cac note lay tu viewmodel.

    // xu li adapter.
    override fun initViews() {
        // add notes.
        binding.ivAddNotes.setOnClickListener {
            Log.i(TAG, "Add notes")
            mCallBack.showFrag(M002CreateNoteFrag.TAG, null, true)
        }
        // Lay ds note tu vm.
        noteList = viewModel.getNoteList()
        Log.i(TAG, "NOTE LIST: $noteList")

        setAdapterOnRv()


    }

    private fun setAdapterOnRv() {
        adapter = NoteAdapter(mContext, noteList, App.instance.getDB())
        binding.rvAllNotes.setHasFixedSize(false)
        binding.rvAllNotes.layoutManager = LinearLayoutManager(mContext)
        binding.rvAllNotes.adapter = adapter

        noteList = viewModel.getNoteList()
        noteList.reverse()
        adapter.addNote(noteList)


        // mo 1 note trong recyclerview.
        adapter.getLiveDataNote().observe(viewLifecycleOwner, Observer<String> {
            saveNoteToDetail(it)
        })
    }

    // Luu toan bo noi dung note: id, content, color, status vao Storage.
    private fun saveNoteToDetail(content: String) {
        App.instance.getStorage().content = content
        //App.instance.getStorage().dateTime = note.getDateTime()
        Log.i(TAG, "Save Note:${App.instance.getStorage().content}")
        mCallBack.showFrag(M002DetailNoteFragment.TAG, null, true)
    }

    override fun initViewBinding(
        inflater: LayoutInflater, container: ViewGroup?
    ): M002NoteTakingEmptyFragBinding {
        return M002NoteTakingEmptyFragBinding.inflate(inflater, container, false)
    }

    override fun getClassVM(): Class<M002TakingEmptyVM> {
        return M002TakingEmptyVM::class.java
    }
}


