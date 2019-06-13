package com.example.gitapp.ui.reposlisting

import android.app.AlertDialog
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.ProgressBar
import com.example.gitapp.R
import com.example.gitapp.model.Repo
import com.example.gitapp.util.SchedulerProvider
import kotlinx.android.synthetic.main.fragment_repos_listing.view.*


class ReposListingFragment : Fragment(), ReposListingView {

    private lateinit var recyclerView: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var presenterImpl: ReposListingPresenter
    private lateinit var reposAdapter: ReposAdapter


    companion object {
        fun newInstance() = ReposListingFragment()
    }

    private fun initViews(view: View) {
        recyclerView = view.recycler_view
        progressBar = view.progress_circular
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenterImpl = ReposListingPresenterImpl(this, SchedulerProvider)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val root = inflater.inflate(R.layout.fragment_repos_listing, container, false)
        initViews(root)
        return root
    }


    override fun showReposList(repos: List<Repo>) {
        val sortedListByName = repos.sortedWith(compareBy { it.fullName })
        initRecyclerView(sortedListByName)

    }

    override fun showError() {
        val alertDialog: AlertDialog.Builder = AlertDialog.Builder(activity)
        alertDialog.setMessage(getString(R.string.error_message_repos))
        alertDialog.setTitle(getString(R.string.error_title))
        alertDialog.setPositiveButton(getString(R.string.ok)) { dialog, _ -> dialog.dismiss() }
        alertDialog.create()
        alertDialog.show()
    }

    override fun onResume() {
        super.onResume()
        presenterImpl.getReposList()
    }


    override fun onStop() {
        super.onStop()
        presenterImpl.unsubscribe()
    }

    override fun showLoader() {
        activity!!.window.setFlags(
            WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
            WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE
        )

        progressBar.visibility = View.VISIBLE
    }

    override fun hideLoader() {
        activity!!.window.clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
        progressBar.visibility = View.GONE
    }

    private fun initRecyclerView(repos: List<Repo>) {
        reposAdapter = ReposAdapter(activity as OnItemClickedListener, context!!, repos)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = reposAdapter
    }
}
