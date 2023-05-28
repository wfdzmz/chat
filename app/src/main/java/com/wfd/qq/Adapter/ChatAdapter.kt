//package com.wfd.qq.Adapter
//
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.widget.TextView
//import androidx.recyclerview.widget.RecyclerView
//import com.example.qq.R
//import com.wfd.qq.entity.Message
//
//class ChatAdapter(val msgList: List<Message>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
//    inner class LeftViewHolder(view: View) : RecyclerView.ViewHolder(view) {
//        val leftMsg: TextView = view.findViewById(R.id.left_message)
//    }
//    inner class RightViewHolder(view: View) : RecyclerView.ViewHolder(view) {
//        val rightMsg: TextView = view.findViewById(R.id.right_message)
//    }
//    override fun getItemViewType(position: Int): Int {
//        val msg = msgList[position]
//        return msg.msg_type
//    }
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = if (viewType == 1) {
//        val view = LayoutInflater.from(parent.context).inflate(R.layout.message_left_item,
//            parent, false)
//        LeftViewHolder(view)
//    } else {
//        val view = LayoutInflater.from(parent.context).inflate(R.layout.message_right_item,
//            parent, false)
//        RightViewHolder(view)
//    }
//    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
//        val msg = msgList[position]
//        when (holder) {
//            is LeftViewHolder -> holder.leftMsg.text = msg.content
//            is RightViewHolder -> holder.rightMsg.text = msg.content
//            else -> throw IllegalArgumentException()
//        }
//    }
//    override fun getItemCount() = msgList.size
//}