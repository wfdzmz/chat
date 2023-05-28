package com.wfd.qq.dao

import androidx.room.*
import com.wfd.qq.entity.Friends
import com.wfd.qq.entity.Message

@Dao
interface FriendsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFriends(friend: Friends)

    @Update
    fun updateFriends(friend: Friends)

    @Delete
    fun deleteFriends(friend: Friends)

    @Query("select * from Friends")
    fun selectAllFriends():List<Friends>

    @Query("select * from Friends where u_id = :uid and friend_id =:f_id")
    fun selectfriendByUidAndFid(uid:Int,f_id:Int):List<Friends>
}