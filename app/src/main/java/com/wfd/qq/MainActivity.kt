package com.wfd.qq


import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavOptions
//import android.widget.Toolbar
//import androidx.appcompat.widget.Toolbar
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.*
import com.example.qq.R
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.wfd.qq.activity.LoginRegisterActivity
import com.wfd.qq.page.Contacts_Fragment
import com.wfd.qq.page.Message_Fragment
import com.wfd.qq.page.Space_Fragment

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

//        isLogin() ;
//        val prefs = getSharedPreferences("login_data",Context.MODE_PRIVATE)
//        // 设置标题
//        val name = prefs.getString("name","未登录")
//        supportActionBar?.title = name


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

//    // 添加Action bar选项菜单
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
                val prefs = getSharedPreferences("login_data",Context.MODE_PRIVATE)

                val editor = prefs?.edit()
                editor?.putBoolean("login", false)
                editor?.apply()
            }

        }
        return true
    }
}