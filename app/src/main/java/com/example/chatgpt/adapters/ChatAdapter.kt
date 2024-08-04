package com.example.chatgpt.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.chatgpt.databinding.ItemReceiverBinding
import com.example.chatgpt.databinding.ItemSenderBinding
import com.example.chatgpt.models.Chat
import java.text.SimpleDateFormat
import java.util.Locale


class ChatAdapter:ListAdapter<Chat,RecyclerView.ViewHolder>(DiffCallback()) {

    class SenderViewHolder(private val itemSenderBinding: ItemSenderBinding):RecyclerView.ViewHolder(itemSenderBinding.root){
        fun bind(chat: Chat){
            itemSenderBinding.apply {
            txtMessage.text=chat.message
            val dataFormat=SimpleDateFormat("dd-MMM-yyyy HH:mm a", Locale.getDefault())
                txtDate.text=dataFormat.format(chat.date)
              }
        }
    }


    class ReceiverViewHolder(private val itemReceiverBinding: ItemReceiverBinding):
        RecyclerView.ViewHolder(itemReceiverBinding.root){
        fun bind(chat: Chat){
            itemReceiverBinding.apply {
                txtMessage.text=chat.message
                val dataFormat=SimpleDateFormat("dd-MMM-yyyy HH:mm a", Locale.getDefault())
                txtDate.text=dataFormat.format(chat.date)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
    return if (viewType==1){ //receiver item
         ReceiverViewHolder(
         ItemReceiverBinding.inflate(
             LayoutInflater.from(parent.context)
             ,parent,false
         )
  )



}else{ //sender item
        SenderViewHolder(
            ItemSenderBinding.inflate(
                LayoutInflater.from(parent.context)
                ,parent,false
            )
        )
    }

    }



    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
val chat=getItem(position)
        if (chat.messageType.equals("sender",true)){
            (holder as SenderViewHolder).bind(chat)
        }else{
            (holder as ReceiverViewHolder).bind(chat)

        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (getItem(position).messageType.equals("sender",true)){
            0  //sender item
        }else{
            1   //receiver item
        }
    }
    class DiffCallback:DiffUtil.ItemCallback<Chat> () {     //22:19 ghesmat 2
        override fun areItemsTheSame(oldItem: Chat, newItem: Chat): Boolean {
            return oldItem.chatId==newItem.chatId
        }

        override fun areContentsTheSame(oldItem: Chat, newItem: Chat): Boolean {
            return oldItem==newItem
        }
    }


}