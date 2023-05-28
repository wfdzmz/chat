package com.wfd.qq.dao


import androidx.room.*
import com.wfd.qq.entity.Message
import com.wfd.qq.entity.User

@Dao
interface MessageDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMessage(message: Message)

    @Update
    fun updateMessage(message: Message)

    @Delete
    fun deleteMessage(message: Message)

    @Query("select * from Message")
    fun selectAllMessage():List<Message>

    @Query("select * from Message where u_id1 =:uid and U_id2 = :f_id")
    fun selectMessageByUidAndFid(uid:Int,f_id:Int):List<Message>

}