package com.ndt.beproductive.viewmodel

import android.util.Log
import com.ndt.beproductive.api.reponse.PopularNews
import retrofit2.Call

class M004ExploreVM : BaseViewModel() {
    companion object {
        val TAG: String = M004ExploreVM::class.java.name
        const val API_KEY = "170f7c5107b64693a91f0cd6a7d9e2e7"
        const val GET_NEW_ARTICLES = "GET_NEW_ARTICLES"
    }

    private val resultList = mutableListOf<PopularNews.Articles>()

    fun getListArticleAPI() {
        val call: Call<PopularNews> =
            getAPI().getNewsPopular("tesla", "2024-05-10", "publishedAt", API_KEY)
        call.enqueue(initHandleRespone(GET_NEW_ARTICLES)!!)
    }

    fun addToList(result: List<PopularNews.Articles?>?) {
        result?.filterNotNull()?.let { filteredList ->
            resultList.addAll(filteredList)
        }
    }

    fun getListArticle(): List<PopularNews.Articles> {
        Log.i(TAG, "${resultList.size}")
        return resultList
    }
}
