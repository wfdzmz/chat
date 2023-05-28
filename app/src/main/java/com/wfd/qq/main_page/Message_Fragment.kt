package com.wfd.qq.main_page


import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.qq.R
import com.wfd.qq.Adapter.Message_item_Adapter
import com.wfd.qq.entity.Message_item
import com.wfd.qq.other_page.Chat_Fragment

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

        // 设置标题
//        val toolbar = view.findViewById<Toolbar>(R.id.toolbar)
//        (activity as AppCompatActivity).setSupportActionBar(toolbar)
//        val actionBar = (activity as AppCompatActivity).supportActionBar
//        actionBar?.title = "message"

        init() // 初始化
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
        parentFragmentManager.beginTransaction().replace(R.id.nav_host_fragment, Chat_Fragment()).addToBackStack(null).commit()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        menu.clear()
        inflater.inflate(R.menu.sign_out, menu)
    }
    private fun init() {
        repeat(1) {
            message_list.add(Message_item("lihua", R.drawable.head1))
            message_list.add(Message_item("wnger", R.drawable.head4))

            message_list.add(Message_item("zansan", R.drawable.head2))
            message_list.add(Message_item("xiaomin", R.drawable.head3))
            message_list.add(Message_item("haha", R.drawable.head5))
        }
    }

}