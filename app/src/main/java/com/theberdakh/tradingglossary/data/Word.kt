package com.theberdakh.tradingglossary.data

import androidx.recyclerview.widget.DiffUtil

data class Word(
    val id: Int,
    val word: String,
    val meaning: String
)

class WordDiffUtil : DiffUtil.ItemCallback<Word>() {
    override fun areItemsTheSame(oldItem: Word, newItem: Word): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Word, newItem: Word): Boolean {
        return oldItem.id == newItem.id
    }
}

