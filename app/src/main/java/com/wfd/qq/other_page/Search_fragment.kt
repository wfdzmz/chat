package com.wfd.qq.other_page

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.qq.R

class Search_fragment : Fragment() {

    private lateinit var accountEdit: EditText

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_search, container, false)


        val search_Button = view.findViewById<Button>(R.id.search_button)
        search_Button.setOnClickListener {
            accountEdit = view.findViewById<EditText>(R.id.search_account)
            val account = accountEdit.text.toString()
        }


        return view
    }
}