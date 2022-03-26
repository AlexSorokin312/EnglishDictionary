package com.example.englishdictionary.wordsAPI

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface TranslationAPI {

    @GET("dicservice.json/lookup")
    fun getTranslation(
        @Query("key") apiKey: String,
        @Query("lang") lang: String = "en-ru",
        @Query("text") word: String
    ): Call<TranslationServerResponseData>
}