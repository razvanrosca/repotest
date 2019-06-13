package com.example.gitapp.ui.main

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.gitapp.R
import com.example.gitapp.ui.login.LoginFragment
import com.example.gitapp.ui.readme.ReadmeFragment
import com.example.gitapp.ui.reposlisting.OnItemClickedListener

class MainActivity : AppCompatActivity(), OnItemClickedListener{


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        addLoginFragment()
    }

    private fun addLoginFragment() {
        supportFragmentManager.beginTransaction().add(R.id.container, LoginFragment.newInstance()).commit()
    }

    override fun onItemClicked(fullName: String) {
        supportFragmentManager.beginTransaction().replace(R.id.container,ReadmeFragment.newInstance(fullName)).addToBackStack("").commit()
    }
}
