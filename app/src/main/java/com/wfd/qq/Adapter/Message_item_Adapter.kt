package com.wfd.qq.Adapter

import android.app.Activity
import android.media.Image
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.recyclerview.widget.RecyclerView
import com.example.qq.R
import com.wfd.qq.entity.Message_item
import com.wfd.qq.login_register.Welcome_Fragment

class Message_item_Adapter(val messagelist: List<Message_item>) : RecyclerView.Adapter<Message_item_Adapter.ViewHolder>() {

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        // 获取视图

        val fruitImage: ImageView = view.findViewById(R.id.head_sculpture)
        val fruitName: TextView = view.findViewById(R.id.u_name)
    }

    private var onItemClickListener: ((position: Int) -> Unit)? = null
    fun setOnItemClickListener(listener: (position: Int) -> Unit) {
        onItemClickListener = listener
    }


    // 用于创建ViewHolder实例的
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.message_item, parent, false)

        val viewHolder = ViewHolder(view)

        viewHolder.itemView.setOnClickListener {
            val position = viewHolder.bindingAdapterPosition
            onItemClickListener?.invoke(position)
        }

//        viewHolder.fruitName.setOnClickListener{
//            Toast.makeText(parent.context, "you clicked name",
//                Toast.LENGTH_SHORT).show()
//        }
//        viewHolder.fruitImage.setOnClickListener {
////            val position = viewHolder.adapterPosition
//            val position = viewHolder.bindingAdapterPosition
//            val msg = messagelist[position]
//            Toast.makeText(parent.context, "you clicked image ${msg.name}",
//                Toast.LENGTH_SHORT).show()
//        }

        return viewHolder
    }

    // 对RecyclerView子项的数据进行赋值
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val mes = messagelist[position]
        holder.fruitImage.setImageResource(mes.imageId)
        holder.fruitName.text = mes.name

        // 点击事件
        holder.itemView.setOnClickListener { onItemClickListener?.invoke(position) }
    }
    // 告诉RecyclerView一共有 多少子项
    override fun getItemCount() = messagelist.size

    interface OnItemClickListener {
        fun onItemClick(position:Int)
    }
}