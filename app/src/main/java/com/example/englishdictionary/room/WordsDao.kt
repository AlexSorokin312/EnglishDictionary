package com.example.filmsproject.room

import androidx.room.*
import com.example.englishdictionary.wordsAPI.WordDto

@Dao
interface WordsDao {

    @Query("SELECT * FROM WordsEntity")
    fun all(): List<WordsEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(entity: WordsEntity)

    @Query("UPDATE WordsEntity SET word = :englishWord, translation =:russianWord, ts =:transcription, pos =:partOfSpeech  WHERE id =:id")
    fun updateWord(
        id: Int,
        englishWord: String,
        russianWord: String,
        transcription: String,
        partOfSpeech: String
    )

    @Delete
    fun delete(entity: WordsEntity)

    @Query("DELETE FROM WordsEntity")
    fun deleteAll()
}