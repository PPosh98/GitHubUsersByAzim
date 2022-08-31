package com.example.githubusersbyazim.ui.screens.user_details

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.githubusersbyazim.model.followers.Followers
import com.example.githubusersbyazim.model.userDetails.UserDetailsModel
import com.example.githubusersbyazim.ui.UsersViewModel

@Composable
fun UserDetailsScreen(
    username: String = "",
    viewModel: UsersViewModel = hiltViewModel(),
    onNavigateToFollower: (String) -> Unit
) {

    viewModel.getSearchedUser(username)
    viewModel.getFollowers(username)

    val userDetails by remember {viewModel.userDetailsApiData}
    val followers by remember {viewModel.followersApiData}

    Column {
        UserIntro(userDetails)
        UserDetails(userDetails)
        FollowersSection(followers, onNavigateToFollower)
    }
}

@Composable
fun UserIntro(user: UserDetailsModel) {
    Surface(
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(user.avatarUrl)
                    .crossfade(true)
                    .build(),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .clip(CircleShape)
                    .size(128.dp)
            )
            Text(
                text = user.login,
                style = MaterialTheme.typography.h5,
            )
            user.bio?.let {
                Text(
                    text = it,
                    style = MaterialTheme.typography.h5,
                )
            }
        }
    }
}

@Composable
fun UserDetails(user: UserDetailsModel) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(1)
    ) {
        item { Text(text = "Name: ${user.login}") }
        item { Text(text = "Email: ${user.email}") }
        item { Text(text = "Company: ${user.company}") }
        item { Text(text = "Blog: ${user.blog}") }
        item { Text(text = "Hireable: ${user.hireable}") }
        item { Text(text = "Location: ${user.location}") }
        item { Text(text = "Organization: ${user.organizationsUrl}") }
        item { Text(text = "Repos: ${user.reposUrl}") }
        item { Text(text = "No. of followers: ${user.followers}") }
        item { Text(text = "No. of following: ${user.following}") }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun FollowersSection(
    followers: Followers,
    onNavigateToFollower: (String) -> Unit,
) {
    LazyRow {
        items(followers) { follower ->
            Surface(
                modifier = Modifier.fillMaxWidth(),
                onClick = { onNavigateToFollower(follower.login) }
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    AsyncImage(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(follower.avatarUrl)
                            .crossfade(true)
                            .build(),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .clip(CircleShape)
                            .size(128.dp)
                    )
                    Text(
                        text = follower.login,
                        style = MaterialTheme.typography.h5,
                    )
                }
            }
        }
    }
}