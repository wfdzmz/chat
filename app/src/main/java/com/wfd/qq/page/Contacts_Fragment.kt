package com.wfd.qq.page
import android.os.Bundle
import android.view.*
import android.widget.ExpandableListView
import androidx.fragment.app.Fragment
import com.example.qq.R
import com.wfd.qq.Adapter.ExpandableListAdapter

class Contacts_Fragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_contacts, container, false)
        setHasOptionsMenu(true);  //保证能在Fragment里面调用onCreateOptionsMenu()方法

        val expandableListView = view.findViewById<ExpandableListView>(R.id.expandable_list_view)
        val groupList = mutableListOf<String>("新朋友", "家人", "同事")
        val childList = mutableListOf<List<String>>(
            listOf("李四", "王五"),
            listOf("姐姐", "哥哥"),
            listOf("老板", "经理")
        )
        val adapter = ExpandableListAdapter(requireContext(), groupList, childList)
        expandableListView.setAdapter(adapter)

        return view
    }
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        menu.clear()
        inflater.inflate(R.menu.add_friend, menu)
    }
}