package com.example.chatgpt.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.navigation.fragment.findNavController
import com.example.chatgpt.R
import com.example.chatgpt.databinding.FragmentWelcomeScreenBinding


class WelcomeScreenFragment : Fragment() {
    private var _binding: FragmentWelcomeScreenBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentWelcomeScreenBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            continueBtn.apply {
                AnimationUtils.loadAnimation(requireContext(),R.anim.zoom_in_cut)
                setOnClickListener {
                    val action=WelcomeScreenFragmentDirections.actionWelcomeScreenFragmentToRobotListFragment()
                    findNavController().navigate(action)
                }
            }
        }

    }

}