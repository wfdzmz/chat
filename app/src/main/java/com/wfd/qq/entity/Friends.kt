package com.wfd.qq.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity(tableName="Friends")
data class Friends(
    @PrimaryKey(autoGenerate = true)
    @NonNull
    val id:Int = 1 ,

    @NonNull
    @ColumnInfo(name="friend_id")
    var friend_id: Int ,

    @NonNull
    @ColumnInfo(name="group")
    val group_id:Int
)