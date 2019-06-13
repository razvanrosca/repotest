package com.example.gitapp.ui.reposlisting

import com.example.gitapp.model.Repo

interface ReposListingView {

    fun showReposList(repos: List<Repo>)

    fun showError()

    fun showLoader()

    fun hideLoader()

}