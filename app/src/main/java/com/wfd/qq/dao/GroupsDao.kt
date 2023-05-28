package com.wfd.qq.dao

import androidx.room.*
import com.wfd.qq.entity.Groups

interface GroupsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertGroup(group: Groups)

    @Update
    fun updateGroup(group: Groups)

    @Delete
    fun deleteGroup(group: Groups)

    @Query("select * from Groups")
    fun selectAllGroup():List<Groups>
}