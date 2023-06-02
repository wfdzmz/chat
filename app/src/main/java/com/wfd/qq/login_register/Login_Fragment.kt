package com.wfd.qq.login_register

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.qq.R
import com.wfd.qq.dao.Login_userDao
import com.wfd.qq.dao.UserDao
import com.wfd.qq.entity.AppDatabase
import com.wfd.qq.entity.Login_user

class Login_Fragment : Fragment(){
    private lateinit var db: AppDatabase

    private lateinit var TableUser: UserDao
    private lateinit var accountEdit:EditText
    private lateinit var passwordEdit: EditText

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        db = AppDatabase.getDatabase(requireContext())
        accountEdit = view.findViewById<EditText>(R.id.account)
        passwordEdit = view.findViewById<EditText>(R.id.password)
        TableUser = db.userDao()

        // 被Toor取代
//        // 设置标题
//        val act = requireActivity() as AppCompatActivity
//        val actionBar = act.supportActionBar            // 获取ActionBar对象
//        actionBar?.title = "请登录"

        // 恢复密码
        val prefs = activity?.getSharedPreferences("login_data",Context.MODE_PRIVATE)
        val isRemember = prefs?.getBoolean("remember_password", false)
        if (isRemember == true) {

            // 将账号和密码都设置到文本框中
            val account = prefs.getString("account", "")
            val password = prefs.getString("password", "")

            accountEdit.setText(account)
            passwordEdit.setText(password)
            view.findViewById<CheckBox>(R.id.rememberPassword).isChecked = true
        }

        // 登录
        val loginButton = view.findViewById<Button>(R.id.login)
        loginButton.setOnClickListener {
            val account = accountEdit.text.toString()
            val password = passwordEdit.text.toString()
            val user = TableUser.findByAccount(account)

            if(user == null)  Toast.makeText(requireContext(), "用户不存在", Toast.LENGTH_LONG).show()
            else if(user.password != password) Toast.makeText(requireContext(), "密码不正确", Toast.LENGTH_LONG).show()
            else
            {
                val editor = prefs?.edit()
                // 检查复选框是否被选中
                if (view.findViewById<CheckBox>(R.id.rememberPassword).isChecked) editor?.putBoolean("remember_password", true)
                else editor?.putBoolean("remember_password", false)

                editor?.putString("account", account)
                editor?.putString("name", user.name)
                editor?.putString("password", password)
                editor?.putBoolean("login", true)
                editor?.apply()

                val Tablelogin_user = db.login_userDao()
                Tablelogin_user.insertLogin_user(Login_user(account=account , name = user.name, image = 1))

                // 跳转到欢迎页
                parentFragmentManager.beginTransaction()
                    .replace(R.id.login_register_container, Welcome_Fragment())
                    .commit()
            }
        }

        // 注册
        val registerButton = view.findViewById<Button>(R.id.register)
        registerButton.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.login_register_container, Register_Fragment())
                .addToBackStack(null)
                .commit()
        }
    }


}