package com.example.githubusersbyazim.roomdb

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.githubusersbyazim.model.userDetails.UserDetailsModel

@Entity(tableName = "searchedUsers")
class UserEntity(val userModel: UserDetailsModel) {
    @PrimaryKey(autoGenerate = true)
    var generatedId: Int = 0
    var username: String? = userModel.login
}