package com.example.githubusersbyazim.ui

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.githubusersbyazim.model.followers.Followers
import com.example.githubusersbyazim.model.userDetails.UserDetailsModel
import com.example.githubusersbyazim.model.users.Users
import com.example.githubusersbyazim.repository.Repository
import com.example.githubusersbyazim.roomdb.UserEntity
import com.example.githubusersbyazim.roomdb.UsersEntity
import com.example.githubusersbyazim.util.observeOnce
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.concurrent.Flow
import javax.inject.Inject

@HiltViewModel
class UsersViewModel @Inject constructor(private val repository: Repository): ViewModel() {

    private val _usersData: MutableState<Users> = mutableStateOf(Users())
    val usersData: State<Users> get() = _usersData

    private val _userDetailsApiData: MutableState<UserDetailsModel> = mutableStateOf(UserDetailsModel())
    val userDetailsApiData: State<UserDetailsModel> get() = _userDetailsApiData

    private val _followersApiData: MutableState<Followers> = mutableStateOf(Followers())
    val followersApiData: State<Followers> get() = _followersApiData

    private val _searchTextState: MutableState<String> =
        mutableStateOf(value = "")
    val searchTextState: State<String> = _searchTextState

    fun updateSearchTextState(newValue: String) {
        _searchTextState.value = newValue
    }

    fun getDefaultUsers() {
        viewModelScope.launch(Dispatchers.IO) {
            if (repository.getDefaultUsersFromDB().value?.usersModel?.isEmpty() == true) {
                val response = repository.getDefaultUsersFromAPI()
                Log.i("data", "data fetched from API!")
                if(response.isSuccessful) {
                    response.body()?.let {
                        _usersData.value = it
                        addDefaultUsersToDB(it)
                    }
                }
            } else {
                Log.i("data", "data fetched from Database!")
                _usersData.value = repository.getDefaultUsersFromDB().value?.usersModel!!
            }
        }
    }

    fun getSearchedUser(username: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = repository.getSearchedUserFromAPI(username)
            if(response.isSuccessful) {
                response.body()?.let {
                    _userDetailsApiData.value = it
                }
            }
        }
    }

    fun getFollowers(username: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = repository.getFollowersFromAPI(username)
            response.body()?.let {
                _followersApiData.value = it
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