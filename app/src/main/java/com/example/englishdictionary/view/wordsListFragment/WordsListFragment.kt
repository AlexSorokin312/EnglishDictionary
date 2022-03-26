package com.example.englishdictionary.view.wordsListFragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.englishdictionary.MainActivity
import com.example.englishdictionary.databinding.FragmentWithAllWordsBinding
import com.example.englishdictionary.fragmentNavigation.INavigation
import com.example.englishdictionary.model.Word
import com.example.englishdictionary.view.wordAddingFragment.WordAddingFragment
import com.example.englishdictionary.view.wordDetailsFragment.WordDetailsFragment
import com.example.englishdictionary.viewModel.AppState
import com.example.englishdictionary.viewModel.WordsListViewModel

class WordsListFragment : Fragment() {

    //region Listeners Interfaces
    interface OnWordItemClickListener {
        fun onWordItemClick(word: Word)
    }

    interface OnWordItemDeleteButtonListener {
        fun onWordItemDeleteItemListener(word: Word)
    }

    interface OnWordItemAddedListener {
        fun onWordItemAddedListener(word: Word)
    }
    //endregion

    //region Properties

    private var _binding: FragmentWithAllWordsBinding? = null
    private val binding: FragmentWithAllWordsBinding
        get() = _binding!!

    private var words: List<Word> = listOf()

    private var navigationListener: INavigation? = null

    private var wordsAdapter: WordsAdapter? = WordsAdapter().apply {
        setOnItemClickListener(object : OnWordItemClickListener {
            override fun onWordItemClick(word: Word) {
                val bundle = Bundle()
                bundle.putParcelable(WordDetailsFragment.BINDING_KEY, word)
                navigationListener?.navigateToFragment(
                    WordDetailsFragment.newInstance(
                        bundle
                    )
                )
            }
        })
        setOnItemDeleteListener(object : OnWordItemDeleteButtonListener {
            override fun onWordItemDeleteItemListener(word: Word) {
                viewModel.deleteWordFromDB(word)
            }
        })
    }

    private val viewModel: WordsListViewModel by lazy {
        ViewModelProvider(this).get(WordsListViewModel::class.java)
    }

    //endregion

    //region Fragment Methods
    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is INavigation) {
            navigationListener = context

        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.getDataFromDB()
        wordsAdapter?.notifyDataSetChanged()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentWithAllWordsBinding.inflate(inflater, container, false)
        (context as MainActivity).setSupportActionBar(binding.bottomAppBar)
        setButtonListeners()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val observer = Observer<AppState>() { renderData(it) }
        viewModel.dataToObserve.observe(viewLifecycleOwner, observer)
        setHasOptionsMenu(true)
        wordsAdapter?.setData(words)
        binding.recyclerView.adapter = wordsAdapter
        viewModel.getDataFromDB()
    }

    override fun onDetach() {
        super.onDetach()
        navigationListener = null
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
        wordsAdapter = null
    }

    companion object {
        fun newInstance() = WordsListFragment()
    }

    private fun renderData(appState: AppState) {
        when (appState) {
            is AppState.SuccessWordsLoading -> {
                words = appState.words
                wordsAdapter?.setData(words)
            }
            is AppState.SuccessWordDelete -> {
                viewModel.getDataFromDB()
            }
            is AppState.Loading -> {
                //todo loading
            }
            is AppState.Error -> {
                //todo error
            }
        }
        wordsAdapter?.notifyDataSetChanged()
    }

    private fun setButtonListeners() {
        binding.addWordFloatingButton.setOnClickListener {
            activity?.let {
                WordAddingFragment.newInstance(object : OnWordItemAddedListener {
                    override fun onWordItemAddedListener(word: Word) {
                        viewModel.getDataFromDB()
                        wordsAdapter?.notifyDataSetChanged()
                    }
                }
                ).show(it.supportFragmentManager, "")
            }
        }
    }

    //endregion Fragment Methods
}