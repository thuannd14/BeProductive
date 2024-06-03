package com.ndt.beproductive.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.ndt.beproductive.App
import com.ndt.beproductive.R

class ChangeThemeAdapter(private var mContext: Context, private val imageList: Array<Int>) :
    RecyclerView.Adapter<ChangeThemeAdapter.ThemeHolder>() {

    companion object {
        val TAG: String = ChangeThemeAdapter::class.java.name
    }

    private var index: Int = 0


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ThemeHolder {
        val view = LayoutInflater.from(mContext).inflate(R.layout.item_change_theme, parent, false)
        return ThemeHolder(view)
    }

    override fun onBindViewHolder(
        holder: ThemeHolder,
        @SuppressLint("RecyclerView") position: Int
    ) {
        Log.i(TAG, "Pos: $position")
        holder.ivTheme.setImageResource(imageList[position])
        index = position
    }

    override fun getItemCount(): Int {
        return imageList.size
    }


    inner class ThemeHolder(view: View) : ViewHolder(view) {
        val ivTheme: ImageView = view.findViewById<ImageView>(R.id.iv_change_theme)

        init {
            ivTheme.setOnClickListener {
                it.startAnimation(
                    AnimationUtils.loadAnimation(
                        mContext, androidx.appcompat.R.anim.abc_fade_in
                    )
                )
                Log.i(TAG, "index: $index")
                //App.instance.getStorage().indexImg = index
            }
        }
    }
}