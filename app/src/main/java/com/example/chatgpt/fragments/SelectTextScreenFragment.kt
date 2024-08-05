package com.example.chatgpt.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.chatgpt.R
import com.example.chatgpt.databinding.FragmentSelectTextScreenBinding
import com.example.chatgpt.utils.gone

class SelectTextScreenFragment : Fragment() {
    private var _binding: FragmentSelectTextScreenBinding? = null
    private val binding get() = _binding!!
    private val selectArgs by navArgs<SelectTextScreenFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSelectTextScreenBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        selectArgs.let {
            binding.selectTxt.text=selectArgs.selectedMessage
        }
        binding.toolbarLayout.apply {
            robotImageLL.gone()
            backImg.apply {
                setImageResource(R.drawable.ic_close)
                setOnClickListener {
                    findNavController().navigateUp()
                }
            }
        }





    }
}