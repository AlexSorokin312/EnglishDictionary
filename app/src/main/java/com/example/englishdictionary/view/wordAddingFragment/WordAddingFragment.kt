package com.example.englishdictionary.view.wordAddingFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.englishdictionary.databinding.FragmentAddNewWordBinding
import com.example.englishdictionary.view.wordsListFragment.WordsListFragment
import com.example.englishdictionary.viewModel.AppState
import com.example.englishdictionary.viewModel.WordAddingViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class WordAddingFragment(private val onWordAddListener: WordsListFragment.OnWordItemAddedListener) :
    BottomSheetDialogFragment() {

    //region Properties

    private var _binding: FragmentAddNewWordBinding? = null
    private val binding: FragmentAddNewWordBinding
        get() = _binding!!

    private val viewModel: WordAddingViewModel by lazy {
        ViewModelProvider(this).get(WordAddingViewModel::class.java)
    }
    //endregion

    //region Fragment Methods
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddNewWordBinding.inflate(inflater, container, false)
        binding.findWord.setOnClickListener {
            Thread {
                viewModel.sendServerRequest(binding.searchableWord.text.toString())
            }.start()

        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val observer = Observer<AppState>() { renderData(it) }
        viewModel.observableData.observe(viewLifecycleOwner, observer)
        viewModel.sendServerRequest(binding.searchableWord.text.toString())
    }

    private fun renderData(appState: AppState) {
        when (appState) {
            is AppState.SuccessWordAdding -> {
                onWordAddListener.onWordItemAddedListener(appState.word)
            }
            is AppState.Loading -> {
                //todo loading
            }
            is AppState.Error -> {
                //todo loading
            }
        }
    }

    companion object {
        fun newInstance(listener: WordsListFragment.OnWordItemAddedListener) =
            WordAddingFragment(listener)
    }
    //endregion
}