package com.ndt.beproductive.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ndt.beproductive.api.chatbot.ChatBotRequest
import com.ndt.beproductive.api.chatbot.ChatBotResponse
import retrofit2.Call
import com.ndt.beproductive.BuildConfig


class ChatbotViewModel : BaseViewModel() {

    companion object {
        val TAG: String = ChatbotViewModel::class.java.name
        val TAG_GENERATE_CONTENT: String = ChatbotViewModel::class.java.name
        const val apiKey = BuildConfig.API_KEY_GOOGLE_GEMINI
    }

    private val _chatResponse = MutableLiveData<String>()
    val chatResponse: LiveData<String> = _chatResponse
    fun getContent(userMessage: String) {
        val request = ChatBotRequest.GeminiRequest(
            contents = listOf(ChatBotRequest.Content(parts = listOf(ChatBotRequest.Part(text = userMessage))))
        )

        val call: Call<ChatBotResponse.GeminiResponse> =
            getAPI().generateContent(apiKey, request)
        call.enqueue(initHandleRespone(TAG_GENERATE_CONTENT)!!)
    }
    override fun handleSuccess(key: String?, body: Any?) {
        super.handleSuccess(key, body) // gọi lại giữ log
        if (key == TAG_GENERATE_CONTENT) {
            val response = body as? ChatBotResponse.GeminiResponse
            val message = response?.candidates?.firstOrNull()
                ?.content?.parts?.firstOrNull()?.text ?: "Không có phản hồi"
            _chatResponse.postValue(message)
        }
    }
}