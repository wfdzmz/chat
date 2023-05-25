package com.wfd.qq

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.qq.R
import com.wfd.qq.Adapter.Message_item_Adapter
import com.wfd.qq.entity.Message_item

class Message222 : AppCompatActivity() {
    private val message_item = ArrayList<Message_item>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initFruits() // 初始化

        val layoutManager = LinearLayoutManager(this)
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)

//        layoutManager.orientation = LinearLayoutManager.HORIZONTAL
        recyclerView.layoutManager = layoutManager
        val adapter = Message_item_Adapter(message_item)
        recyclerView.adapter = adapter
    }
    private fun initFruits() {
        repeat(10) {
            message_item.add(Message_item("name1", R.drawable.ic_launcher_foreground))
            message_item.add(Message_item("name2", R.drawable.ic_launcher_foreground))
        }
    }
}