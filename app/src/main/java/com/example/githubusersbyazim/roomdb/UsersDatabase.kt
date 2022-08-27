package com.example.githubusersbyazim.roomdb

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(
    entities = [UsersEntity::class, UserEntity::class],
    version = 1,
    exportSchema = false
)

@TypeConverters(UserTypeConverter::class)
abstract class UsersDatabase : RoomDatabase() {
    abstract fun usersDAO() : UsersDAO
}