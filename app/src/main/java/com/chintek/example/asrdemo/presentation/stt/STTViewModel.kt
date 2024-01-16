package com.chintek.example.asrdemo.presentation.stt

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chintek.example.asrdemo.domain.chat.Message
import com.chintek.example.asrdemo.domain.chat.Role
import com.chintek.example.asrdemo.domain.stt.STTConverter
import com.chintek.example.asrdemo.domain.tts.TTSConverter
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class STTViewModel  @Inject constructor(
    private val sttConverter: STTConverter,
    private val ttsConverter: TTSConverter,
): ViewModel() {

    val TAG = STTViewModel::class.java.simpleName
    var state by mutableStateOf(STTState())

    fun updateText(text: String) {
        state = state.copy(spokenText = text)
    }

    fun voiceInput() {
        sttConverter.start {
            Log.i(TAG, "STT=${it.text}, ERR= ${it.error}")
            it.text?.let { text ->
                state = state.copy(spokenText = text)
            }
            it.error?.let { err ->

            }
        }
    }

    fun send() {
        if (state.spokenText.isNullOrEmpty()) return
        echo(state.spokenText)
        state = state.copy(spokenText = "" )
    }

    private fun echo(text: String) {
        ttsConverter.speak(text)
        state.messages.add(Message(Role.User(), text))
        state.messages.add(Message(Role.System(), text))
        state = state.copy(messages = state.messages)
    }
}