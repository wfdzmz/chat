package com.wfd.qq.other_page

import android.content.Context
import android.os.Bundle
import android.text.InputType
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.example.qq.R
import com.wfd.qq.dao.FriendsDao
import com.wfd.qq.dao.GroupsDao
import com.wfd.qq.dao.UserDao
import com.wfd.qq.entity.AppDatabase
import com.wfd.qq.entity.Friends
import com.wfd.qq.entity.User

class Search_fragment : Fragment() {

    private lateinit var db: AppDatabase
    private lateinit var TableUser: UserDao
    private lateinit var TableFriens:FriendsDao
    private lateinit var TableGroups: GroupsDao
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
        TableGroups = db.groupsDao()

        // 当前登录用户
        val prefs = activity?.getSharedPreferences("login_data",Context.MODE_PRIVATE)
        var account = prefs?.getString("account", "").toString()
        user = TableUser.findByAccount(account)

        val search_Button = view.findViewById<Button>(R.id.search_button)
        // 查询按钮被点击
        search_Button.setOnClickListener {

            accountEdit = view.findViewById<EditText>(R.id.search_account)
            account = accountEdit.text.toString()
            val friend = TableUser.findByAccount(account)

            val search_find_layout = view.findViewById<LinearLayout>(R.id.search_find_layout)
            search_find_layout.setVisibility(View.VISIBLE)


            val search_add = view.findViewById<Button>(R.id.search_add_friend)
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
            account = accountEdit.text.toString()
            val friend = TableUser.findByAccount(account)

            val relationship = TableFriens.selectfriendByUidAndFid(user.id, friend.id)

            println(user)
            println(friend)
            println(relationship)
            print("\n----\n")

            if (relationship != null) Toast.makeText(
                requireContext(),
                "你们已经是朋友了哦",
                Toast.LENGTH_LONG
            ).show()
            else {
                val builder = AlertDialog.Builder(requireContext())
                builder.setTitle("请输入要加入的组")

                // 设置布局
                val input = EditText(requireContext())
                input.inputType = InputType.TYPE_CLASS_TEXT
                builder.setView(input)

                // 设置按钮
                builder.setPositiveButton("确定") { dialog, which ->
                    val text = input.text.toString()
                    val g = TableGroups.selectAllGroupByUidandGroup(user.id, text)

                    if (g == null) Toast.makeText(requireContext(), "该分组不存在", Toast.LENGTH_LONG)
                        .show()
                    else {
                        val friends = Friends(u_id = user.id, friend_id = friend.id, group_id = g.id)

                        TableFriens.insertFriends(friends)

                        // 好友的默认分组为好友组。
                        friends.u_id = friend.id
                        friends.friend_id = user.id
                        friends.group_id = 0;

                        TableFriens.insertFriends(friends)

                        Toast.makeText(requireContext(), "添加成功", Toast.LENGTH_LONG).show()
                    }
                }

                builder.setNegativeButton("取消") { dialog, which ->
                    dialog.cancel()
                }
                // 创建对话框并显示
                val dialog = builder.create()
                dialog.show()
            }
        }
        return view
    }

}