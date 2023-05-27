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
import com.wfd.qq.entity.Space_item
import com.wfd.qq.login_register.Welcome_Fragment

class Space_item_Adapter(val spaceList: List<Space_item>) : RecyclerView.Adapter<Space_item_Adapter.ViewHolder>() {

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        // 获取视图

        val image: ImageView = view.findViewById(R.id.space_head_sculpture)
        val time:TextView = view.findViewById(R.id.space_time)
        val uname: TextView = view.findViewById(R.id.space_u_name)
        val content:TextView = view.findViewById(R.id.space_content) ;
    }

    private var onItemClickListener: ((position: Int) -> Unit)? = null
    fun setOnItemClickListener(listener: (position: Int) -> Unit) {
        onItemClickListener = listener
    }


    // 用于创建ViewHolder实例的
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.space_item, parent, false)

        val viewHolder = ViewHolder(view)

        viewHolder.itemView.setOnClickListener {
            val position = viewHolder.bindingAdapterPosition
            onItemClickListener?.invoke(position)
        }

//        viewHolder.uname.setOnClickListener{
//            Toast.makeText(parent.context, "you clicked name",
//                Toast.LENGTH_SHORT).show()
//        }
//        viewHolder.image.setOnClickListener {
////            val position = viewHolder.adapterPosition
//            val position = viewHolder.bindingAdapterPosition
//            val msg = spaceList[position]
//            Toast.makeText(parent.context, "you clicked image ${msg.name}",
//                Toast.LENGTH_SHORT).show()
//        }

        return viewHolder
    }

    // 对RecyclerView子项的数据进行赋值
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val mes = spaceList[position]
        holder.image.setImageResource(mes.imageId)
        holder.time.text = mes.time
        holder.uname.text = mes.name
        holder.content.text=mes.content

        // 点击事件
        holder.itemView.setOnClickListener { onItemClickListener?.invoke(position) }
    }
    // 告诉RecyclerView一共有 多少子项
    override fun getItemCount() = spaceList.size

    interface OnItemClickListener {
        fun onItemClick(position:Int)
    }
}