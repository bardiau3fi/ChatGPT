package com.example.chatgpt.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.chatgpt.R
import com.example.chatgpt.databinding.FragmentChatScreenBinding
import com.example.chatgpt.databinding.FragmentRobotListBinding


class RobotListFragment : Fragment() {
    private var _binding: FragmentRobotListBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentRobotListBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.moveChatBtn.setOnClickListener {
            val action=RobotListFragmentDirections.actionRobotListFragmentToChatScreenFragment()
            findNavController().navigate(action)
        }
    }

}