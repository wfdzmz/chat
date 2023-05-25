package com.wfd.qq.Adapter

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.qq.R
import com.wfd.qq.entity.Message_item

class Message_item_Adapter(val fruitList: List<Message_item>) : RecyclerView.Adapter<Message_item_Adapter.ViewHolder>() {

inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    // 获取视图
    val fruitImage: ImageView = view.findViewById(R.id.head_sculpture)
    val fruitName: TextView = view.findViewById(R.id.u_name)
}

    // 用于创建ViewHolder实例的
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_message, parent, false)
        val viewHolder = ViewHolder(view)
        viewHolder.itemView.setOnClickListener {
//            val position = viewHolder.adapterPosition
            val position = viewHolder.bindingAdapterPosition
            val fruit = fruitList[position]
            Toast.makeText(parent.context, "you clicked view ${fruit.name}",
                Toast.LENGTH_SHORT).show()
        }
        viewHolder.fruitName.setOnClickListener{
            Toast.makeText(parent.context, "you clicked name",
                Toast.LENGTH_SHORT).show()
        }
        viewHolder.fruitImage.setOnClickListener {
//            val position = viewHolder.adapterPosition
            val position = viewHolder.bindingAdapterPosition
            val fruit = fruitList[position]
            Toast.makeText(parent.context, "you clicked image ${fruit.name}",
                Toast.LENGTH_SHORT).show()
        }
        return viewHolder
    }

    // 对RecyclerView子项的数据进行赋值
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val fruit = fruitList[position]
//        holder.fruitImage.setImageResource(fruit.imageId)
//        holder.fruitName.text = fruit.name
        holder.fruitName.text = "用户名"
    }
    // 告诉RecyclerView一共有 多少子项
    override fun getItemCount() = fruitList.size
}