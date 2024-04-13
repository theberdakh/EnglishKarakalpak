package com.theberdakh.tradingglossary.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.app.ActivityCompat.getDrawable
import androidx.core.app.ActivityCompat.recreate
import androidx.fragment.app.Fragment
import com.theberdakh.englishkarakalpak.R
import com.theberdakh.tradingglossary.search.SearchFragment
import com.theberdakh.tradingglossary.data.Word
import com.theberdakh.tradingglossary.utils.addFragmentToBackStack
import com.theberdakh.englishkarakalpak.databinding.FragmentMainBinding
import com.theberdakh.tradingglossary.about.AboutFragment
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

        val nightMode = AppCompatDelegate.getDefaultNightMode()
        if (nightMode == AppCompatDelegate.MODE_NIGHT_YES || nightMode == AppCompatDelegate.MODE_NIGHT_AUTO_BATTERY || nightMode == AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM || nightMode == AppCompatDelegate.MODE_NIGHT_UNSPECIFIED) {
            binding.toolbar.navigationIcon = getDrawable(requireContext(), R.drawable.round_light_mode_24)

        } else {
            binding.toolbar.navigationIcon = getDrawable(requireContext(), R.drawable.baseline_mode_night_24)

        }

        binding.toolbar.setOnMenuItemClickListener { menuItem ->
            if(menuItem.itemId == R.id.action_about){
                addFragmentToBackStack(requireActivity().supportFragmentManager,
                    R.id.main_container,
                    AboutFragment()
                )
            }
            true
        }

        binding.toolbar.setNavigationOnClickListener {
            // Check the current night mode state

            if (nightMode == AppCompatDelegate.MODE_NIGHT_YES) {
                // If it's currently set to night mode, change to light mode
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            } else {
                // If it's currently set to light mode or undefined, change to night mode
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            }
            // Recreate the activity for the theme change to take effect
            recreate(requireActivity())
        }

        return binding.root
    }

    private fun initViews() {
        val adapter = WordAdapter{
            navigateToWordFragment(it)
        }
        binding.recyclerSearchBar.adapter = adapter
        val jsonString: String = requireContext().jsonToString("words.json")
        val arrayOfWords = convertJsonString(jsonString)

        val list = arrayOfWords.toList()
        adapter.submitList(list)
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