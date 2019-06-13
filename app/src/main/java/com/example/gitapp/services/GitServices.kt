package com.example.gitapp.services

import com.example.gitapp.model.Readme
import com.example.gitapp.model.Repo
import com.example.gitapp.model.UserDetails
import io.reactivex.Single

object GitServices {

    fun login(userName: String, password: String): Single<UserDetails> {
        return ServiceFactory.createRetrofitService(ServiceAPI::class.java, userName, password)
            .loginUser()
    }

    fun getRepositories(): Single<List<Repo>> {
        return ServiceFactory.createRetrofitService(ServiceAPI::class.java)
            .getRepositories()
    }

    fun getReadme(fullName: String): Single<Readme> {
        return ServiceFactory.createRetrofitService(ServiceAPI::class.java)
            .getReadme(fullName)
    }

}