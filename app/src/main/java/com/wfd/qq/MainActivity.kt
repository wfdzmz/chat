package com.wfd.qq


import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.Toolbar
//import android.widget.Toolbar
//import androidx.appcompat.widget.Toolbar
import androidx.navigation.ui.*
import com.example.qq.R
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.wfd.qq.login_register.LoginRegisterActivity
import com.wfd.qq.main_page.Contacts_Fragment
import com.wfd.qq.main_page.Message_Fragment
import com.wfd.qq.main_page.Space_Fragment
import com.wfd.qq.other_page.Search_fragment
import com.wfd.qq.other_page.Send_Space_Fragment

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration:AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        isLogin()
        
        supportFragmentManager.beginTransaction().replace(R.id.nav_host_fragment, Message_Fragment()).commit()
        val bottomNavView = findViewById<BottomNavigationView>(R.id.bottom_navigation_view)
        bottomNavView.setOnItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.bottom_nav_message -> {
                    supportFragmentManager.beginTransaction().replace(R.id.nav_host_fragment, Message_Fragment()).commit()
                    true
                }
                R.id.bottom_nav_contacts -> {
                    supportFragmentManager.beginTransaction().replace(R.id.nav_host_fragment, Contacts_Fragment()).commit()
                    true
                }
                R.id.bottom_nav_space -> {
                    supportFragmentManager.beginTransaction().replace(R.id.nav_host_fragment, Space_Fragment()).commit()
                    true
                }
                else -> false
            }
        }

        val prefs = getSharedPreferences("login_data",Context.MODE_PRIVATE)
        // 设置标题
        val name = prefs.getString("name","未登录")
        supportActionBar?.title = name
    }

    fun isLogin(){
        val prefs = getSharedPreferences("login_data",Context.MODE_PRIVATE)
        var flag = prefs.getBoolean("login", false)

        if(!flag)
        {
            finish()
            val intent = Intent(this, LoginRegisterActivity::class.java)
            startActivity(intent)
        }
    }

//    // 添加Action bar选项菜单
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    // 设置顶部菜单的响应
    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.sign_out -> {
                val builder = AlertDialog.Builder(this)

                // 设置弹出框二次确认
                builder.setTitle("是否确认退出登录？")
                // 设置按钮
                builder.setPositiveButton("确定") { dialog, which ->
                    // 退出登录
                    finish()
                    val intent = Intent(this, LoginRegisterActivity::class.java)
                    startActivity(intent)
                    val prefs = getSharedPreferences("login_data",Context.MODE_PRIVATE)

                    val editor = prefs?.edit()
                    editor?.putBoolean("login", false)
                    editor?.apply()
                }
                builder.setNegativeButton("取消") { dialog, which ->
                    dialog.cancel()
                }

                // 创建对话框并显示
                val dialog = builder.create()
                dialog.show()

            }
            R.id.add_friend -> {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.nav_host_fragment, Search_fragment())
                    .addToBackStack(null)
                    .commit()
            }
            R.id.add_space ->{
                supportFragmentManager.beginTransaction()
                    .replace(R.id.nav_host_fragment, Send_Space_Fragment())
                    .addToBackStack(null)
                    .commit()
            }
            R.id.add_group -> {
                val builder = AlertDialog.Builder(this)
                builder.setTitle("请输入添加的组名")

                // 设置布局
                val input = EditText(this)
                input.inputType = InputType.TYPE_CLASS_TEXT
                builder.setView(input)

                // 设置按钮
                builder.setPositiveButton("确定") { dialog, which ->
                    val text = input.text.toString()
                    // 处理用户输入的文本
                }

                builder.setNegativeButton("取消") { dialog, which ->
                    dialog.cancel()
                }
                // 创建对话框并显示
                val dialog = builder.create()
                dialog.show()
            }
        }
        return true
    }
}