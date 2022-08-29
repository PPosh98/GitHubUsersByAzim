package com.example.githubusersbyazim.ui

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.githubusersbyazim.model.followers.Followers
import com.example.githubusersbyazim.model.userDetails.UserDetailsModel
import com.example.githubusersbyazim.model.users.Users
import com.example.githubusersbyazim.repository.Repository
import com.example.githubusersbyazim.roomdb.UserEntity
import com.example.githubusersbyazim.roomdb.UsersEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.concurrent.Flow
import javax.inject.Inject

@HiltViewModel
class UsersViewModel @Inject constructor(private val repository: Repository): ViewModel() {

    val usersApiData: MutableState<Users> = mutableStateOf(Users())
    val userDetailsApiData: MutableState<UserDetailsModel> = mutableStateOf(UserDetailsModel())
    val followersApiData: MutableState<Followers> = mutableStateOf(Followers())

    private val _searchTextState: MutableState<String> =
        mutableStateOf(value = "")
    val searchTextState: State<String> = _searchTextState

    fun updateSearchTextState(newValue: String) {
        _searchTextState.value = newValue
    }

    val readDefaultUsers = repository.getDefaultUsersFromDB()

    fun getDefaultUsers() {
        viewModelScope.launch(Dispatchers.IO) {
            val response = repository.getDefaultUsersFromAPI()
            if(response.isSuccessful) {
                response.body()?.let {
                    usersApiData.value = it
                    addDefaultUsersToDB(it)
                }
            }
        }
    }

    fun getSearchedUser(username: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = repository.getSearchedUserFromAPI(username)
            if(response.isSuccessful) {
                response.body()?.let {
                    userDetailsApiData.value = it
                }
            }
        }
    }

    fun getFollowers(username: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = repository.getFollowersFromAPI(username)
            response.body()?.let {
                followersApiData.value = it
            }
        }
    }

    private fun addDefaultUsersToDB(users: Users) {
        CoroutineScope(Dispatchers.IO).launch {
            repository.addDefaultUsersToDB(UsersEntity(users))
        }
    }

    private fun addSearchedUserToDB(user: UserDetailsModel) {
        CoroutineScope(Dispatchers.IO).launch {
            repository.addSearchedUserToDB(UserEntity(user))
        }
    }
}