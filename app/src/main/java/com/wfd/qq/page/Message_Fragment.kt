package com.wfd.qq.page


import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.qq.R
import com.wfd.qq.Adapter.Message_item_Adapter
import com.wfd.qq.entity.Message_item

class Message_Fragment : Fragment() , Message_item_Adapter.OnItemClickListener {

//    private lateinit var db: AppDatabase
//    private lateinit var TableUser: UserDao
//    private lateinit var accountEdit:EditText
//    private lateinit var nameEdit: EditText
//    private lateinit var passwordEdit1: EditText
//    private lateinit var passwordEdit2: EditText

    private val message_list = ArrayList<Message_item>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        setHasOptionsMenu(true);  //保证能在Fragment里面调用onCreateOptionsMenu()方法
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
//
        val layoutManager = LinearLayoutManager(context)
        val recyclerView = view.findViewById<RecyclerView>(R.id.message_recyclerView)

//        layoutManager.orientation = LinearLayoutManager.HORIZONTAL
        recyclerView.layoutManager = layoutManager

        val adapter = Message_item_Adapter(message_list)

        recyclerView.adapter = adapter.apply {
            setOnItemClickListener { position ->
                onItemClick(position)
            }
        }
    }
    override fun onItemClick(position:Int) {
        // 处理点击事件
        parentFragmentManager.beginTransaction().replace(R.id.nav_host_fragment, Chat_Fragment()).commit()
    }
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        menu.clear()
        inflater.inflate(R.menu.sign_out, menu)
    }
    private fun initFruits() {
        repeat(20) {
            message_list.add(Message_item("name1", R.drawable.foreground))
            message_list.add(Message_item("name2", R.drawable.contacts_yes))
        }
    }

}