package com.example.helloworld

import android.content.Context
import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import kotlinx.android.synthetic.main.main_lv_item.view.*

class MainListAdapter(val context:Context, val messageList:ArrayList<Message>):BaseAdapter() {
//     xml 파일의 View와 데이터를 연결하는 핵심 역할을 하는 메소드이다.
    override fun getView(position: Int, converView: View?, parent: ViewGroup?): View {
    /* LayoutInflater는 item을 Adapter에서 사용할 View로 부풀려주는(inflate) 역할을 한다. */
        var view:View = LayoutInflater.from(context).inflate(R.layout.main_lv_item, null)

        /* ArrayList<Dog>의 변수 dog의 이미지와 데이터를 ImageView와 TextView에 담는다. */
        val message = messageList[position]

        view.txtContent.text = message.content
        view.txtTime.text = message.Time

        return view

    }

//    해당 위치의 item을 메소드이다. Int 형식으로 된 position을 파라미터로 갖는다.
//    예를 들어 1번째 Dog item을 선택하고 싶으면 코드에서 getItem(0)과 같이 쓸 수 있을 것이다.
    override fun getItem(p0: Int): Any {
        return messageList[p0]
    }

//    해당 위치의 item id를 반환하는 메소드이다. 이 예제에서는 실질적으로 id가 필요하지 않아서 0을 반환하도록 설정했다.
    override fun getItemId(p0: Int): Long {
        return 0
    }

//    ListView에 속한 item의 전체 수를 반환한다.
    override fun getCount(): Int {
        return messageList.size
    }
}