package com.example.gitapp.ui.readme

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.gitapp.R
import com.example.gitapp.util.SchedulerProvider
import kotlinx.android.synthetic.main.fragment_readme.view.*

private const val FULL_NAME = "fullName"

class ReadmeFragment : Fragment(), ReadmeFragmentView {

    private lateinit var readmeContent: TextView
    private lateinit var fullName: String
    private lateinit var presenter: ReadmeFragmentPresenter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            fullName = it.getString(FULL_NAME)!!
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_readme, container, false)
        initViews(root)
        return root

    }

    private fun initViews(view: View) {
        readmeContent = view.readme_content
    }

    companion object {

        fun newInstance(fullName: String) =
            ReadmeFragment().apply {
                arguments = Bundle().apply {
                    putString(FULL_NAME, fullName)
                }
            }
    }

    override fun onResume() {
        super.onResume()
        presenter = ReadmeFragmentPresenterImpl(ReadmeFragmentInteractorImpl(), this, SchedulerProvider)
        presenter.getReadme(fullName)
    }

    override fun showContent(content: String) {
        readmeContent.text = content
    }

    override fun showError() {
    }
}
