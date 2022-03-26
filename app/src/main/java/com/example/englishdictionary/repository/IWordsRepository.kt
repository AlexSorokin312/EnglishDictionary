package com.example.englishdictionary.repository

import com.example.englishdictionary.model.Word

interface IWordsRepository {

    fun getAllFromDataBase(): List<Word>
    fun addWordInDataBase(word: Word)
    fun updateWord(word: Word)
    fun deleteWord(word: Word)
    fun deleteAll()

}
