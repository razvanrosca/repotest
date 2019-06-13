package com.example.gitapp.ui.reposlisting

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.gitapp.R
import com.example.gitapp.model.Repo
import com.example.gitapp.util.ImageUtils
import kotlinx.android.synthetic.main.repo_item.view.*

class ReposAdapter(
    private val onItemClickedListener: OnItemClickedListener,
    private val context: Context,
    private val repos: List<Repo>
) :
    RecyclerView.Adapter<ReposAdapter.Holder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, position: Int): Holder {
        return Holder(LayoutInflater.from(context).inflate(R.layout.repo_item, viewGroup, false))
    }

    override fun getItemCount(): Int {
        return repos.size
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val repo = repos[position]
        ImageUtils.displayImage(context, holder.avatarImage, repo.owner.avatarUrl)
        holder.title.text = repo.fullName
        holder.itemView.setOnClickListener { onItemClickedListener.onItemClicked(repo.fullName) }
    }


    class Holder(view: View) : RecyclerView.ViewHolder(view) {

        val avatarImage = view.avatar!!
        val title = view.title!!
    }

}