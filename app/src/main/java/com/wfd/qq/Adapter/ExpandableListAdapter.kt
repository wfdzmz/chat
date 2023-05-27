package com.wfd.qq.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseExpandableListAdapter
import android.widget.TextView
import com.example.qq.R

class ExpandableListAdapter(private val context: Context,
                            private val groupList: List<String>,
                            private val childList: List<List<String>>): BaseExpandableListAdapter() {

    override fun getGroup(groupPosition: Int): Any {
        return groupList[groupPosition]
    }

    override fun isChildSelectable(groupPosition: Int, childPosition: Int): Boolean {
        return true
    }

    override fun hasStableIds(): Boolean {
        return false
    }

    override fun getGroupView(groupPosition: Int, isExpanded: Boolean, convertView: View?, parent: ViewGroup?): View {
        var view = convertView
        if (view == null) {
            val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            view = inflater.inflate(R.layout.list_group_of_friend_item, null)
        }
        val titleTextView = view!!.findViewById<TextView>(R.id.group_title_text_view)
        titleTextView.text = "   " + groupList[groupPosition]
        return view!!
    }

    override fun getChildrenCount(groupPosition: Int): Int {
        return childList[groupPosition].size
    }

    override fun getChild(groupPosition: Int, childPosition: Int): Any {
        return childList[groupPosition][childPosition]
    }

    override fun getGroupId(groupPosition: Int): Long {
        return groupPosition.toLong()
    }

    override fun getChildView(groupPosition: Int, childPosition: Int, isLastChild: Boolean, convertView: View?, parent:
    ViewGroup?): View {
        var view = convertView
        if (view == null) {
            val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            view = inflater.inflate(R.layout.list_child_of_friend_item, null)
        }
        val titleTextView = view!!.findViewById<TextView>(R.id.child_title_text_view)
        titleTextView.text = "  " + childList[groupPosition][childPosition]
        return view!!
    }

    override fun getChildId(groupPosition: Int, childPosition: Int): Long {
        return childPosition.toLong()
    }

    override fun getGroupCount(): Int {
        return groupList.size
    }
}