package com.theberdakh.tradingglossary.data

import androidx.recyclerview.widget.DiffUtil
import kotlinx.serialization.Serializable

@Serializable
data class Word(
    val id: Double,
    val english: String,
    val karakalpak: String
)

class WordDiffUtil : DiffUtil.ItemCallback<Word>() {
    override fun areItemsTheSame(oldItem: Word, newItem: Word): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Word, newItem: Word): Boolean {
        return oldItem.id == newItem.id
    }
}

