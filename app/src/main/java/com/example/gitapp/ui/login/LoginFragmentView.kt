package com.example.gitapp.ui.login

interface LoginFragmentView {

    fun goToNextScreen()

    fun showError()

    fun showLoader()

    fun hideLoader()

}