package com.example.githubusersbyazim.roomdb

import androidx.room.TypeConverter
import com.example.githubusersbyazim.model.userDetails.UserDetailsModel
import com.example.githubusersbyazim.model.users.Users
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class UserTypeConverter {

    var gson = Gson()

    @TypeConverter
    fun usersToString(users: Users): String = gson.toJson(users)

    @TypeConverter
    fun stringToUsers(data: String): Users {
        val listType = object : TypeToken<Users>() {}.type
        return gson.fromJson(data, listType)
    }

    @TypeConverter
    fun userToString(user: UserDetailsModel): String = gson.toJson(user)

    @TypeConverter
    fun stringToUser(data: String): UserDetailsModel {
        val listType = object : TypeToken<UserDetailsModel>() {}.type
        return gson.fromJson(data, listType)
    }
}