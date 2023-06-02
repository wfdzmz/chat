package com.wfd.qq.entity


import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName="Login_user")
data class Login_user(
    @PrimaryKey(autoGenerate = true)
    @NonNull
    val id:Int = 0 ,

    @NonNull
    @ColumnInfo(name = "account")
    val account:String ,

    @NonNull
    @ColumnInfo(name= "name")
    val name: String,

    @ColumnInfo(name="image")
    val image: Int
)
