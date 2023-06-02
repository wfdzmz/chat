package com.wfd.qq.login_register

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import androidx.appcompat.app.AppCompatActivity
import com.example.qq.R
import com.wfd.qq.MainActivity

class Welcome_Fragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_welcome, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val act = requireActivity() as AppCompatActivity
        val actionBar = act.supportActionBar            // 获取ActionBar对象
        actionBar?.title = "欢迎使用本软件"

        val prefs = activity?.getSharedPreferences("login_data",Context.MODE_PRIVATE)
        val isRemember = prefs?.getBoolean("noDisplayWelcome", false)
        if (isRemember == true) {
            activity?.finish()
            val intent = Intent(requireContext(), MainActivity::class.java)
            startActivity(intent)
        }

        val button = view.findViewById<Button>(R.id.home)
        button.setOnClickListener() {

            val editor = prefs?.edit()
            // 检查复选框是否被选中
            if (view.findViewById<CheckBox>(R.id.nodisply).isChecked) editor?.putBoolean("noDisplayWelcome", true)
            else editor?.putBoolean("noDisplayWelcome", false)
            editor?.apply()

            activity?.finish()
            val intent = Intent(requireContext(), MainActivity::class.java)
            startActivity(intent)
        }
    }
    override fun onDestroy() {
        super.onDestroy()
        activity?.finish()
    }

}