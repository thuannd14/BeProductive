package com.ndt.beproductive

import android.app.AlertDialog
import android.content.DialogInterface
import android.graphics.Canvas
import androidx.compose.ui.graphics.Color
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.ndt.beproductive.adapter.NoteAdapter
import it.xabaras.android.recyclerview.swipedecorator.RecyclerViewSwipeDecorator

class RecyclerViewTouchHelper(private var adapter: NoteAdapter) :
    ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
    companion object {
        val TAG: String = RecyclerViewTouchHelper::class.java.name
    }

    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {
        return true
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        val pos = viewHolder.bindingAdapterPosition // Lấy vị trí của item trong rv.
        if(direction == ItemTouchHelper.RIGHT){
            val builder: AlertDialog.Builder = AlertDialog.Builder(adapter.getContext())
            builder.setTitle("Delete Note")
            builder.setMessage("Are you sure?")
            builder.setPositiveButton("Yes", DialogInterface.OnClickListener {
                    dialog, which -> adapter.deleteNote(pos) }
            )
            builder.setNegativeButton("Cancel", DialogInterface.OnClickListener {
                    dialog, which -> adapter.notifyItemChanged(pos) })

            val dialog: AlertDialog = builder.create()
            dialog.show()
        }
        else if (direction == ItemTouchHelper.LEFT){
            adapter.editNote(pos)
        }
    }

    override fun onChildDraw(
        c: Canvas,
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        dX: Float,
        dY: Float,
        actionState: Int,
        isCurrentlyActive: Boolean
    ) {
        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
        RecyclerViewSwipeDecorator.Builder(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
            .addSwipeLeftBackgroundColor(
                ContextCompat.getColor(
                    adapter.getContext(),
                    R.color.purple
                )
            )
            .addSwipeLeftActionIcon(R.drawable.ic_base_edit)
            .addSwipeRightBackgroundColor(android.graphics.Color.GRAY)
            .addSwipeRightActionIcon(R.drawable.ic_baseline_delete)
            .addCornerRadius(20, 20)
            .addPadding(10, 10F, 10F, 10F)
            .create()
            .decorate()
    }
}