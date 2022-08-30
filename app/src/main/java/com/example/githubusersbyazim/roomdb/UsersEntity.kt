package com.example.githubusersbyazim.roomdb

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.githubusersbyazim.model.users.Users

@Entity(tableName = "users")
class UsersEntity(val usersModel: Users){
    @PrimaryKey(autoGenerate = false)
    var generatedId: Int = 0
}