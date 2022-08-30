package com.example.githubusersbyazim.repository

import androidx.lifecycle.LiveData
import com.example.githubusersbyazim.model.followers.Followers
import com.example.githubusersbyazim.model.userDetails.UserDetailsModel
import com.example.githubusersbyazim.model.users.Users
import com.example.githubusersbyazim.roomdb.UserEntity
import com.example.githubusersbyazim.roomdb.UsersEntity
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface Repository {
    suspend fun getDefaultUsersFromAPI() : Response<Users>

    suspend fun addDefaultUsersToDB(usersEntity: UsersEntity)

    fun getDefaultUsersFromDB() : LiveData<UsersEntity>

    suspend fun getSearchedUserFromAPI(username: String) : Response<UserDetailsModel>

    fun addSearchedUserToDB(userEntity: UserEntity)

    fun getSearchedUserFromDB(username: String) : UserEntity

    fun getSearchedUsersFromDB() : Flow<UserEntity>

    suspend fun getFollowersFromAPI(username: String) : Response<Followers>

}