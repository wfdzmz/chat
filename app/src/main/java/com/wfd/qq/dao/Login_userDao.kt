package com.wfd.qq.dao


import androidx.room.*
import com.wfd.qq.entity.Login_user
import com.wfd.qq.entity.User

@Dao
interface Login_userDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertLogin_user(user: Login_user)

    @Update
    fun updateLogin_user(user: Login_user)

    @Delete
    fun deleteLogin_user(user: Login_user)

    @Query("delete from Login_user where account = :account")
    fun deleteLogin_userByaccount(account: String)

    @Query("select * from Login_user")
    fun loadAllUsers(): List<Login_user>

    @Query("SELECT * FROM Login_user WHERE id = :id")
    fun findById(id: Int):Login_user

    @Query("SELECT * FROM Login_user WHERE account =:account")
    fun findByAccount(account: String): Login_user

//    @Query("select * from users where user_id = :id")
//    fun findByUser(id:String)
}