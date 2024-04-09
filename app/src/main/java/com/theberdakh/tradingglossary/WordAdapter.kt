package com.theberdakh.tradingglossary

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.theberdakh.tradingglossary.databinding.ItemCardWordBinding

class WordAdapter(private val onClick: (Word) -> Unit): ListAdapter<Word, WordAdapter.WordViewHolder>(WordDiffUtil()){

    inner class WordViewHolder(private val binding: ItemCardWordBinding): ViewHolder(binding.root){
        fun bind(){
            val word = getItem(adapterPosition)
            binding.titleWord.text = word.word
            binding.captionWord.text = word.meaning
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordViewHolder {
        return WordViewHolder(ItemCardWordBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        ))
    }

    override fun onBindViewHolder(holder: WordViewHolder, position: Int) = holder.bind()
}