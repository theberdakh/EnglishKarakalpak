package com.theberdakh.tradingglossary.word

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.theberdakh.tradingglossary.data.Word
import com.theberdakh.englishkarakalpak.databinding.FragmentWordBinding

class WordFragment(private val word: Word): Fragment() {
    private var _binding: FragmentWordBinding? =null
    private val binding get() = checkNotNull(_binding)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWordBinding.inflate(inflater, container, false)

        initViews()
        initListeners()

        return binding.root
    }

    private fun initListeners() {
        binding.toolbarAllWords.setNavigationOnClickListener {
            requireActivity().supportFragmentManager.popBackStack()
        }
    }

    private fun initViews() {
        binding.title.text  = word.english
        binding.meaning.text = word.karakalpak
    }
}