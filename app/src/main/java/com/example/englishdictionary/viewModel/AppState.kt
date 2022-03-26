package com.example.englishdictionary.viewModel

import com.example.englishdictionary.model.Word

sealed class AppState {

    data class SuccessWordsLoading(val words: List<Word>) : AppState()
    data class SuccessWordAdding(val word: Word) : AppState()
    data class SuccessWordUpdate(val word: Word) : AppState()
    data class SuccessWordDelete(val word: Word) : AppState()
    class Error(val error: Throwable) : AppState()
    object Loading : AppState()

}
