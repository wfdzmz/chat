package com.wfd.qq.method

import com.wfd.qq.entity.Space_item

class Space_time_Comparator (private val a: Space_item, private val b:Space_item): Comparator<Space_item> {
    override fun compare(p1: Space_item, p2: Space_item): Int {
        val t1 = p1.time
        val t2 = p2.time
        val n = t1.length
        for(i in 1 until n)
        {
            if(t1[i] < t2[i]) return 1
        }
        return 0
    }
}