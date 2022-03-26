package com.example.englishdictionary.repository

import com.example.englishdictionary.model.Word
import com.example.englishdictionary.model.convertWordEntityToWord
import com.example.englishdictionary.model.convertWordToEntity
import com.example.filmsproject.room.WordsDao

class WordsRepositoryImp(private val localSource: WordsDao) : IWordsRepository {

    override fun getAllFromDataBase(): List<Word> = convertWordEntityToWord(localSource.all())

    override fun addWordInDataBase(word: Word) = localSource.insert(convertWordToEntity(word))

    override fun updateWord(word: Word) {
        localSource.updateWord(
            word.id,
            word.englishWord,
            word.translation,
            word.transcription,
            word.partOfSpeech
        )
    }

    override fun deleteWord(word: Word) {
        localSource.delete(convertWordToEntity(word))
    }

    override fun deleteAll() {
        localSource.deleteAll()
    }
}