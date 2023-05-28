package com.wfd.qq.dao

import androidx.room.*
import com.wfd.qq.entity.Friends
import com.wfd.qq.entity.Message

interface FriendsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFriends(friend: Friends)

    @Update
    fun updateFriends(friend: Friends)

    @Delete
    fun deleteFriends(friend: Friends)

    @Query("select * from Friends")
    fun selectAllFriends():List<Friends>

    @Query("select * from Message where u_id1 =:uid and U_id2 = f_id")
    fun selectMessageByUidAndFid(uid:Int,f_id:Int):List<Message>
}