package com.chintek.example.asrdemo.presentation.stt

import androidx.compose.runtime.mutableStateListOf
import com.chintek.example.asrdemo.domain.chat.Message

data class STTState (
    val messages: MutableList<Message> = mutableStateListOf(),
    val spokenText: String = "",
    val isLoading: Boolean = false,
    val error: String? = null,
)