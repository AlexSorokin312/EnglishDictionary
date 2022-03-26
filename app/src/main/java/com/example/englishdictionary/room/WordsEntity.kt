package com.example.filmsproject.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class WordsEntity(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val word : String,
    val translation : String,
    val ts : String,
    val pos : String)