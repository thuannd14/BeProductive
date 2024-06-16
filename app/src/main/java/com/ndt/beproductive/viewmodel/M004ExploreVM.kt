package com.ndt.beproductive.viewmodel

import android.util.Log
import com.ndt.beproductive.App
import com.ndt.beproductive.api.reponse.PopularNews
import com.ndt.beproductive.api.reponse.TopHeadlines
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class M004ExploreVM : BaseViewModel() {
    companion object {
        val TAG: String = M004ExploreVM::class.java.name
        const val API_KEY = "170f7c5107b64693a91f0cd6a7d9e2e7"
        const val GET_NEW_ARTICLES = "GET_NEW_ARTICLES"
    }

    private val resultList = mutableListOf<PopularNews.Articles>()
    private val topHeadlinesList = mutableListOf<TopHeadlines.Articles>()


    // Popular
    fun getListArticleAPI() {
        val call: Call<PopularNews> =
            getAPI().getNewsPopular("bitcoin", "2024-06-09", "popularity", API_KEY)
        call.enqueue(initHandleRespone(GET_NEW_ARTICLES)!!)

    }


    fun addToList(result: List<PopularNews.Articles?>?) {
        result?.filterNotNull()?.let { filteredList ->
            resultList.addAll(filteredList)
        }
        App.instance.getStorage().articleList = resultList
    }

    fun getListArticle(): List<PopularNews.Articles> {
        Log.i(TAG, "Popular: ${resultList.size}")
        return resultList
    }


    // Top headlines.
    fun getLisTopHeadlinesAPI() {
        val call: Call<TopHeadlines> = getAPI().getTopHeadlines("us", API_KEY)
        call.enqueue(object : Callback<TopHeadlines> {
            override fun onResponse(p0: Call<TopHeadlines>, response: Response<TopHeadlines>) {
                if (response.code() == 200 || response.code() == 201) {
                    Log.i(TAG, "onResponse ${response.body()}")
                    App.instance.getStorage().topHeadline = response.body()
                } else {
                    Log.i(TAG, "Null")
                }
            }

            override fun onFailure(p0: Call<TopHeadlines>, p1: Throwable) {
                Log.i(TAG, "onFailure ${p1.message}")
            }
        })
    }

    fun addToHeadlines(result: List<TopHeadlines.Articles?>?) {
        result?.filterNotNull()?.let { filteredList ->
            topHeadlinesList.addAll(filteredList)
        }
    }

    fun getListTopHeadlines(): List<TopHeadlines.Articles> {
        Log.i(TAG, "OnSuccess TopHeadlines: ${topHeadlinesList.size}")
        return topHeadlinesList
    }
}
