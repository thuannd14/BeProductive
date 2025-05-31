package com.ndt.beproductive.model

data class Message(
        val text: String,
        val isUser: Boolean,
        val timestamp: String
)