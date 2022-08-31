package com.example.githubusersbyazim.ui

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.githubusersbyazim.ui.screens.user_details.UserDetailsScreen
import com.example.githubusersbyazim.ui.screens.users_list.UsersListScreen
import com.example.githubusersbyazim.ui.theme.GitHubUsersByAzimTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GitHubUsersByAzimTheme {
                Navigation()
            }
        }
    }
}

@Composable
fun Navigation(viewModel: UsersViewModel = hiltViewModel()) {

    val navController = rememberNavController()

    val arguments = listOf(
        navArgument("username") { type = NavType.StringType }
    )

    NavHost(navController = navController, startDestination = "usersList") {
        composable("usersList") {
            UsersListScreen(
                onNavigateToDetails = { username ->
                    viewModel.getSearchedUser(username)
                    viewModel.getFollowers(username)
                    navController.navigate("userDetails/$username") },
                onSearchClicked = { username -> navController.navigate("userDetails/$username")}
            ) }
        composable("usersDetails") {
            UserDetailsScreen(
                onNavigateToFollower = { username -> navController.navigate("userDetails/$username") },
                onNavigateBack = {}
            ) }
        composable(
            route = "userDetails/{username}",
            arguments = arguments
        ) { navBackStackEntry ->
            val username =
                navBackStackEntry.arguments?.getString("username")
            if (username != null) {
                UserDetailsScreen(
                    username = username,
                    onNavigateToFollower = {navController.navigate("userDetails/$username")},
                    onNavigateBack = {navController.navigate("usersList")}
                )
            }
        }
    }
}