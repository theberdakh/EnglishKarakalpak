package com.theberdakh.tradingglossary.search

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.theberdakh.englishkarakalpak.R
import com.theberdakh.tradingglossary.data.Word
import com.theberdakh.englishkarakalpak.databinding.FragmentSearchBinding
import com.theberdakh.tradingglossary.data.Language
import com.theberdakh.tradingglossary.data.convertJsonUsingSerialisation
import com.theberdakh.tradingglossary.data.jsonToString
import com.theberdakh.tradingglossary.utils.addFragment
import com.theberdakh.tradingglossary.word.WordFragment

class SearchFragment : Fragment() {
    private var _binding: FragmentSearchBinding? = null
    private lateinit var adapter: SearchWordAdapter
    private val allWords = mutableListOf<Word>()
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

        adapter = SearchWordAdapter {
            navigateToWordFragment(it)
        }
        val recyclerDividerItemDecoration =
            DividerItemDecoration(requireContext(), RecyclerView.VERTICAL)
        binding.recyclerSearch.addItemDecoration(recyclerDividerItemDecoration)
        binding.recyclerSearch.adapter = adapter


        val jsonString: String = requireContext().jsonToString("words.json")
        allWords.addAll(convertJsonUsingSerialisation(jsonString))
        adapter.submitList(allWords)

    }

    @SuppressLint("NotifyDataSetChanged")
    private fun initListeners() {

        binding.navigationBackIcon.setOnClickListener {
            requireActivity().supportFragmentManager.popBackStack()
        }


        binding.searchEditText.doAfterTextChanged {
            val sortedWords = sortedWords(it.toString())
            adapter.notifyDataSetChanged()
            adapter.submitList(sortedWords)
        }
    }

    private fun sortedWords(text: String, language: Language = Language.English): List<Word> {


        val allContainingWords = allWords.filter { word ->
            val wordToCheck = when (language) {
                Language.English -> word.english
                Language.Karakalpak -> word.karakalpak
            }
            wordToCheck.lowercase().contains(text)
        }

        val startingFromText = allContainingWords.filter { word ->
            val wordToCheck = when (language) {
                Language.English -> word.english
                Language.Karakalpak -> word.karakalpak
            }
            wordToCheck.lowercase().startsWith(text.lowercase())
        }

        return startingFromText
    }


    private fun navigateToWordFragment(word: Word) {
        addFragment(
            requireActivity().supportFragmentManager,
            R.id.main_container,
            WordFragment(word)
        )
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}