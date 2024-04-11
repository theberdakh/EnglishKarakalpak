package com.theberdakh.tradingglossary.search

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.theberdakh.tradingglossary.R
import com.theberdakh.tradingglossary.data.Word
import com.theberdakh.tradingglossary.databinding.FragmentSearchBinding
import com.theberdakh.tradingglossary.utils.addFragmentToBackStack
import com.theberdakh.tradingglossary.data.convertJsonString
import com.theberdakh.tradingglossary.data.jsonToString
import com.theberdakh.tradingglossary.utils.addFragment
import com.theberdakh.tradingglossary.word.WordFragment

class SearchFragment: Fragment() {
    private var _binding: FragmentSearchBinding? = null
    private val listOfWords = mutableListOf<Word>()
    private lateinit var adapter: SearchWordAdapter
    private val binding get() = checkNotNull(_binding)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        initViews()
        initListeners()
        return binding.root
    }

    private fun initViews() {
        binding.searchEditText.requestFocus()

         adapter = SearchWordAdapter{
            navigateToWordFragment(it)
        }
        val recyclerDividerItemDecoration = DividerItemDecoration(requireContext(), RecyclerView.VERTICAL)
        binding.recyclerSearch.addItemDecoration(recyclerDividerItemDecoration)
        binding.recyclerSearch.adapter = adapter
        val jsonString: String = requireContext().jsonToString("words.json")
        val arrayOfWords = convertJsonString(jsonString)

        if (arrayOfWords !=  null){
             listOfWords.addAll(arrayOfWords.toList())
            adapter.submitList(listOfWords)
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun initListeners(){

        binding.navigationBackIcon.setOnClickListener {
            requireActivity().supportFragmentManager.popBackStack()
        }


        binding.searchEditText.doAfterTextChanged {
            val words = sortedWords(it.toString())
            adapter.notifyDataSetChanged()
            adapter.submitList(words)
        }
    }

    private fun sortedWords(text: String): List<Word> {
        println(text)
        return listOfWords.filter {word ->
            val loweredName = word.word.lowercase()
            val loweredText = text.lowercase()
            loweredName.contains(loweredText)
        }
    }

    private fun navigateToWordFragment(word: Word) {
        addFragment(requireActivity().supportFragmentManager,
            R.id.main_container,
            WordFragment(word)
        )
    }
    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}