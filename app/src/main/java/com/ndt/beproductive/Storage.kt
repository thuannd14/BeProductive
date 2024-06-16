package com.ndt.beproductive

import com.ndt.beproductive.api.reponse.PopularNews
import com.ndt.beproductive.api.reponse.TopHeadlines

class Storage {
    companion object {
        val TAG: String = Storage::class.java.name
    }

    var id: Int? = null
    lateinit var content: String
    lateinit var dateTime: String
    var color: Int? = null

    // Luu vi tri Int cua hinh anh trong list.
    var indexImg: Int? = null

    // Luu noi dung ghi chu o main focus screen.
    var contentText: String? = null

    // check day co phai lan dau vao app ko?
    var isFirstTime: Boolean = false
    fun setIsFirstTime(isFirstTime: Boolean) {
        this.isFirstTime = isFirstTime
    }

    var articleList: List<PopularNews.Articles?>? = null

    var topHeadline: TopHeadlines? = null

    var totalTimeFocus = 0
    var totalTimeBreak = 0
}