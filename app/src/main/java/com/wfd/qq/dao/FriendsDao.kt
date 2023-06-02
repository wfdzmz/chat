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

    @Query("select * from Friends where u_id = :uid")
    fun findFriendsByUid(uid:Int):List<Friends>

    @Query("select * from Friends where u_id = :uid and friend_id =:f_id")
    fun selectfriendByUidAndFid(uid:Int,f_id:Int):Friends

    @Query("select * from Friends where u_id = :uid and group_id =:g_id")
    fun selectfriendByUidAndGroupid(uid:Int,g_id:Int):List<Friends>
}