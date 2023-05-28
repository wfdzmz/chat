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

class Send_Space_Fragment : Fragment() {

    private lateinit var edit: EditText

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_add_space, container, false)


        val space_sent_button = view.findViewById<Button>(R.id.space_sent_button)
        space_sent_button.setOnClickListener {
            edit = view.findViewById<EditText>(R.id.space_content)
            val content = edit.text.toString()
            Toast.makeText(requireContext(),content,Toast.LENGTH_SHORT)
        }


        return view
    }
}