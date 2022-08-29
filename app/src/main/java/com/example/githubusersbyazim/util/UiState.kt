package com.example.githubusersbyazim.util

import com.example.githubusersbyazim.model.users.Users

sealed class UiState {
    object Loading: UiState()
    data class Success(val usersResponse: Users): UiState()
    data class Error(val error: Throwable): UiState()
}
