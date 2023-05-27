package com.wfd.qq.login_register

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.qq.R
import com.wfd.qq.login_register.Login_Fragment

class LoginRegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_loginregister)

        // 登录界面
        supportFragmentManager.beginTransaction().replace(R.id.login_register_container, Login_Fragment()).commit()
    }

}