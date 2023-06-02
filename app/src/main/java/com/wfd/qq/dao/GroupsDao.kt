package com.wfd.qq.dao

import androidx.room.*
import com.wfd.qq.entity.Groups

@Dao
interface GroupsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertGroup(group: Groups)

    @Update
    fun updateGroup(group: Groups)

    @Delete
    fun deleteGroup(group: Groups)

    @Query("select * from Groups where u_id = :uid")
    fun selectAllGroupByUid(uid:Int):List<Groups>

    @Query("select * from Groups where u_id = :uid and group_name = :group")
    fun selectAllGroupByUidandGroup(uid:Int , group:String):Groups


}