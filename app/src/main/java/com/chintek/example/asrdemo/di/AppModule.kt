package com.chintek.example.asrdemo.di

import android.app.Application
import android.speech.SpeechRecognizer
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideSpeechRecognizer(app: Application): SpeechRecognizer {
        return SpeechRecognizer.createSpeechRecognizer(app)
    }
}