package com.example.englishdictionary.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.englishdictionary.BuildConfig
import com.example.englishdictionary.EN_RU
import com.example.englishdictionary.model.Word
import com.example.englishdictionary.model.convertWordDtoToWord
import com.example.englishdictionary.model.getDefaultWord
import com.example.englishdictionary.repository.IWordsRepository
import com.example.englishdictionary.repository.WordsRepositoryImp
import com.example.englishdictionary.wordsAPI.TranslationRetrofit
import com.example.englishdictionary.wordsAPI.TranslationServerResponseData
import com.example.filmsproject.app.App
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class WordAddingViewModel(
    val observableData: MutableLiveData<AppState> = MutableLiveData<AppState>(),
    private val wordsRepository: IWordsRepository = WordsRepositoryImp(App.getWordsDao()),
    private val retrofitImp: TranslationRetrofit = TranslationRetrofit()
) : ViewModel() {

    fun sendServerRequest(englishWord: String) {
        observableData.postValue(AppState.Loading)
        Thread {
            val apiKey = BuildConfig.englishDictionaryApiKey
            if (apiKey.isBlank()) {
                //todo noApiKey
            } else {
                retrofitImp.getRetrofitImpl().getTranslation(apiKey, EN_RU, englishWord).enqueue(

                    object : Callback<TranslationServerResponseData> {

                        override fun onResponse(
                            call: Call<TranslationServerResponseData>,
                            response: Response<TranslationServerResponseData>
                        ) {
                            if (response.isSuccessful && response.body() != null) {
                                val response = response.body()
                                val wordsDto = response?.def
                                if (!wordsDto.isNullOrEmpty()) {
                                    val word = convertWordDtoToWord(wordsDto.first())
                                    saveWordInDataBase(word)
                                } else {
                                    val word = getDefaultWord(englishWord)
                                    saveWordInDataBase(word)
                                }
                            } else {
                                observableData.postValue(AppState.Error(NullPointerException()))
                            }
                        }

                        override fun onFailure(
                            call: Call<TranslationServerResponseData>,
                            t: Throwable
                        ) {
                            //todo handling exception
                        }
                    }
                )
            }
        }.start()
    }

    fun saveWordInDataBase(word: Word) {
        Thread {
            try {
                wordsRepository.addWordInDataBase(word)
                observableData.postValue(AppState.SuccessWordAdding(word))
            } catch (e: Exception) {
                //todo handling exception
            }
        }.start()
    }
}