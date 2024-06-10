package com.ndt.beproductive.api.reponse

import com.google.gson.annotations.SerializedName

class PopularNews {

    @SerializedName("status")
    var status: String? = null

    @SerializedName("totalResults")
    var totalResult: Int? = null

    @SerializedName("articles")
    var articlesList: List<PopularNews.Articles>? = null

    inner class Articles {
        @SerializedName("author")
        var author: String? = null

        @SerializedName("title")
        var title: String? = null

        @SerializedName("urlToImage")
        var imgPath: String? = null

        @SerializedName("publishedAt")
        var publishedAt: String? = null

        @SerializedName("description")
        var descArticle: String? = null


        override fun toString(): String {
            return "Articles(author=$author, title=$title, imgPath=$imgPath, publishedAt=$publishedAt, descArticle=$descArticle)"
        }
    }
}