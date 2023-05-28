package com.wfd.qq.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity(tableName="Friends",primaryKeys = ["u_id", "friend_id"])
data class Friends(

    @NonNull
    @ColumnInfo(name="u_id")
    val u_id:Int ,

    @NonNull
    @ColumnInfo(name="friend_id")
    var friend_id: Int ,

    @NonNull
    @ColumnInfo(name="group")
    val group_id:Int
)