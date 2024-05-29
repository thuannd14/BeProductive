package com.ndt.beproductive

import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class AddNewNote: BottomSheetDialogFragment() {
    companion object {
        val TAG: String = AddNewNote::class.java.name
        fun getInstance(): AddNewNote {
            return AddNewNote()
        }
    }


}