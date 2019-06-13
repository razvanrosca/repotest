package com.example.gitapp.model

import com.google.gson.annotations.SerializedName


data class UserDetails(
    @SerializedName("current_user_url") var currentUserUrl:String="",
    @SerializedName("current_user_authorizations_html_url") var currentUserAuthorization:String="",
    @SerializedName("user_url") var userUrl:String="",
    @SerializedName("user_organizations_url") var userOrganizationUrl:String="",
    @SerializedName("user_repositories_url") var userRepositoriesUrl:String="",
    @SerializedName("user_search_url") var userSearchUrl:String="")