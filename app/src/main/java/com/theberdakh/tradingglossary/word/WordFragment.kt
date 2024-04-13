package com.theberdakh.tradingglossary.word

import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.theberdakh.tradingglossary.data.Word
import com.theberdakh.englishkarakalpak.databinding.FragmentWordBinding
import java.util.Locale

class WordFragment(private val word: Word): Fragment(), TextToSpeech.OnInitListener {
    private var _binding: FragmentWordBinding? =null
    private var tts: TextToSpeech? = null
    private val binding get() = checkNotNull(_binding)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWordBinding.inflate(inflater, container, false)
        tts = TextToSpeech(requireContext(), this)

        initViews()
        initListeners()

        return binding.root
    }

    private fun initListeners() {
        binding.toolbarAllWords.setNavigationOnClickListener {
            requireActivity().supportFragmentManager.popBackStack()
        }

        binding.chipListen.setOnClickListener {
            readWord(word.english)
        }
    }

    private fun readWord(word: String) {
        val isSpeaking = tts?.isSpeaking
        if (isSpeaking != null && !isSpeaking){
            tts?.speak(word, TextToSpeech.QUEUE_FLUSH, null, "")
        }


    }

    private fun initViews() {
        binding.title.text  = word.english
        binding.meaning.text = word.karakalpak
    }

    override fun onInit(status: Int) {
        if (status == TextToSpeech.SUCCESS) {
            val result = tts?.setLanguage(Locale.US)
            if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                Log.e("TTS","The Language not supported!")
            } else {
                Log.i("TTS", "TTS status $result")
            }
        }
    }

    override fun onDestroy() {
        _binding = null
        if (tts != null) {
            tts!!.stop()
            tts!!.shutdown()
        }
        super.onDestroy()
    }
}