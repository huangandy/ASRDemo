package com.chintek.example.asrdemo.data.stt

import android.app.Application
import android.content.Intent
import android.os.Bundle
import android.speech.RecognitionListener
import android.speech.RecognizerIntent
import android.speech.SpeechRecognizer
import com.chintek.example.asrdemo.domain.stt.STTConverter
import com.chintek.example.asrdemo.domain.stt.STTResult
import java.util.Locale
import javax.inject.Inject

class DefaultSTTConverter @Inject constructor(
    private val speechRecognizer: SpeechRecognizer,
    private val application: Application,
    ): STTConverter, RecognitionListener {

    private val recognizerIntent: Intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH).apply {
        putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
        putExtra(RecognizerIntent.EXTRA_CALLING_PACKAGE, application.packageName)
        putExtra(RecognizerIntent.EXTRA_LANGUAGE, "ja_JP");
//        putExtra(RecognizerIntent.EXTRA_SPEECH_INPUT_MINIMUM_LENGTH_MILLIS, 2500)
//        putExtra(RecognizerIntent.EXTRA_SPEECH_INPUT_COMPLETE_SILENCE_LENGTH_MILLIS, 2000)
    }

    private var callback: ((STTResult) -> Unit)? = null

    init {
        speechRecognizer.setRecognitionListener(this)
    }

    override fun start(completion: (STTResult) -> Unit) {
        stop()
        this.callback = completion
        speechRecognizer.startListening(recognizerIntent)

    }

    override fun stop() {
        this.callback = null
        speechRecognizer.stopListening()
    }

    private fun updateResults(speechBundle: Bundle?) {
        val userSaid = speechBundle?.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION)
        this.callback?.let {
            it(STTResult(text = userSaid?.get(0)))
        }
    }

    override fun onPartialResults(results: Bundle?) {}
    override fun onResults(results: Bundle?) = updateResults(speechBundle = results)
    override fun onEndOfSpeech() {}
    override fun onError(errorCode: Int) {
        this.callback?.let {
            it(
                STTResult(null, error = when (errorCode) {
                    SpeechRecognizer.ERROR_AUDIO -> "音訊檔案有問題"
                    SpeechRecognizer.ERROR_CLIENT -> "error_client"
                    SpeechRecognizer.ERROR_INSUFFICIENT_PERMISSIONS -> "error_permission"
                    SpeechRecognizer.ERROR_NETWORK -> "網路問題"
                    SpeechRecognizer.ERROR_NETWORK_TIMEOUT -> "收音時間已到"
                    SpeechRecognizer.ERROR_NO_MATCH -> "未聽到任何聲音"
                    SpeechRecognizer.ERROR_RECOGNIZER_BUSY -> "忙碌中"
                    SpeechRecognizer.ERROR_SERVER -> "伺服器錯誤"
                    SpeechRecognizer.ERROR_SPEECH_TIMEOUT -> "error_timeout"
                    else -> "未知錯誤"
                })
            )
        }
    }

    override fun onReadyForSpeech(p0: Bundle?) {}
    override fun onRmsChanged(p0: Float) {}
    override fun onBufferReceived(p0: ByteArray?) {}
    override fun onEvent(p0: Int, p1: Bundle?) {}
    override fun onBeginningOfSpeech() {}
}