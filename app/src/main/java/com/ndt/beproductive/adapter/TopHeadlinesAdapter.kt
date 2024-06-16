package com.ndt.beproductive.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
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
import com.bumptech.glide.load.MultiTransformation
import com.bumptech.glide.request.RequestOptions
import com.ndt.beproductive.App
import com.ndt.beproductive.R
import com.ndt.beproductive.api.reponse.PopularNews
import com.ndt.beproductive.api.reponse.TopHeadlines
import jp.wasabeef.glide.transformations.RoundedCornersTransformation

class TopHeadlinesAdapter(
    private var context: Context,
    private var articlesList: List<TopHeadlines.Articles?> = mutableListOf()
) : RecyclerView.Adapter<TopHeadlinesAdapter.TopHeadlineHolder>() {
    companion object {
        val TAG: String = TopHeadlinesAdapter::class.java.name
    }

    private val liveData = MutableLiveData<TopHeadlines.Articles>()

    fun getTopHeadline(): MutableLiveData<TopHeadlines.Articles> {
        return liveData
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopHeadlineHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_top_headlines, parent, false)
        return TopHeadlineHolder(view)
    }

    override fun getItemCount(): Int {
        return articlesList.size
    }

    override fun onBindViewHolder(holder: TopHeadlineHolder, position: Int) {
        val article: TopHeadlines.Articles = articlesList[position]!!

        val multi = MultiTransformation<Bitmap>(
            RoundedCornersTransformation(128, 0, RoundedCornersTransformation.CornerType.ALL)
        )
        Glide.with(context)
            .load(article.imgPath)
            .apply(RequestOptions.bitmapTransform(multi))
            .centerCrop()
            .into(holder.ivPopular)

        holder.tvTitlePopular.text = article.title
        Log.i(TAG, "Title: ${article.title} ")

        holder.tvTitlePopular.tag = article
    }

    inner class TopHeadlineHolder(view: View) : ViewHolder(view) {
        val tvTitlePopular: TextView = view.findViewById<TextView?>(R.id.tv_title_popular)
        val ivPopular: ImageView = view.findViewById<ImageView>(R.id.iv_popular)

        init {
            tvTitlePopular.setOnClickListener {
                it.startAnimation(
                    AnimationUtils.loadAnimation(
                        context, androidx.appcompat.R.anim.abc_fade_in
                    )
                )
                goToDetail(tvTitlePopular.tag as TopHeadlines.Articles)
            }
        }
    }

    private fun goToDetail(tag: TopHeadlines.Articles) {
        Log.i(TAG, "Content article: $tag")
        liveData.postValue(tag)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateArticleList(result: List<TopHeadlines.Articles?>) {
        articlesList = result.filterNotNull().toMutableList()
        Log.i(TAG, "getItemCount: ${articlesList.size}")
        notifyDataSetChanged()
    }
}