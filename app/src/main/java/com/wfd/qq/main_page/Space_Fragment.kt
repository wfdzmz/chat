package com.wfd.qq.main_page
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.qq.R
import com.wfd.qq.Adapter.Space_item_Adapter
import com.wfd.qq.entity.Space_item
import com.wfd.qq.other_page.Chat_Fragment

class Space_Fragment : Fragment() , Space_item_Adapter.OnItemClickListener {

    private val space_list = ArrayList<Space_item>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_space, container, false)
        setHasOptionsMenu(true);  //保证能在Fragment里面调用onCreateOptionsMenu()方法

        init() // 初始化
//
        val layoutManager = LinearLayoutManager(context)
        val recyclerView = view.findViewById<RecyclerView>(R.id.space_recyclerView)

//        layoutManager.orientation = LinearLayoutManager.HORIZONTAL
        recyclerView.layoutManager = layoutManager

        val adapter = Space_item_Adapter(space_list)

        recyclerView.adapter = adapter.apply {
            setOnItemClickListener { position ->
                onItemClick(position)
            }
        }

        return view
    }


    override fun onItemClick(position:Int) {
        // 处理点击事件
        parentFragmentManager.beginTransaction().replace(R.id.nav_host_fragment, Chat_Fragment()).commit()
    }
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        menu.clear()
        inflater.inflate(R.menu.add_facebook, menu)
    }

    private fun init() {
        repeat(5) {
            space_list.add(Space_item("name1", "2023-5-23" , R.drawable.head1,"发个动态记录一下第一个动态实现了，哈哈哈哈哈哈哈哈哈！！！！"))
            space_list.add(Space_item("name2","20223-5-25", R.drawable.head2,getString(R.string.welcome)))
        }
    }
}