package com.example.englishdictionary.model

import com.example.englishdictionary.wordsAPI.WordDto
import com.example.filmsproject.room.WordsEntity

fun convertWordEntityToWord(entityList: List<WordsEntity>): List<Word> {
    return entityList.map {
        Word(it.id, it.word, it.translation, it.ts, it.pos)
    }
}

fun convertWordToEntity(word: Word): WordsEntity {
    return WordsEntity(
        word.id,
        word.englishWord,
        word.translation,
        word.transcription,
        word.partOfSpeech
    )
}

fun convertWordDtoToWord(wordDTO: WordDto): Word =
    Word(wordDTO.id, wordDTO.text, wordDTO.tr.first().text, wordDTO.ts, wordDTO.pos)

