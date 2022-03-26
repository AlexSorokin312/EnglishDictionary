package com.example.englishdictionary.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.englishdictionary.model.Word
import com.example.englishdictionary.repository.IWordsRepository
import com.example.englishdictionary.repository.WordsRepositoryImp
import com.example.filmsproject.app.App

class WordDetailsViewModel(
    val dataToObserve: MutableLiveData<AppState> = MutableLiveData<AppState>(),
    private val wordsRepository: IWordsRepository = WordsRepositoryImp(App.getWordsDao())
) : ViewModel() {


    fun updateWord(word: Word, translation: String) {
        dataToObserve.postValue(AppState.Loading)
        Thread {
            val updatedWord = Word(
                word.id,
                word.englishWord,
                translation,
                word.transcription,
                word.partOfSpeech
            )
            wordsRepository.updateWord(updatedWord)
        }
            .start()
    }

}