package com.example.gitapp.ui.login

import com.example.gitapp.model.UserDetails
import com.example.gitapp.services.GitServices
import io.reactivex.Single

class LoginFragmentInteractorImpl : LoginFragmentInteractor {

    override fun areUserNameAndPasswordCompleted(username: String, password: String): Boolean {
        return !(username.isEmpty() || password.isEmpty())


    }

    override fun startLogin(username: String, password: String): Single<UserDetails> {
        return GitServices.login(username, password)
    }
}