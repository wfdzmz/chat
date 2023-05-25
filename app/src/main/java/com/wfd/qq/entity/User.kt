package com.wfd.qq.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity(tableName="users")
data class User(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "account")
    val account:String ,

    @NonNull
    @ColumnInfo(name="name")
    val name: String,

    @NonNull
    @ColumnInfo(name="password")
    val password: String
)