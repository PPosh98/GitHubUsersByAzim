package com.example.githubusersbyazim.roomdb

import androidx.room.Entity
import com.example.githubusersbyazim.model.Users

@Entity(tableName = "users")
class UsersEntity(val usersModel: Users)