package com.chintek.example.asrdemo.domain.chat


data class Message(
    val role: Role,
    val text: String = ""
)
