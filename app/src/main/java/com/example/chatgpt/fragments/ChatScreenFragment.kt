package com.example.chatgpt.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.chatgpt.R
import com.example.chatgpt.adapters.ChatAdapter
import com.example.chatgpt.databinding.FragmentChatScreenBinding
import com.example.chatgpt.models.Chat
import com.example.chatgpt.utils.copyToClipBoard
import com.example.chatgpt.utils.hideKeyBoard
import com.example.chatgpt.utils.longToastShow
import com.example.chatgpt.utils.shareMsg
import com.example.chatgpt.viewmodel.ChatViewModel
import java.util.Date


class ChatScreenFragment : Fragment() {
    private var _binding: FragmentChatScreenBinding? = null
    private val binding get() = _binding!!

    private val chatViewModel:ChatViewModel by lazy {
        ViewModelProvider(this)[ChatViewModel::class.java]
    }
   private val chatList = arrayListOf(
        Chat(
            "1",
            "Hi, how can I integrate Retrofit in Android development?",
            "sender",
            Date()
        ),
        Chat(
            "2",
            "You can integrate Retrofit by adding it as a dependency in your app's build.gradle file.",
            "receiver",
            Date()
        ),
        Chat(
            "3",
            "What is the purpose of ViewModel in MVVM architecture?",
            "sender",
            Date()
        ),
        Chat(
            "4",
            "ViewModel is used to store and manage UI-related data, separating it from the UI components.",
            "receiver",
            Date()
        ),
        Chat(
            "5",
            "What is the purpose of ViewModel in MVVM architecture?",
            "sender",
            Date()
        ),
        Chat(
            "6",
            "ViewModel is used to store and manage UI-related data, separating it from the UI components.",
            "receiver",
            Date()
        ),
        Chat(
            "7",
            "How do I implement a RecyclerView in Android?",
            "sender",
            Date()
        ),
        Chat(
            "8",
            "You can create a RecyclerView by defining a layout, adapter, and connecting it to your data source.",
            "receiver",
            Date()
        ),
        Chat(
            "9",
            "Can you suggest a good Android IDE?",
            "sender",
            Date()
        ),
        Chat(
            "10",
            "Android Studio is the official IDE for Android app development and is highly recommended.",
            "receiver",
            Date()
        ),
        Chat(
            "11",
            "How can I implement a swipe-to-refresh feature in my Android app?",
            "sender",
            Date()
        ),
        Chat(
            "12",
            "You can implement swipe-to-refresh by using the SwipeRefreshLayout widget in your layout XML and handling refresh events in your code.",
            "receiver",
            Date()
        )
    )
    private  var chatAdapter= ChatAdapter(){message,textView ->
        val popup=PopupMenu(context,textView)
        try {
            val fields = popup.javaClass.declaredFields
            for (field in fields) {
                if ("mPopup" == field.name) {
                    field.isAccessible = true
                    val menuPopupHelper = field.get(popup)
                    val classPopupHelper = Class.forName(menuPopupHelper.javaClass.name)
                    val setForceIcons = classPopupHelper.getMethod(
                        "setForceShowIcon",
                        Boolean::class.javaPrimitiveType
                    )
                    setForceIcons.invoke(menuPopupHelper, true)
                    break
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        popup.menuInflater.inflate(R.menu.option_menu,popup.menu)
        popup.setOnMenuItemClickListener {item->
            when(item.itemId){
                //copy
                R.id.coppyMenu->{
                    view?.context?.copyToClipBoard(message)
                    return@setOnMenuItemClickListener true}
                //select
                R.id.selectTxtMenu->{
                     val action=ChatScreenFragmentDirections.actionChatScreenFragmentToSelectTextScreenFragment(message)
                 findNavController().navigate(action)


                    return@setOnMenuItemClickListener true}
                //share
                R.id.shareTxtMenu->{
                    view?.context?.shareMsg(message)
                    return@setOnMenuItemClickListener true}
                else->{return@setOnMenuItemClickListener true}
            }
        }
    popup.show()
}

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentChatScreenBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            chatRv.adapter=chatAdapter
            chatAdapter.submitList(chatList)
            var counter=-1
        sendImageBtn.setOnClickListener {
            view.context.hideKeyBoard(it)
            if (edMessage.text.toString().trim().isNotEmpty()){
                counter+=1
                if (counter>=chatList.size){
                    return@setOnClickListener
            }
                chatViewModel.insertChat(chatList[counter])

            }else{
                view.context.longToastShow("Message Is Required")
            }

        }







            chatViewModel.chatList.observe(viewLifecycleOwner){
                chatAdapter.submitList(it)
                chatRv.smoothScrollToPosition(it.size)
            }


        }

    }
}