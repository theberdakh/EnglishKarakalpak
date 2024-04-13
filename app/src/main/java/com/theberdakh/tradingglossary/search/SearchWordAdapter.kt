package com.theberdakh.tradingglossary.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.theberdakh.tradingglossary.data.Word
import com.theberdakh.tradingglossary.data.WordDiffUtil
import com.theberdakh.englishkarakalpak.databinding.ItemSearchBinding
import com.theberdakh.tradingglossary.data.Language

class SearchWordAdapter(private val language: Language, private val onClick: (Word) -> Unit): ListAdapter<Word, SearchWordAdapter.SearchWordViewHolder>(
    WordDiffUtil()
) {
    inner class SearchWordViewHolder(private val binding: ItemSearchBinding): ViewHolder(binding.root){
        fun bind(){
            val word = getItem(adapterPosition)
            when(language){
                Language.English -> {binding.titleWord.text = word.english}
                Language.Karakalpak -> {binding.titleWord.text = word.karakalpak}
            }
            binding.root.setOnClickListener {
                onClick.invoke(word)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchWordViewHolder {
        return SearchWordViewHolder(ItemSearchBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        ))
    }

    override fun onBindViewHolder(holder: SearchWordViewHolder, position: Int) = holder.bind()


}