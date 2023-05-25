package com.wfd.qq.entity


import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.wfd.qq.dao.UserDao


@Database(entities = [User::class], version = 1,exportSchema=false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao

    companion object {
        private var INSTANCE: AppDatabase? = null
        fun getDatabase(context: Context): AppDatabase {
            if (INSTANCE == null) {
                synchronized(this) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                        AppDatabase::class.java, "chat").allowMainThreadQueries()
                        .build()
                }
            }
            return INSTANCE!!
        }
    }
}
