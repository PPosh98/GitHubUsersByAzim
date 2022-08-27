package com.example.githubusersbyazim.repository

import com.example.githubusersbyazim.api.FetchAPI
import com.example.githubusersbyazim.model.UserDetailsModel
import com.example.githubusersbyazim.model.Users
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

    override fun getDefaultUsersFromDB(): Flow<UsersEntity> =
        usersDAO.readDefaultUsersFromDb()

    override suspend fun getSearchedUserFromAPI(username: String): Response<UserDetailsModel> =
        fetchAPI.getUser(username)

    override fun addSearchedUserToDB(userEntity: UserEntity) =
        usersDAO.insertSearchedUserToDb(userEntity)

    override fun getSearchedUserFromDB(username: String): UserEntity =
        usersDAO.readSearchedUserFromDb(username)

    override fun getSearchedUsersFromDB(): Flow<UserEntity> =
        usersDAO.readSearchedUsersFromDb()

}