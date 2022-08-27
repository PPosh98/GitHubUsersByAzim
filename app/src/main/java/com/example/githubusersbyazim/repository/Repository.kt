package com.example.githubusersbyazim.repository

import com.example.githubusersbyazim.model.UserDetailsModel
import com.example.githubusersbyazim.model.Users
import com.example.githubusersbyazim.roomdb.UserEntity
import com.example.githubusersbyazim.roomdb.UsersEntity
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface Repository {
    suspend fun getDefaultUsersFromAPI() : Response<Users>

    suspend fun addDefaultUsersToDB(usersEntity: UsersEntity)

    fun getDefaultUsersFromDB() : Flow<UsersEntity>

    suspend fun getSearchedUserFromAPI(username: String) : Response<UserDetailsModel>

    fun addSearchedUserToDB(userEntity: UserEntity)

    fun getSearchedUserFromDB(username: String) : UserEntity

    fun getSearchedUsersFromDB() : Flow<UserEntity>

}