package com.example.englishdictionary.wordsAPI

data class WordDto(
    val id: Int,
    val text: String,
    val pos: String,
    val ts: String,
    val tr: List<WordTranslationDTO>
)

data class WordTranslationDTO(val text: String, val pos: String)