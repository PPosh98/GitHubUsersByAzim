package com.example.githubusersbyazim.di

import android.content.Context
import androidx.room.Room
import com.example.githubusersbyazim.roomdb.UsersDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Provides
    fun provideRoomInstance(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(
        context,
        UsersDatabase::class.java,
        "UsersDatabase"
    ).build()

    @Provides
    fun provideUsersDao(database: UsersDatabase) = database.usersDAO()
}