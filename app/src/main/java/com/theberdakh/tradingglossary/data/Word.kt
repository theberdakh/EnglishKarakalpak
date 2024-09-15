package com.theberdakh.tradingglossary.data

import androidx.recyclerview.widget.DiffUtil
import kotlinx.serialization.Serializable

@Serializable
data class Word(
    val karakalpak: String,
    val english: String
)

class WordDiffUtil : DiffUtil.ItemCallback<Word>() {
    override fun areItemsTheSame(oldItem: Word, newItem: Word): Boolean {
        return oldItem.english == newItem.english && oldItem.karakalpak == newItem.karakalpak
    }

    override fun areContentsTheSame(oldItem: Word, newItem: Word): Boolean {
        return oldItem == newItem
    }
}

