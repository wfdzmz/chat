package com.wfd.qq.login_register


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.qq.R
import com.wfd.qq.Adapter.Message_item_Adapter
import com.wfd.qq.dao.UserDao
import com.wfd.qq.entity.AppDatabase
import com.wfd.qq.entity.Message_item
import com.wfd.qq.entity.User

class Message : Fragment(){

//    private lateinit var db: AppDatabase
//    private lateinit var TableUser: UserDao
//    private lateinit var accountEdit:EditText
//    private lateinit var nameEdit: EditText
//    private lateinit var passwordEdit1: EditText
//    private lateinit var passwordEdit2: EditText

    private val message_item = ArrayList<Message_item>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_message, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 获取数据库链接以及控件链接
//        db = AppDatabase.getDatabase(requireContext())
//        accountEdit = view.findViewById<EditText>(R.id.account)
//        nameEdit = view.findViewById<EditText>(R.id.name)
//        passwordEdit1 = view.findViewById<EditText>(R.id.password1)
//        passwordEdit2 = view.findViewById<EditText>(R.id.password2)
//        TableUser = db.userDao() ;

        initFruits() // 初始化

        val layoutManager = LinearLayoutManager(requireContext())
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)

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