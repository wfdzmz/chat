package com.wfd.qq


import android.content.Context
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.*
import androidx.navigation.ui.NavigationUI.setupActionBarWithNavController
import com.example.qq.R
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.wfd.qq.activity.LoginRegisterActivity
import com.wfd.qq.login_register.Login_Fragment
import com.wfd.qq.page.Contacts_Fragment
import com.wfd.qq.page.Message
import com.wfd.qq.page.Space_Fragment

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration:AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val bottomNavView = findViewById<BottomNavigationView>(R.id.bottom_navigation_view)
        bottomNavView.setOnItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.bottom_nav_message -> {

                    supportFragmentManager.beginTransaction().replace(R.id.nav_host_fragment, Message()).commit()
//                    Toast.makeText(this,"1",Toast.LENGTH_SHORT).show()
                     // 处理 Home 选项卡的点击事件
                    true
                }

                R.id.bottom_nav_contacts -> {

                    supportFragmentManager.beginTransaction().replace(R.id.nav_host_fragment, Contacts_Fragment()).commit()
                    Toast.makeText(this,"2",Toast.LENGTH_SHORT).show()
                    // 处理 Dashboard 选项卡的点击事件
                    true
                }
                R.id.bottom_nav_space -> {
                    supportFragmentManager.beginTransaction().replace(R.id.nav_host_fragment, Space_Fragment()).commit()
                    Toast.makeText(this,"3",Toast.LENGTH_SHORT).show()
                    // 处理 Notifications 选项卡的点击事件
                    true
                }
                else -> false
            }
        }

//        isLogin() ;
//        val prefs = getSharedPreferences("login_data",Context.MODE_PRIVATE)
//        // 设置标题
//        val name = prefs.getString("name","未登录")
//        supportActionBar?.title = name


//        // 获取NavHost对象
//        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
//        // 获取navController对象
//        val navController = navHostFragment.navController

        //创建顶层destinations，并将它们添加到底部导航栏
//        appBarConfiguration = AppBarConfiguration(setOf(
//            R.id.nav_home,
//            R.id.nav_space
//            ))

//        setupActionBarWithNavController(navController, appBarConfiguration)
//        findViewById<BottomNavigationView>(R.id.nav_view).setupWithNavController(navController)
    }

    fun isLogin(){
        val prefs = getSharedPreferences("login_data",Context.MODE_PRIVATE)
        var flag = prefs.getBoolean("login", false)

        if(!flag)
        {
            finish()
            val intent = Intent(this,LoginRegisterActivity::class.java)
            startActivity(intent)
        }
    }

    // 设置Action bar上的导航按钮响应
    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.container2)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    // 添加Action bar选项菜单
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true
    }
    // 设置顶部菜单的响应
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.sign_out -> {
                finish()
                val intent = Intent(this, LoginRegisterActivity::class.java)
                startActivity(intent)
            }
        }
        return true
    }
}