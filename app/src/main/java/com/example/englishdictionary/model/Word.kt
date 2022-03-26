package com.example.englishdictionary.model

import android.os.Parcelable
import com.example.englishdictionary.NO_PART_OF_SPEECH
import com.example.englishdictionary.NO_TRANSCRIPTION
import com.example.englishdictionary.NO_TRANSLATION
import kotlinx.android.parcel.Parcelize

fun getDefaultWord(englishWord: String): Word {
    return Word(0, englishWord, NO_TRANSLATION, NO_TRANSCRIPTION, NO_PART_OF_SPEECH)
}

@Parcelize
data class Word(
    val id: Int,
    val englishWord: String,
    val translation: String,
    val transcription: String,
    val partOfSpeech: String
) : Parcelable
