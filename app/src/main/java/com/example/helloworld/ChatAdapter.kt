package com.example.containerrecyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.helloworld.Message
import com.example.helloworld.R
import kotlinx.android.synthetic.main.main_lv_item.view.*
import java.text.SimpleDateFormat

class ChatAdapter : RecyclerView.Adapter<Holder>() {
    var listData = mutableListOf<Message>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.main_lv_item, parent, false)
        return Holder(view)
    }

    override fun getItemCount(): Int {
        return listData.size
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val message = listData.get(position)
        holder.setMessage(message)
    }
}

class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun setMessage(message:Message) {
        // 받아온 데이터를 바인딩
        itemView.txtContent.text = message.content
        itemView.txtTime.text = message.Time
    }
}

