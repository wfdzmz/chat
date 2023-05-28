package com.wfd.qq.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName="Groups")
data class Groups(
    @PrimaryKey(autoGenerate = true)
    @NonNull
    val id:Int = 1 ,

    @NonNull
    @ColumnInfo(name="friend_id")
    var u_id: Int ,

    @NonNull
    @ColumnInfo(name="group")
    val group: String
)
