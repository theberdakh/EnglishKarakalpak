package com.theberdakh.tradingglossary.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.theberdakh.englishkarakalpak.R
import com.theberdakh.tradingglossary.search.SearchFragment
import com.theberdakh.tradingglossary.data.Word
import com.theberdakh.tradingglossary.utils.addFragmentToBackStack
import com.theberdakh.englishkarakalpak.databinding.FragmentMainBinding
import com.theberdakh.tradingglossary.data.convertJsonString
import com.theberdakh.tradingglossary.data.jsonToString
import com.theberdakh.tradingglossary.word.WordFragment

class MainFragment : Fragment() {
    private var _binding: FragmentMainBinding? = null
    private val binding get() = checkNotNull(_binding)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)

        initViews()
        initListeners()

        return binding.root
    }

    private fun initViews() {
        val adapter = WordAdapter{
            navigateToWordFragment(it)
        }
        binding.recyclerSearchBar.adapter = adapter
        val jsonString: String = requireContext().jsonToString("words.json")
        val arrayOfWords = convertJsonString(jsonString)

        if (arrayOfWords !=  null){
            val list = arrayOfWords.toList()
            adapter.submitList(list)
        }
    }

    private fun navigateToWordFragment(word: Word) {
        addFragmentToBackStack(requireActivity().supportFragmentManager,
            R.id.main_container,
            WordFragment(word)
        )
    }

    private fun initListeners() {
        binding.searchBar.setOnClickListener {
            println("clicked")
            navigateToSearchFragment()
        }
    }

    private fun navigateToSearchFragment() {
        addFragmentToBackStack(
            requireActivity().supportFragmentManager,
            R.id.main_container,
            SearchFragment()
        )
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}