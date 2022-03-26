package com.example.englishdictionary.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.englishdictionary.model.Word
import com.example.englishdictionary.repository.IWordsRepository
import com.example.englishdictionary.repository.WordsRepositoryImp
import com.example.filmsproject.app.App

class WordsListViewModel(
    val dataToObserve: MutableLiveData<AppState> = MutableLiveData<AppState>(),
    private val wordsRepository: IWordsRepository = WordsRepositoryImp(App.getWordsDao())
) : ViewModel() {

    fun getDataFromDB() {
        dataToObserve.postValue(AppState.Loading)
        Thread {
            try {
                val words: List<Word> = wordsRepository.getAllFromDataBase()
                dataToObserve.postValue(
                    AppState.SuccessWordsLoading(words)
                )
            } catch (e: Exception) {
                dataToObserve.postValue(AppState.Error(e))
            }
        }.start()
    }

    fun deleteWordFromDB(word: Word) {
        dataToObserve.postValue(AppState.Loading)
        Thread {
            wordsRepository.deleteWord(word)
            dataToObserve.postValue(AppState.SuccessWordDelete(word))
        }.start()
    }
}