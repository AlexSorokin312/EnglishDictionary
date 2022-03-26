package com.example.filmsproject.app

import android.app.Application
import androidx.room.Room
import com.example.filmsproject.room.DB
import com.example.filmsproject.room.WordsDao

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        appInstance = this
    }

    companion object {
        private var appInstance: App? = null
        private var db: DB? = null
        private const val DB_NAME = "Words.db"

        fun getWordsDao(): WordsDao {
            if (db == null) {
                synchronized(DB::class.java) {
                    if (db == null) {
                        if (appInstance == null) {
                            throw IllegalStateException("Error")
                        }
                        db = Room.databaseBuilder(
                            appInstance!!.applicationContext,
                            DB::class.java,
                            DB_NAME
                        )
                            .build()
                    }
                }
            }
            return db!!.wordsDao()
        }
    }
}