package com.wfd.qq.entity


import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.wfd.qq.dao.*


@Database(entities = [User::class,Friends::class,Groups::class,Message::class,Space::class], version = 15,exportSchema=false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao
    abstract fun friendsDao(): FriendsDao
    abstract fun message(): MessageDao
    abstract fun spaceDao(): SpaceDao
    abstract fun groupsDao(): GroupsDao

    companion object {
        private var INSTANCE: AppDatabase? = null
        fun getDatabase(context: Context): AppDatabase {
            if (INSTANCE == null) {
                synchronized(this) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                        AppDatabase::class.java, "chat").allowMainThreadQueries()
                        .fallbackToDestructiveMigration()
                        .build()
                }
            }
            return INSTANCE!!
        }
    }
}
