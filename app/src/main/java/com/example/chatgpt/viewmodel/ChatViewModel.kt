package com.example.chatgpt.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.chatgpt.models.Chat
import kotlinx.coroutines.launch

class ChatViewModel(application:Application):AndroidViewModel(application) {
    var chatList=MutableLiveData<List<Chat>>(arrayListOf())
        private set

    fun insertChat(chat: Chat){
        viewModelScope.launch {
            val modifiedChatList=ArrayList<Chat>().apply {
                addAll(chatList.value!!)
            }
            modifiedChatList.add(chat)
            chatList.postValue(modifiedChatList)
        }
    }

}