package com.wfd.qq.login_register

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
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

        val button = view.findViewById<Button>(R.id.home)
        button.setOnClickListener() {
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