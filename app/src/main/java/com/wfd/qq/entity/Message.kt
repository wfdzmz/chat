package com.wfd.qq.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity(tableName="message")
data class Message(
    @PrimaryKey(autoGenerate = true)
    @NonNull
    val id:Int = 1 ,
    @NonNull
    @ColumnInfo(name = "u_id1")
    var u_id1:Int,
    @NonNull
    @ColumnInfo(name="u_id2")
    var u_id2: Int ,

    @NonNull
    @ColumnInfo(name="time")
    val time: String ,
    @NonNull
    @ColumnInfo(name="content")
    val content: String,

    @NonNull
    @ColumnInfo(name="msg_type")
    var type: Int

)
{
    companion object {
        const val TYPE_RECEIVED = 0
        const val TYPE_SENT = 1
    }
}