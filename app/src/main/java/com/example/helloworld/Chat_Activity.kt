package com.example.helloworld

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.containerrecyclerview.ChatAdapter
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_chat_.*
import kotlinx.android.synthetic.main.main_lv_item.*
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.util.*

class Chat_Activity : AppCompatActivity() {

    // adapter 설정
    var adapter = ChatAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat_)

        // Main Activity에서 설정하면 여기서는 안해도 적용됨.
//        // Hide the status bar.
//        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
//        // Remember that you should never show the action bar if the
//        // status bar is hidden, so hide that too if necessary.
//        actionBar?.hide()

        // recycler 뷰의 adater 설정 1회만 해줌
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        // intent로 뷰 간의 데이터 전송.
//        var user = intent.getSerializableExtra("user") as FirebaseUser?

//        Toast.makeText(this,"displayName" + user?.displayName.toString(), Toast.LENGTH_SHORT).show()
//        Toast.makeText(this,"email" + user?.email.toString(), Toast.LENGTH_SHORT).show()
//        Toast.makeText(this,"phoneNumber" + user?.phoneNumber.toString(), Toast.LENGTH_SHORT).show()

        // send message when press "Done" on keyboard
        txtMessage.setOnKeyListener(View.OnKeyListener { v, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_ENTER) {
                //Perform Code
                addMessage()
                return@OnKeyListener true
            }
            false
        })

        btnEnter.setOnClickListener{
            addMessage()
        }
    }

    private fun addMessage(){
        val msg = txtMessage.text.toString()

        // 내용이 있는 경우만 message 올림
        if(msg.length > 0) {
            // 메시지 옆에 표시될 시간. '오전 10:22'
            val currentDateTime = Calendar.getInstance().time
            val dateFormat = SimpleDateFormat("a h:mm", Locale.KOREA).format(currentDateTime)

            // message 클래스 타입으로 데이터 추가 및 데이터 반영
            adapter.listData.add(Message(txtMessage.text.toString(), dateFormat))
//        recyclerView.adapter = adapter
//        recyclerView.layoutManager = LinearLayoutManager(this)

            // 스크롤 위치를 맨 아래로 이동
            recyclerView.scrollToPosition(adapter.getItemCount() - 1)

            // 텍스트 입력 위젯의 데이터 초기화
            txtMessage.setText("")
        }
    }
}
