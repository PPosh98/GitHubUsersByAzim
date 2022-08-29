package com.example.githubusersbyazim.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.githubusersbyazim.ui.screens.user_details.UserDetailsScreen
import com.example.githubusersbyazim.ui.screens.users_list.UsersListScreen
import com.example.githubusersbyazim.ui.theme.GitHubUsersByAzimTheme
import dagger.hilt.android.AndroidEntryPoint

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
fun Navigation() {
    val navController = rememberNavController()

    val arguments = listOf(
        navArgument("username") { type = NavType.StringType }
    )

    NavHost(navController = navController, startDestination = "usersList") {
        composable("usersList") {
            UsersListScreen(
                onNavigateToDetails = { username -> navController.navigate("userDetails/$username")},
                onSearchClicked = { username -> navController.navigate("userDetails/$username")}
            ) }
        composable("usersDetails") {
            UserDetailsScreen(
                onNavigateToFollower = { username -> navController.navigate("userDetails/$username") }
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
                    onNavigateToFollower = {}
                )
            }
        }

    }
}