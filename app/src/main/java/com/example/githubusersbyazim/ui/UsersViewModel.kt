package com.example.githubusersbyazim.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.githubusersbyazim.model.UserDetailsModel
import com.example.githubusersbyazim.model.Users
import com.example.githubusersbyazim.repository.Repository
import com.example.githubusersbyazim.roomdb.UserEntity
import com.example.githubusersbyazim.roomdb.UsersEntity
import com.example.githubusersbyazim.util.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UsersViewModel @Inject constructor(private val repository: Repository): ViewModel() {

    private var _usersLiveData: MutableLiveData<UiState> = MutableLiveData(UiState.Loading)
    val usersLiveData: LiveData<UiState> get() = _usersLiveData

    val readDefaultUsers: LiveData<UsersEntity> = repository.getDefaultUsersFromDB().asLiveData()

    fun getDefaultUsers() {

        CoroutineScope(Dispatchers.IO).launch {
            val response = repository.getDefaultUsersFromAPI()
            if(response.isSuccessful){
                _usersLiveData.postValue(
                    response.body()?.let {
                        if (response.body()!!.size > 0) addDefaultUsersToDB(it)
                        UiState.Success(
                            response.body() as Users
                        )
                    }
                )
            } else {
                _usersLiveData.postValue(
                    UiState.Error(
                        Throwable(
                            response.message()
                        )
                    )
                )
            }
        }
    }

    fun getSearchedUser(username: String) {

        CoroutineScope(Dispatchers.IO).launch {
            val response = repository.getSearchedUserFromAPI(username)
            if(response.isSuccessful){
                _usersLiveData.postValue(
                    response.body()?.let {
                        if (response.body() != null) addSearchedUserToDB(it)
                        UiState.Success(
                            response.body() as Users
                        )
                    }
                )
            } else {
                _usersLiveData.postValue(
                    UiState.Error(
                        Throwable(
                            response.message()
                        )
                    )
                )
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