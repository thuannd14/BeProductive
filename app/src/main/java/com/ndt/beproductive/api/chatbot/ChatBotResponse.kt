package com.ndt.beproductive.api.chatbot

class ChatBotResponse {
    data class GeminiResponse(
        val candidates: List<Candidate>
    )

    data class Candidate(
        val content: ContentResult
    )

    data class ContentResult(
        val parts: List<PartResult>
    )

    data class PartResult(
        val text: String
    )
}