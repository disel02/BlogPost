package com.studio1hub.app.pagingapp.utils

import androidx.recyclerview.widget.DiffUtil
import com.studio1hub.app.pagingapp.model.Post

class DiffUtilCallBack : DiffUtil.ItemCallback<Post>() {
    override fun areItemsTheSame(oldItem: Post, newItem: Post): Boolean {
        return oldItem.key == newItem.key
    }

    override fun areContentsTheSame(oldItem: Post, newItem: Post): Boolean {
        return oldItem.title == newItem.title
                && oldItem.author == newItem.author
                && oldItem.thumbnail == newItem.thumbnail
    }

}