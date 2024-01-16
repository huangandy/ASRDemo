package com.chintek.example.asrdemo.domain.stt

interface STTConverter {
    fun start(completion: (STTResult) -> Unit)
    fun stop()
}