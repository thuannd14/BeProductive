package com.ndt.beproductive.fragment

import android.annotation.SuppressLint
import android.text.format.DateUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.ndt.beproductive.R
import com.ndt.beproductive.act.MainActivity
import com.ndt.beproductive.adapter.ChatAdapter
import com.ndt.beproductive.databinding.FragmentChatbotAiBinding
import com.ndt.beproductive.dialog.LoadingDialog
import com.ndt.beproductive.model.Message
import com.ndt.beproductive.viewmodel.ChatbotViewModel
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class ChatbotFragment: BaseFrag<FragmentChatbotAiBinding, ChatbotViewModel>() {
    companion object {
        val TAG: String = ChatbotFragment::class.java.name
    }
    private lateinit var dialog: LoadingDialog
    private val messageList = mutableListOf<Message>()
    val chatAdapter = ChatAdapter(messageList)
    @SuppressLint("NotifyDataSetChanged")
    override fun initViews() {

        dialog = LoadingDialog(mContext)
        binding.tvBack.setOnClickListener {
            mCallBack.backPrevious()
        }
        binding.recyclerViewMessages.adapter = chatAdapter
        binding.recyclerViewMessages.layoutManager = LinearLayoutManager(context)

        binding.sendButton.setOnClickListener {
            val msgText = binding.edtUserMessage.text.toString().trim()
            if (msgText.isNotEmpty()) {
                val time = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"))
                val userMessage = Message(msgText, true, time)
                messageList.add(userMessage)
                chatAdapter.notifyItemInserted(messageList.size - 1)
                binding.recyclerViewMessages.scrollToPosition(messageList.size - 1)
                viewModel.getContent(msgText)
                dialog.show()
                binding.edtUserMessage.text.clear()
            }
        }

        viewModel.chatResponse.observe(viewLifecycleOwner) { response ->
            dialog.dismiss()
            val time = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"))
            val botMessage = Message(response, false, time)
            messageList.add(botMessage)
            chatAdapter.notifyItemInserted(messageList.size - 1)
            binding.recyclerViewMessages.scrollToPosition(messageList.size - 1)
        }

        binding.tvMenu.setOnClickListener {
            val popup = PopupMenu(mContext, it)
            popup.menuInflater.inflate(R.menu.chat_popup_menu, popup.menu)

            popup.setOnMenuItemClickListener { menuItem ->
                when (menuItem.itemId) {
                    R.id.menu_clear_chat -> {
                        messageList.clear()
                        chatAdapter.notifyDataSetChanged()
                        if(messageList.size == 0) {
                            Toast.makeText(mContext, "Chat cleared", Toast.LENGTH_SHORT).show()
                        }
                        true
                    }
                    else -> false
                }
            }

            popup.show()
        }

    }

    private fun openMenu() {
        // clear chat
    }


    override fun initViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentChatbotAiBinding {
        return FragmentChatbotAiBinding.inflate(inflater, container, false)
    }

    override fun getClassVM(): Class<ChatbotViewModel> {
        return ChatbotViewModel::class.java
    }
}