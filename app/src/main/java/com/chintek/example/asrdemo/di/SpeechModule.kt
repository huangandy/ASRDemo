package com.chintek.example.asrdemo.di

import com.chintek.example.asrdemo.data.stt.DefaultSTTConverter
import com.chintek.example.asrdemo.domain.stt.STTConverter
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class SpeechModule {

    @Binds
    @Singleton
    abstract fun bindSTTConverter(defaultSTTConverter: DefaultSTTConverter): STTConverter

}