package com.ndt.beproductive.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.ndt.beproductive.R
import com.ndt.beproductive.api.reponse.PopularNews

class PopularAdapter(
    private var context: Context,
    private var articlesList: List<PopularNews.Articles?> = mutableListOf()
) : RecyclerView.Adapter<PopularAdapter.PopularHolder>() {
    companion object {
        val TAG: String = PopularAdapter::class.java.name
    }

    private val liveData = MutableLiveData<PopularNews.Articles>()

    fun getArticle(): MutableLiveData<PopularNews.Articles> {
        return liveData
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_popular, parent, false)
        return PopularHolder(view)
    }

    override fun getItemCount(): Int {
        return articlesList.size
    }

    override fun onBindViewHolder(holder: PopularHolder, position: Int) {
        val article: PopularNews.Articles = articlesList[position]!!
        Glide.with(context).load(article.imgPath).into(holder.ivPopular)
        holder.tvTitlePopular.text = article.title
        Log.i(TAG, "Title: ${article.title} ")

        holder.tvTitlePopular.tag = article
    }

    inner class PopularHolder(view: View) : ViewHolder(view) {
        val tvTitlePopular: TextView = view.findViewById<TextView?>(R.id.tv_title_popular)
        val ivPopular: ImageView = view.findViewById<ImageView>(R.id.iv_popular)

        init {
            ivPopular.setOnClickListener {
                it.startAnimation(
                    AnimationUtils.loadAnimation(
                        context, androidx.appcompat.R.anim.abc_fade_in
                    )
                )
                goToDetail(tvTitlePopular.tag as PopularNews.Articles)
            }
        }

    }

    private fun goToDetail(tag: PopularNews.Articles) {
        Log.i(TAG, "Content article: $tag")
        liveData.postValue(tag)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateArticleList(result: List<PopularNews.Articles?>) {
        articlesList = result.filterNotNull().toMutableList()
        Log.i(TAG, "getItemCount: ${articlesList.size}")
        notifyDataSetChanged()
    }
}