package com.example.gitapp.model

import com.google.gson.annotations.SerializedName


data class Repo(
    @SerializedName("id") var id: String,
    @SerializedName("node_id") var nodeId: String,
    @SerializedName("name") var name: String,
    @SerializedName("full_name") var fullName: String,
    @SerializedName("private") var private: Boolean,
    @SerializedName("owner") var owner: RepoOwner
)
