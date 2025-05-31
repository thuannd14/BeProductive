package com.ndt.beproductive.api

import com.ndt.beproductive.api.chatbot.ChatBotRequest
import com.ndt.beproductive.api.chatbot.ChatBotResponse
import com.ndt.beproductive.api.reponse.TopHeadlines
import com.ndt.beproductive.api.reponse.PopularNews
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface API {
    // Search for news articles that mention a specific topic or keyword de vao cho popular.
    @GET("everything")
    fun getNewsPopular(
        @Query("q") query: String?,
        @Query("from") from: String?,
        @Query("sortBy") sortBy: String?,
        @Query("apiKey") apiKey: String?
    ): Call<PopularNews>

    @GET("top-headlines")
    fun getTopHeadlines(
        @Query("country") country: String?,
        @Query("apiKey") apiKey: String?
    ): Call<TopHeadlines>

    // CALL API for Gemini Chatbot
    @POST("v1beta/models/gemini-2.0-flash:generateContent")
    fun generateContent(
        @Query("key") apiKey: String,
        @Body request: ChatBotRequest.GeminiRequest
    ): Call<ChatBotResponse.GeminiResponse>

}