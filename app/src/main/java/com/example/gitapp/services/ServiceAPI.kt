package com.example.gitapp.services

import com.example.gitapp.model.Readme
import com.example.gitapp.model.Repo
import com.example.gitapp.model.UserDetails
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface ServiceAPI {

    @GET(".")
    fun loginUser(): Single<UserDetails>

    @GET("repositories")
    fun getRepositories(): Single<List<Repo>>

    @GET("repos/{repo}/readme")
    fun getReadme(@Path(value = "repo" , encoded = true) fullName: String): Single<Readme>

}