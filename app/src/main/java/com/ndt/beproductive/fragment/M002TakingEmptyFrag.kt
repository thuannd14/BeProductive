package com.ndt.beproductive.fragment

import android.graphics.PorterDuff
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import com.ndt.beproductive.App
import com.ndt.beproductive.R
import com.ndt.beproductive.RecyclerViewTouchHelper
import com.ndt.beproductive.adapter.NoteAdapter
import com.ndt.beproductive.databinding.M002NoteTakingEmptyFragBinding
import com.ndt.beproductive.model.Note
import com.ndt.beproductive.viewmodel.M002TakingEmptyVM

class M002TakingEmptyFrag : BaseFrag<M002NoteTakingEmptyFragBinding, M002TakingEmptyVM>() {
    companion object {
        val TAG: String = M002TakingEmptyFrag::class.java.name
        val ALL_NOTES = 1
        val FOCUS_TIME = 2
        val EXPLORE = 3
        val SETTING = 4
    }

    private lateinit var adapter: NoteAdapter
    private lateinit var noteList: MutableList<Note> // Luu cac note lay tu viewmodel.

    // xu li adapter.
    override fun initViews() {
        val color = ContextCompat.getColor(mContext, R.color.light_blue)
        binding.includeMenu.ivNotes.setColorFilter(color, PorterDuff.Mode.SRC_IN)
        // xu lí khi click vào 1 chức năng thanh menu thì đổi màu.
        changeMenu(color)

        // add notes.
        binding.ivAddNotes.setOnClickListener {
            Log.i(TAG, "Add notes")
            mCallBack.showFrag(M002CreateNoteFrag.TAG, null, true)
        }

        binding.includeMenu.ivSetting.setOnClickListener {
            mCallBack.showFrag(M005SettingFrag.TAG, null, true)
        }
        // Lay ds note tu vm.
        noteList = viewModel.getNoteList()
        Log.i(TAG, "NOTE LIST: $noteList")

        setAdapterOnRv()


    }


    private fun changeMenu(color: Int) {
        if (R.id.iv_notes == ALL_NOTES) {
            binding.includeMenu.ivNotes.setOnClickListener {
                it.startAnimation(
                    AnimationUtils.loadAnimation(
                        mContext, androidx.appcompat.R.anim.abc_fade_in
                    )
                )
                binding.includeMenu.ivNotes.setColorFilter(color, PorterDuff.Mode.SRC_IN)
                mCallBack.showFrag(TAG, null, true)
            }
        } else if (R.id.iv_explore == EXPLORE) {
            binding.includeMenu.ivExplore.setOnClickListener {
                it.startAnimation(
                    AnimationUtils.loadAnimation(
                        mContext, androidx.appcompat.R.anim.abc_fade_in
                    )
                )
                binding.includeMenu.ivExplore.setColorFilter(color, PorterDuff.Mode.SRC_IN)
                mCallBack.showFrag(TAG, null, true)
            }
        } else if (R.id.iv_pomodoro == EXPLORE) {
            binding.includeMenu.ivPomodoro.setOnClickListener {
                it.startAnimation(
                    AnimationUtils.loadAnimation(
                        mContext, androidx.appcompat.R.anim.abc_fade_in
                    )
                )
                binding.includeMenu.ivPomodoro.setColorFilter(color, PorterDuff.Mode.SRC_IN)
                mCallBack.showFrag(TAG, null, true)
            }
        } else if (R.id.iv_setting == EXPLORE) {
            binding.includeMenu.ivSetting.setOnClickListener {
                it.startAnimation(
                    AnimationUtils.loadAnimation(
                        mContext, androidx.appcompat.R.anim.abc_fade_in
                    )
                )
                binding.includeMenu.ivSetting.setColorFilter(color, PorterDuff.Mode.SRC_IN)
                mCallBack.showFrag(TAG, null, true)
            }
        }
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


