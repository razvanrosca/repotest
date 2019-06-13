package com.example.gitapp.ui.login

import com.example.gitapp.model.UserDetails
import io.reactivex.Single

interface LoginFragmentInteractor {

    fun areUserNameAndPasswordCompleted(username:String, password:String):Boolean

     fun startLogin(username: String, password: String): Single<UserDetails>

}