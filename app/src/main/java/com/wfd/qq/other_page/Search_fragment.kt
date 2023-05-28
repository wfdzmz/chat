package com.wfd.qq.other_page

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import com.example.qq.R
import com.wfd.qq.dao.FriendsDao
import com.wfd.qq.dao.UserDao
import com.wfd.qq.entity.AppDatabase
import com.wfd.qq.entity.Friends
import com.wfd.qq.entity.User

class Search_fragment : Fragment() {

    private lateinit var db: AppDatabase
    private lateinit var TableUser: UserDao
    private lateinit var TableFriens:FriendsDao
    private lateinit var accountEdit: EditText
    private lateinit var searcTextView: TextView
    private lateinit var user:User

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_search, container, false)


        db = AppDatabase.getDatabase(requireContext())
        TableUser = db.userDao()
        TableFriens = db.friendsDao()

        val prefs = activity?.getSharedPreferences("login_data",Context.MODE_PRIVATE)
        val account = prefs?.getString("account", "").toString()
        user = TableUser.findByAccount(account)

        val search_Button = view.findViewById<Button>(R.id.search_button)

        // 查询按钮被点击
        search_Button.setOnClickListener {
            val search_add = view.findViewById<Button>(R.id.search_add_friend)
            accountEdit = view.findViewById<EditText>(R.id.search_account)
            val account = accountEdit.text.toString()
            val friend = TableUser.findByAccount(account)

            val search_find_layout = view.findViewById<LinearLayout>(R.id.search_find_layout)
            search_find_layout.setVisibility(View.VISIBLE)

            searcTextView = view.findViewById<EditText>(R.id.search_u_name)
            if(friend != null) {
                search_add.visibility=View.VISIBLE
                searcTextView.setText(friend.name)
            }
            else {
                search_add.visibility=View.GONE
                searcTextView.setText("未查到该用户")
            }
        }

        // 添加好友
        val search_add = view.findViewById<Button>(R.id.search_add_friend)
        search_add.setOnClickListener {

            accountEdit = view.findViewById<EditText>(R.id.search_account)
            val account = accountEdit.text.toString()
            val friend = TableUser.findByAccount(account)

            val friends=Friends(u_id=user.id, friend_id = friend.id, group_id = 1)

            val relationship = TableFriens.selectfriendByUidAndFid(user.id , friend.id) ;
            if(relationship == null) {
                TableFriens.insertFriends(friends)
                friends.u_id = friend.id
                friends.friend_id = user.id
                TableFriens.insertFriends(friends)
                Toast.makeText(requireContext(),"添加成功",Toast.LENGTH_LONG)
            }
            else Toast.makeText(requireContext(),"该好友已添加",Toast.LENGTH_LONG)
        }
        return view
    }

}