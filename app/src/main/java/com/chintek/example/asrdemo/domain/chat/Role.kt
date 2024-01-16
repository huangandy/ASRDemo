package com.chintek.example.asrdemo.domain.chat

sealed class Role(val roleText: String) {
    data class System(val role: String = "assistant"): Role(role)
    data class User(val role: String = "user"): Role(role)
}
