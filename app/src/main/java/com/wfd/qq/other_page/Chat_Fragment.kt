package com.wfd.qq.other_page

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.qq.R
import com.wfd.qq.Adapter.ChatAdapter
import com.wfd.qq.dao.MessageDao
import com.wfd.qq.dao.UserDao
import com.wfd.qq.entity.AppDatabase
import com.wfd.qq.entity.Message
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class Chat_Fragment : Fragment() {
    private lateinit var db: AppDatabase
    private lateinit var TableUser: UserDao
    private lateinit var TableMessage: MessageDao

    private var msgList = ArrayList<Message>()

    companion object {
        var friendname : String = ""
    }

    override fun onDetach(){
        super.onDetach()


        val act = requireActivity() as AppCompatActivity
        val actionBar = act.supportActionBar            // 获取ActionBar对象
        val prefs = activity?.getSharedPreferences("login_data", Context.MODE_PRIVATE)
        val name = prefs?.getString("name", "")

        actionBar?.title = name

        Log.e("MainActivityv" , "onDetach")
    }
//    override fun back
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_chat, container, false)

        val act = requireActivity() as AppCompatActivity
        val actionBar = act.supportActionBar            // 获取ActionBar对象
        actionBar?.title = friendname

        // 查询信息
        db = AppDatabase.getDatabase(requireContext())
        TableMessage = db.messageDao()
        TableUser = db.userDao()

        val prefs = activity?.getSharedPreferences("login_data", Context.MODE_PRIVATE)
        val account = prefs?.getString("account", "").toString()

        val user = TableUser.findByAccount(account)
        val friend = TableUser.findByName(friendname)


        msgList = TableMessage.selectMessageByUidAndFid(user.id , friend.id) as ArrayList<Message>

        val layoutManager = LinearLayoutManager(requireContext())
        val recyclerView = view.findViewById<RecyclerView>(R.id.chat_recyclerView)
        recyclerView.layoutManager = layoutManager

        val adapter = ChatAdapter(msgList)
        recyclerView.adapter = adapter

        // 发送消息
        val send = view.findViewById<Button>(R.id.chat_send)
        send.setOnClickListener{
            val enter = view.findViewById<EditText>(R.id.enter)
            val content = enter.text.toString()
            if (content != "") {
                val currentDate = Date()
                val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
                val dateString = dateFormat.format(currentDate)


                val msg = Message(u_id1 = user.id, u_id2 = friend.id, time = dateString , content = content, type = Message.TYPE_SENT)
                val msg2 = Message(u_id2 = user.id, u_id1 = friend.id, time = dateString , content = content, type = Message.TYPE_RECEIVED)

                msgList.add(msg)
                println("发送的消息:"+(msg.type)+"\n内容："+content+"\n------------\n")
                adapter.notifyItemInserted(msgList.size - 1) // 当有新消息时，
                // 刷新RecyclerView中的显示
                recyclerView.scrollToPosition(msgList.size - 1) // 将RecyclerView
                // 定位到最后一行
                enter.setText("") // 清空输入框中的内容

                TableMessage.insertMessage(msg)
                TableMessage.insertMessage(msg2)
            }
        }

        return view
    }
}