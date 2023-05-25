package com.wfd.qq


import android.content.Context
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.onNavDestinationSelected
import androidx.navigation.ui.setupWithNavController
import com.example.qq.R
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.wfd.qq.activity.LoginRegisterActivity
import com.wfd.qq.login_register.Login_Fragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        isLogin() ;
        val prefs = getSharedPreferences("login_data",Context.MODE_PRIVATE)
        // 设置标题
        val name = prefs.getString("name","未登录")
        supportActionBar?.title = name

//        退出登录
//        val un_login = findViewById<Button>(R.id.un_login)
//        un_login.setOnClickListener(){
//            finish()
//            val intent = Intent(this,LoginRegisterActivity::class.java)
//            startActivity(intent)
//        }

        // 获取NavHost对象
//        val navHostFragment = supportFragmentManager.
//        findFragmentById(androidx.navigation.ui.R.id.nav_host_fragment) as NavHostFragment
//        // 获取navController对象
//        val navController = navHostFragment.navController
//
//        //创建顶层destinations，并将它们添加到底部导航栏
//        val appBarConfiguration = AppBarConfiguration(setOf(
//            androidx.navigation.ui.R.id.nav_home, androidx.navigation.ui.R.id.nav_tickets, androidx.navigation.ui.R.id.nav_offers, androidx.navigation.ui.R.id.nav_rewards))
//        setupActionBarWithNavController(navController, appBarConfiguration)
//        findViewById<BottomNavigationView>(androidx.navigation.ui.R.id.nav_view)
//            ?.setupWithNavController(navController)

    }

    fun isLogin(){

    }
    override fun onDestroy() {
        super.onDestroy()
        val prefs = getSharedPreferences("login_data",Context.MODE_PRIVATE)
        var flag = prefs.getBoolean("login", false)

        if(!flag)
        {
            finish()
            val intent = Intent(this, LoginRegisterActivity::class.java)
            startActivity(intent)
        }
    }
    // 设置Action bar上的导航按钮响应
//    override fun onSupportNavigateUp(): Boolean {
//        val navController = findNavController(androidx.navigation.ui.R.id.nav_host_fragment)
//        return navController.navigateUp(appBarConfiguration)
//                || super.onSupportNavigateUp()
//    }
//
//    // 添加Action bar选项菜单
//    override fun onCreateOptionsMenu(menu: Menu): Boolean {
//        menuInflater.inflate(androidx.navigation.ui.R.menu.main, menu)
//        return true
//    }
//
//    // 设置Action bar选项菜单项的选中事件处理
//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        super.onOptionsItemSelected(item)
//        return item.onNavDestinationSelected(
//            findNavController(
//                androidx.navigation.ui.R.id.nav_host_fragment
//            )
//        )
//    }
}