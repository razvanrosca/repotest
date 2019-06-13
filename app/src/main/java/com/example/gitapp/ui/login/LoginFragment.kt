package com.example.gitapp.ui.login

import android.app.AlertDialog
import android.os.Bundle
import android.support.design.widget.TextInputEditText
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Button
import android.widget.ProgressBar
import com.example.gitapp.R
import com.example.gitapp.ui.reposlisting.ReposListingFragment
import com.example.gitapp.util.SchedulerProvider


class LoginFragment : Fragment(), LoginFragmentView, View.OnClickListener {

    private lateinit var userNameEditText: TextInputEditText
    private lateinit var passwordEditText: TextInputEditText
    private lateinit var submit: Button
    private lateinit var presenter: LoginFragmentPresenter
    private lateinit var progressBar: ProgressBar


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val root = inflater.inflate(R.layout.fragment_login, container, false)
        initViews(root)
        presenter = LoginFragmentPresenterImpl(SchedulerProvider, LoginFragmentInteractorImpl(), this)
        return root
    }

    companion object {
        fun newInstance() = LoginFragment()
    }

    private fun initViews(view: View) {

        userNameEditText = view.findViewById(R.id.user_name_edt)
        passwordEditText = view.findViewById(R.id.password_edt)
        submit = view.findViewById(R.id.submit)
        progressBar = view.findViewById(R.id.progress_circular)

        submit.setOnClickListener(this)

    }

    override fun onClick(view: View) {
        if (view.id == R.id.submit) {
            presenter.startLogin(userNameEditText.text.toString(), passwordEditText.text.toString())
        }
    }

    override fun goToNextScreen() {
        activity!!.supportFragmentManager.beginTransaction().replace(R.id.container, ReposListingFragment.newInstance())
            .commit()
    }

    override fun showError() {

        val alertDialog: AlertDialog.Builder = AlertDialog.Builder(activity)
        alertDialog.setMessage(getString(R.string.error_message))
        alertDialog.setTitle(getString(R.string.error_title))
        alertDialog.setPositiveButton(getString(R.string.ok)) { dialog, _ -> dialog.dismiss() }
        alertDialog.create()
        alertDialog.show()

    }

    override fun onStop() {
        super.onStop()
        presenter.unsubscribe()
    }

    override fun showLoader() {
        activity!!.window.setFlags(
            WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
            WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE
        )

        progressBar.visibility = View.VISIBLE
        userNameEditText.clearFocus()
    }

    override fun hideLoader() {
        activity!!.window.clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
        progressBar.visibility = View.GONE
    }
}
