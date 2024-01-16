package com.chintek.example.asrdemo.data.tts

import android.app.Application
import android.speech.tts.TextToSpeech
import android.util.Log
import com.chintek.example.asrdemo.domain.tts.TTSConverter
import java.util.Locale
import javax.inject.Inject


class AndroidTTSConverter @Inject constructor(
    private val application: Application,
): TTSConverter, TextToSpeech.OnInitListener {

    private var tts: TextToSpeech = TextToSpeech(application, this)
    override fun speak(text: String) {
        tts.speak(text, TextToSpeech.QUEUE_FLUSH, null,"")
    }

    override fun stop() {
        tts.stop()
    }

    override fun onInit(status: Int) {
        if (status == TextToSpeech.SUCCESS) {
            val result = tts.setLanguage(Locale.JAPAN)
            if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                Log.e("TTS","The Language not supported!")
            }
        }
    }

}