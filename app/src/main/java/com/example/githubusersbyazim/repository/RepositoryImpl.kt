package com.example.githubusersbyazim.repository

import androidx.lifecycle.LiveData
import com.example.githubusersbyazim.api.FetchAPI
import com.example.githubusersbyazim.model.followers.Followers
import com.example.githubusersbyazim.model.userDetails.UserDetailsModel
import com.example.githubusersbyazim.model.users.Users
import com.example.githubusersbyazim.roomdb.UserEntity
import com.example.githubusersbyazim.roomdb.UsersDAO
import com.example.githubusersbyazim.roomdb.UsersEntity
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import javax.inject.Inject

class RepositoryImpl @Inject constructor(private val fetchAPI: FetchAPI, private val usersDAO: UsersDAO) :
    Repository {
    override suspend fun getDefaultUsersFromAPI(): Response<Users> =
        fetchAPI.getUsers()

    override suspend fun addDefaultUsersToDB(usersEntity: UsersEntity) =
        usersDAO.insertDefaultUsersToDb(usersEntity)

    override fun getDefaultUsersFromDB(): LiveData<UsersEntity> =
        usersDAO.readDefaultUsersFromDb()

    override suspend fun getSearchedUserFromAPI(username: String): Response<UserDetailsModel> =
        fetchAPI.getUser(username)

    override fun addSearchedUserToDB(userEntity: UserEntity) =
        usersDAO.insertSearchedUserToDb(userEntity)

    override fun getSearchedUserFromDB(username: String): UserEntity =
        usersDAO.readSearchedUserFromDb(username)

    override fun getSearchedUsersFromDB(): Flow<UserEntity> =
        usersDAO.readSearchedUsersFromDb()

    override suspend fun getFollowersFromAPI(username: String): Response<Followers> =
        fetchAPI.getFollowers(username)
}