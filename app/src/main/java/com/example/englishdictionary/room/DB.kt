package com.example.filmsproject.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = arrayOf(WordsEntity::class), version = 1, exportSchema = true)
abstract class DB : RoomDatabase() {

    abstract fun wordsDao(): WordsDao

}
