package com.wfd.qq.page
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import com.example.qq.R

class Space_Fragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_space, container, false)
        setHasOptionsMenu(true);  //保证能在Fragment里面调用onCreateOptionsMenu()方法
        return view
    }
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        menu.clear()
        inflater.inflate(R.menu.add_facebook, menu)
    }
}