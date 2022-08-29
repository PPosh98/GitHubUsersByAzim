package com.example.githubusersbyazim.model.users


import com.google.gson.annotations.SerializedName

data class UsersItemModel(
    @SerializedName("avatar_url")
    val avatarUrl: String = "https://avatars.githubusercontent.com/u/1?v=4",
    @SerializedName("events_url")
    val eventsUrl: String = "https://api.github.com/users/mojombo/events{/privacy}",
    @SerializedName("followers_url")
    val followersUrl: String = "https://api.github.com/users/mojombo/followers",
    @SerializedName("following_url")
    val followingUrl: String = "https://api.github.com/users/mojombo/following{/other_user}",
    @SerializedName("gists_url")
    val gistsUrl: String = "https://api.github.com/users/mojombo/gists{/gist_id}",
    @SerializedName("gravatar_id")
    val gravatarId: String = "",
    @SerializedName("html_url")
    val htmlUrl: String = "https://github.com/mojombo",
    @SerializedName("id")
    val id: Int = 1,
    @SerializedName("login")
    val login: String = "mojombo",
    @SerializedName("node_id")
    val nodeId: String = "MDQ6VXNlcjE=",
    @SerializedName("organizations_url")
    val organizationsUrl: String = "https://api.github.com/users/mojombo/orgs",
    @SerializedName("received_events_url")
    val receivedEventsUrl: String = "https://api.github.com/users/mojombo/received_events",
    @SerializedName("repos_url")
    val reposUrl: String = "https://api.github.com/users/mojombo/repos",
    @SerializedName("site_admin")
    val siteAdmin: Boolean = false,
    @SerializedName("starred_url")
    val starredUrl: String = "https://api.github.com/users/mojombo/starred{/owner}{/repo}",
    @SerializedName("subscriptions_url")
    val subscriptionsUrl: String = "https://api.github.com/users/mojombo/subscriptions",
    @SerializedName("type")
    val type: String = "User",
    @SerializedName("url")
    val url: String = "https://api.github.com/users/mojombo"
)