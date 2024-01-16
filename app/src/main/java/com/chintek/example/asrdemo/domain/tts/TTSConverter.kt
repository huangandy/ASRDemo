package com.chintek.example.asrdemo.domain.tts

import com.chintek.example.asrdemo.domain.stt.STTResult

interface TTSConverter {
    fun speak(text: String)

    fun stop()
}