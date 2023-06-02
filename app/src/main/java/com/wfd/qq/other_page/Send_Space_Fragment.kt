package com.wfd.qq.other_page

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.qq.R
import com.wfd.qq.dao.UserDao
import com.wfd.qq.entity.AppDatabase
import com.wfd.qq.entity.Space
import com.wfd.qq.login_register.Register_Fragment
import com.wfd.qq.main_page.Space_Fragment
import java.text.SimpleDateFormat
import java.util.*

class Send_Space_Fragment : Fragment() {


    private lateinit var edit: EditText

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_add_space, container, false)

        val space_sent_button = view.findViewById<Button>(R.id.space_sent_button)
        space_sent_button.setOnClickListener {
            edit = view.findViewById<EditText>(R.id.space_content)
            val content = edit.text.toString()

            val db = AppDatabase.getDatabase(requireContext())
            val TableUser = db.userDao()
            val TableSpace = db.spaceDao()

            val prefs = activity?.getSharedPreferences("login_data", Context.MODE_PRIVATE)
            val account = prefs?.getString("account", "").toString()
            val user = TableUser.findByAccount(account)

            val currentDate = Date()
            val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
            val dateString = dateFormat.format(currentDate)

            TableSpace.insertSpace(Space(u_id = user.id , time = dateString ,content = content , image = 1))
            Toast.makeText(requireContext() , "发送成功" , Toast.LENGTH_SHORT).show()

            parentFragmentManager.beginTransaction()
                .replace(R.id.nav_host_fragment, Space_Fragment())
                .commit()
        }


        return view
    }
}