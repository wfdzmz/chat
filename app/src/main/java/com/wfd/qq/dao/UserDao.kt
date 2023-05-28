package com.wfd.qq.dao


import androidx.room.*
import com.wfd.qq.entity.User

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUser(user: User)

    @Update
    fun updateUser(user: User)

    @Delete
    fun deleteUser(user: User)

    @Query("select * from users")
    fun loadAllUsers(): List<User>

    @Query("SELECT * FROM users WHERE name =:name")
    fun findByName(name: String):User

    @Query("SELECT * FROM users WHERE account =:account")
    fun findByAccount(account: String): User

//    @Query("select * from users where user_id = :id")
//    fun findByUser(id:String)
}