package com.example.gitapp.model

import com.google.gson.annotations.SerializedName

data class RepoOwner(
    @SerializedName("login") var login: String,
    @SerializedName("id") var id: String,
    @SerializedName("node_id") var nodeId: String,
    @SerializedName("avatar_url") var avatarUrl: String,
    @SerializedName("gravatar_id") var gravatarId: String,
    @SerializedName("url") var url: String
)
