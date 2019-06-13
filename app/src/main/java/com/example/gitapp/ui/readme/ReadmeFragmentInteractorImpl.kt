package com.example.gitapp.ui.readme

import android.util.Base64


class ReadmeFragmentInteractorImpl : ReadmeFragmentInteractor {

    override fun getDecodedData(content: String): String {
        return String(Base64.decode(content, Base64.DEFAULT))
    }

}