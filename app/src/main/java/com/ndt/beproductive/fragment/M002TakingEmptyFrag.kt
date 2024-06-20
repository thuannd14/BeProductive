package com.ndt.beproductive.fragment

import android.graphics.PorterDuff
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import com.ndt.beproductive.App
import com.ndt.beproductive.R
import com.ndt.beproductive.RecyclerViewTouchHelper
import com.ndt.beproductive.act.MainActivity
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


    override fun initViews() {

        // Lay ds note tu vm.
        noteList = viewModel.getNoteList()
        Log.i(TAG, "NOTE LIST: $noteList")

        // add note.
        binding.ivAddNotes.setOnClickListener {
            mCallBack.showFrag(M002CreateNoteFrag.TAG, null, true)
        }

        changMenu()
        setAdapterOnRv()

    }

    private fun changMenu() {
        binding.includeMenu.ivNotes.setOnClickListener(this)
        binding.includeMenu.ivPomodoro.setOnClickListener(this)
        binding.includeMenu.ivExplore.setOnClickListener(this)
        binding.includeMenu.ivSetting.setOnClickListener(this)
        binding.includeMenu.ivRoom.setOnClickListener(this)

        changeColor(MainActivity.ALL_NOTES, colorBlue, colorBlack)
    }

    override fun clickViews(v: View?) {
        if (v?.id == R.id.iv_notes) {
            changeColor(MainActivity.ALL_NOTES, colorBlue, colorBlack)
            mCallBack.showFrag(TAG, null, true)
        } else if (v?.id == R.id.iv_pomodoro) {
            changeColor(MainActivity.FOCUS_TIME, colorBlue, colorBlack)
            mCallBack.showFrag(M003MainFocusFrag.TAG, null, true)
        } else if (v?.id == R.id.iv_explore) {
            changeColor(MainActivity.EXPLORE, colorBlue, colorBlack)
            mCallBack.showFrag(M004ExploreFrag.TAG, null, true)
        } else if (v?.id == R.id.iv_setting) {
            changeColor(MainActivity.SETTING, colorBlue, colorBlack)
            mCallBack.showFrag(M005SettingFrag.TAG, null, true)
        } else if (v?.id == R.id.iv_room) {
            changeColor(MainActivity.DATA, colorBlue, colorBlack)
            mCallBack.showFrag(M008JoinFrag.TAG, null, true)
        }
    }

    private fun changeColor(key: Int, colorBlue: Int, colorBlack: Int) {
        val alphaNote = if (key == MainActivity.ALL_NOTES) colorBlue else colorBlack
        binding.includeMenu.ivNotes.setColorFilter(alphaNote, PorterDuff.Mode.SRC_IN)

        val alphaLFocus = if (key == MainActivity.FOCUS_TIME) colorBlue else colorBlack
        binding.includeMenu.ivPomodoro.setColorFilter(alphaLFocus, PorterDuff.Mode.SRC_IN)

        val alphaExplore = if (key == MainActivity.EXPLORE) colorBlue else colorBlack
        binding.includeMenu.ivExplore.setColorFilter(alphaExplore, PorterDuff.Mode.SRC_IN)

        val alphaSetting = if (key == MainActivity.SETTING) colorBlue else colorBlack
        binding.includeMenu.ivSetting.setColorFilter(alphaSetting, PorterDuff.Mode.SRC_IN)

        val alphaData = if (key == MainActivity.DATA) colorBlue else colorBlack
        binding.includeMenu.ivRoom.setColorFilter(alphaData, PorterDuff.Mode.SRC_IN)

    }


    private fun setAdapterOnRv() {
        adapter = NoteAdapter(mContext, noteList, App.instance.getDB())
        binding.rvAllNotes.setHasFixedSize(false)
        binding.rvAllNotes.layoutManager = LinearLayoutManager(mContext)
        binding.rvAllNotes.adapter = adapter

        noteList = viewModel.getNoteList()
        noteList.reverse()
        adapter.addNote(noteList)

        // xu lí vuot sang để xóa hoac edit.
        val itemTouchHelper = ItemTouchHelper(RecyclerViewTouchHelper(adapter))
        itemTouchHelper.attachToRecyclerView(binding.rvAllNotes)

        // mo 1 note trong recyclerview.
        adapter.getNote().observe(viewLifecycleOwner, Observer<Note> {
            saveNoteToDetail(it)
        })
    }

    // Luu toan bo noi dung note: id, content, color, status vao Storage.
    private fun saveNoteToDetail(note: Note) {
        viewModel.saveDetail(note)
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


