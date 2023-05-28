package com.wfd.qq.dao


import androidx.room.*
import com.wfd.qq.entity.Space
import com.wfd.qq.entity.Friends

@Dao
interface SpaceDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSpace(space: Space)

    @Update
    fun updateSpace(space: Space)

    @Delete
    fun deleteSpace(space: Space)

//    @Query("SELECT * FROM Space INNER JOIN Friends ON Friends.u_id = Space.u_id WHERE Space.u_id = :u_id")
//    fun selectFriendSpaceByUid(u_id:Int): List<Space>

}