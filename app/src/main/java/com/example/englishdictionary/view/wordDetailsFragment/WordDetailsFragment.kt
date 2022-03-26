package com.example.englishdictionary.view.wordDetailsFragment

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.englishdictionary.databinding.FragmentWordsDetailsBinding
import com.example.englishdictionary.model.Word
import com.example.englishdictionary.viewModel.AppState
import com.example.englishdictionary.viewModel.WordDetailsViewModel

class WordDetailsFragment : Fragment() {

    //region Properties
    private var _binding: FragmentWordsDetailsBinding? = null
    private val binding: FragmentWordsDetailsBinding
        get() = _binding!!

    private val viewModel: WordDetailsViewModel by lazy {
        ViewModelProvider(this).get(WordDetailsViewModel::class.java)
    }

    private lateinit var word: Word

    private val textWatcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            binding.saveChangesButton.visibility = View.VISIBLE
        }

        override fun afterTextChanged(s: Editable?) {
        }
    }
    //endregion

    //region Fragment Methods

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentWordsDetailsBinding.inflate(inflater, container, false)
        setButtonListeners()
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val observer = Observer<AppState>() { renderData(it) }
        viewModel.dataToObserve.observe(viewLifecycleOwner, observer)
        word = arguments?.get(BINDING_KEY) as Word
        if (word != null) {
            setDataInView(word)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {
        const val BINDING_KEY = "WORD_DETAILS_BINDING"
        fun newInstance(bundle: Bundle): WordDetailsFragment {
            val fragment = WordDetailsFragment()
            fragment.arguments = bundle
            return fragment
        }
    }
    //endregion Fragment Methods

    private fun setButtonListeners() {
        with(binding) {
            englishWord.addTextChangedListener(textWatcher)
            russianWord.addTextChangedListener(textWatcher)
            partOfSpeech.addTextChangedListener(textWatcher)
            saveChangesButton.setOnClickListener {
                viewModel.updateWord(word, binding.russianWord.text.toString())
            }
        }

    }

    private fun setDataInView(word: Word) {
        with(binding) {
            englishWord.setText(word.englishWord, TextView.BufferType.EDITABLE)
            russianWord.setText(word.translation, TextView.BufferType.EDITABLE)
            transcription.setText(word.transcription, TextView.BufferType.EDITABLE)
            partOfSpeech.setText(word.partOfSpeech, TextView.BufferType.EDITABLE)
            saveChangesButton.visibility = View.GONE
        }
    }

    private fun renderData(appState: AppState) {
        when (appState) {
            is AppState.SuccessWordUpdate -> {
                //todo successful
            }
            is AppState.Loading -> {
                //todo loading
            }
            is AppState.Error -> {
                //todo error
            }
        }
    }
}


