package com.example.githubusersbyazim.roomdb

import androidx.compose.runtime.State
import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface UsersDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertDefaultUsersToDb(usersEntity: UsersEntity)

    @Query("SELECT * FROM users")
    fun readDefaultUsersFromDb() : Flow<UsersEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSearchedUserToDb(userEntity: UserEntity)

    @Query("SELECT * FROM searchedUsers WHERE username LIKE :username")
    fun readSearchedUserFromDb(username: String) : UserEntity

    @Query("SELECT * FROM searchedUsers")
    fun readSearchedUsersFromDb() : Flow<UserEntity>
}