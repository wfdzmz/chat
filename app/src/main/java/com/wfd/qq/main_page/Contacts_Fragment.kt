package com.wfd.qq.main_page
import android.content.Context
import android.os.Bundle
import android.view.*
import android.widget.Button
import android.widget.ExpandableListView
import androidx.fragment.app.Fragment
import com.example.qq.R
import com.wfd.qq.Adapter.ExpandableListAdapter
import com.wfd.qq.dao.FriendsDao
import com.wfd.qq.dao.GroupsDao
import com.wfd.qq.dao.UserDao
import com.wfd.qq.entity.AppDatabase
import com.wfd.qq.entity.User
import com.wfd.qq.login_register.Register_Fragment

class Contacts_Fragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_contacts, container, false)
        setHasOptionsMenu(true);  //保证能在Fragment里面调用onCreateOptionsMenu()方法

        val db = AppDatabase.getDatabase(requireContext())
        val TableUser = db.userDao()
        val TableFriens=db.friendsDao()
        val TableGroup=db.groupsDao()

        // 获取登录用户
        val prefs = activity?.getSharedPreferences("login_data", Context.MODE_PRIVATE)
        val account = prefs?.getString("account", "").toString()
        val user = TableUser.findByAccount(account)

        // 设置分组
        val expandableListView = view.findViewById<ExpandableListView>(R.id.expandable_list_view)


        val listgroup = TableGroup.selectAllGroupByUid(user.id) // 查询所有分组

        val parentList = mutableListOf<String>()    // 分组名
        val childList = mutableListOf<List<String>>()   // 分组的组内成员

        for(g in listgroup)
        {
            parentList.add(g.group)

            val c = mutableListOf<String>()
            val cc = TableFriens.selectfriendByUidAndGroupid(user.id,g.id) ;
            for(j in cc)
            {
                val friend = TableUser.findById(j.friend_id)
                c.add(friend.name) ;
            }
            childList.add(c)
        }
////        val expandableListView = view.findViewById<ExpandableListView>(R.id.expandable_list_view)
////        val groupList = mutableListOf<String>("新朋友", "家人", "同事")
////        val childList = mutableListOf<List<String>>(
////            listOf("李四", "王五"),
////            listOf("姐姐", "哥哥"),
////            listOf("老板", "经理")
////        )
//
        val adapter = ExpandableListAdapter(requireContext(), parentList, childList)
        expandableListView.setAdapter(adapter)


        return view
    }
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        menu.clear()
        inflater.inflate(R.menu.add_friend, menu)
    }
}