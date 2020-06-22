package com.studio1hub.app.pagingapp.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import coil.transform.CircleCropTransformation
import com.studio1hub.app.pagingapp.R
import com.studio1hub.app.pagingapp.model.Post
import com.studio1hub.app.pagingapp.utils.DiffUtilCallBack
import kotlinx.android.synthetic.main.item_post.view.*

class CoffeePostsAdapter : PagedListAdapter<Post, CoffeePostsAdapter.MyViewHolder>(DiffUtilCallBack()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_post, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    class MyViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {

        fun bind(post: Post) {
            itemView.apply {
                tvitemtitle.text = post.title
                tvitemauthor.text = post.author
                ivitemimg.load(post.thumbnail) {
                    placeholder(R.drawable.ic_photo)
                    transformations(CircleCropTransformation())
                }
            }
        }
    }
}