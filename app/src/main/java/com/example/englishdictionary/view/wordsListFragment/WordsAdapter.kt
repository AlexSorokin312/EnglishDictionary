package com.example.englishdictionary.view.wordsListFragment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageButton
import androidx.recyclerview.widget.RecyclerView
import com.example.englishdictionary.databinding.WordItemBinding
import com.example.englishdictionary.model.Word

class WordsAdapter() :
    RecyclerView.Adapter<WordsAdapter.WordViewHolder>() {

    //region Adapter

    private lateinit var words: List<Word>
    private var onItemClickListener: WordsListFragment.OnWordItemClickListener? = null
    private var onWordItemDeleteButtonListener: WordsListFragment.OnWordItemDeleteButtonListener? =
        null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordViewHolder {
        val binding = WordItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return WordViewHolder(binding)
    }

    override fun onBindViewHolder(holder: WordViewHolder, position: Int) = holder.bind(words[position])

    override fun getItemCount(): Int = words.size

    fun setData(data: List<Word>) {
        words = data
        notifyDataSetChanged()
    }

    fun setOnItemClickListener(listener: WordsListFragment.OnWordItemClickListener) {
        onItemClickListener = listener
    }

    fun setOnItemDeleteListener(listenerWord: WordsListFragment.OnWordItemDeleteButtonListener) {
        onWordItemDeleteButtonListener = listenerWord
    }
    //endregion


    //region ViewHolder
    inner class WordViewHolder(binding: WordItemBinding) : RecyclerView.ViewHolder(binding.root) {

        private val wordItem: View = binding.item
        private val englishWord: TextView = binding.englishWord
        private val translation: TextView = binding.translation
        private val deleteButton: AppCompatImageButton = binding.deleteItemButton

        fun bind(word: Word) {
            englishWord.text = word.englishWord
            translation.text = word.translation
            wordItem.setOnClickListener {
                onItemClickListener?.onWordItemClick(word)
                notifyDataSetChanged()
            }
            deleteButton.setOnClickListener {
                onWordItemDeleteButtonListener?.onWordItemDeleteItemListener(word)
            }
        }
    }
    //endregion
}