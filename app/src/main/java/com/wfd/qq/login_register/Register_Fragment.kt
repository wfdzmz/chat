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
import com.example.qq.R
import com.wfd.qq.dao.UserDao
import com.wfd.qq.entity.AppDatabase
import com.wfd.qq.entity.Groups
import com.wfd.qq.entity.User

class Register_Fragment : Fragment(){

    private lateinit var db: AppDatabase
    private lateinit var TableUser: UserDao
    private lateinit var accountEdit:EditText
    private lateinit var nameEdit: EditText
    private lateinit var passwordEdit1: EditText
    private lateinit var passwordEdit2: EditText


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_register, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        db = AppDatabase.getDatabase(requireContext())

        accountEdit = view.findViewById<EditText>(R.id.account)
        nameEdit = view.findViewById<EditText>(R.id.name)
        passwordEdit1 = view.findViewById<EditText>(R.id.password1)
        passwordEdit2 = view.findViewById<EditText>(R.id.password2)
        TableUser = db.userDao() ;

        val act = requireActivity() as AppCompatActivity
        val actionBar = act.supportActionBar            // 获取ActionBar对象
        actionBar?.title = "请注册"
        // 注册
        val registerButton = view.findViewById<Button>(R.id.register)
        registerButton.setOnClickListener {

            val account = accountEdit.text.toString()
            val name = nameEdit.text.toString()
            val password1 = passwordEdit1.text.toString()
            val password2 = passwordEdit2.text.toString()

            if(account == "") Toast.makeText(requireContext(), "账号为空", Toast.LENGTH_SHORT).show()
            else if(name == "") Toast.makeText(requireContext(), "用户名为空", Toast.LENGTH_SHORT).show()
            else if(password1 == "") Toast.makeText(requireContext(), "密码为空", Toast.LENGTH_SHORT).show()
            else if(password1 != password2) Toast.makeText(requireContext(), "密码不一致", Toast.LENGTH_SHORT).show()
            else
            {
                var user = TableUser.findByAccount(account);
                if (user != null) Toast.makeText(requireContext(), "该用户已存在", Toast.LENGTH_LONG).show()
                else {
                    TableUser.insertUser(User(account=account, name=name, password = password1 , image = 1));
                    user = TableUser.findByAccount(account);
                    db.groupsDao().insertGroup(Groups(u_id = user?.id ?: 0 , group = "好友"))
                    Toast.makeText(requireContext(), "注册成功", Toast.LENGTH_LONG).show()

                    parentFragmentManager.beginTransaction()
                        .replace(R.id.login_register_container, Login_Fragment())
                        .commit()
                }
            }
        }
    }

}