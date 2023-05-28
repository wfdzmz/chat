package com.wfd.qq.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity(tableName="space")
data class Space(

    @PrimaryKey(autoGenerate = true)
    @NonNull
    val id:Int = 1 ,

    @NonNull
    @ColumnInfo(name = "u_id")
    val u_id:Int,

    @NonNull
    @ColumnInfo(name="time")
    val time: String,

    @ColumnInfo(name="content")
    val password: String,

    @ColumnInfo(name="image")
    val image: Int
)