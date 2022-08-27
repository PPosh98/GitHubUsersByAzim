package com.example.githubusersbyazim.util

import com.example.abbreviationsappbyazim.models.Abbreviations

sealed class UiState {
    object Loading: UiState()
    data class Success(val abbrevResponse: Abbreviations): UiState()
    data class Error(val error: Throwable): UiState()
}
