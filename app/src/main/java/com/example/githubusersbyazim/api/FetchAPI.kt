package com.example.githubusersbyazim.api

import com.example.githubusersbyazim.model.UserDetailsModel
import com.example.githubusersbyazim.model.Users
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface FetchAPI {
    @GET("users")
    suspend fun getUsers() : Response<Users>

    @GET("users/{username}")
    suspend fun getUser(@Path("username") username: String) : Response<UserDetailsModel>
}