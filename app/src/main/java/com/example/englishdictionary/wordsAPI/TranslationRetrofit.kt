package com.example.englishdictionary.wordsAPI

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class TranslationRetrofit {

    private val baseUrl = "https://dictionary.yandex.net/api/v1/"

    fun getRetrofitImpl(): TranslationAPI {
        return Retrofit.Builder().baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
            .build().create(TranslationAPI::class.java)
    }
}


