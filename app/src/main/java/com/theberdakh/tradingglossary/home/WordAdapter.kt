package com.theberdakh.tradingglossary.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.theberdakh.tradingglossary.data.Word
import com.theberdakh.tradingglossary.data.WordDiffUtil
import com.theberdakh.englishkarakalpak.databinding.ItemCardWordBinding

class WordAdapter(private val onClick: (Word) -> Unit) :
    ListAdapter<Word, WordAdapter.WordViewHolder>(
        WordDiffUtil()
    ) {

    inner class WordViewHolder(private val binding: ItemCardWordBinding) :
        ViewHolder(binding.root) {
        fun bind() {
            val word = getItem(adapterPosition)
            binding.titleWord.text = word.karakalpak
            binding.captionWord.text = word.english
            binding.root.setOnClickListener {
                onClick.invoke(word)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordViewHolder {
        return WordViewHolder(
            ItemCardWordBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: WordViewHolder, position: Int) = holder.bind()
}