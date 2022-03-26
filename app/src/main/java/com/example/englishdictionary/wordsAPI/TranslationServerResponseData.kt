package com.example.englishdictionary.wordsAPI

import com.google.gson.annotations.SerializedName
import java.lang.reflect.Array

data class TranslationServerResponseData(
    @field:SerializedName("def") val def: List<WordDto>
)