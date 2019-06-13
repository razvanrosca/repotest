package com.example.gitapp.ui.login

interface LoginFragmentPresenter {

    fun startLogin(username: String, password: String)

    fun unsubscribe()

}