package com.example.helloworld

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_chat_.*
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.util.*

class Chat_Activity : AppCompatActivity() {

    var messageList = arrayListOf<Message>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat_)

        messageList.add(Message("어제?", "13:33"))
        messageList.add(Message("오늘?", "14:33"))
        messageList.add(Message("내일?", "15:33"))


        var messageAdapter = MainListAdapter(this, messageList)
        mainListView.adapter = messageAdapter

        btnEnter.setOnClickListener{
            messageList.add(Message(txtMessage.text.toString(), "15:33"))
            messageAdapter = MainListAdapter(this, messageList)
            mainListView.adapter = messageAdapter
        }
    }
}
