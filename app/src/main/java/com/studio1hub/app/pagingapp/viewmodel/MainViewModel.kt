package com.studio1hub.app.pagingapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.studio1hub.app.pagingapp.model.Post
import com.studio1hub.app.pagingapp.repository.PostsDataSource
import kotlinx.coroutines.Dispatchers

class MainViewModel : ViewModel() {
    private var postsLiveData  :LiveData<PagedList<Post>>

    init {
        val config = PagedList.Config.Builder()
            .setPageSize(30)
            .setEnablePlaceholders(false)
            .build()
        postsLiveData  = initializedPagedListBuilder(config).build()
    }

    fun getPosts():LiveData<PagedList<Post>> = postsLiveData

    private fun initializedPagedListBuilder(config: PagedList.Config):
            LivePagedListBuilder<String, Post> {

        val dataSourceFactory = object : DataSource.Factory<String, Post>() {
            override fun create(): DataSource<String, Post> {
                return PostsDataSource(Dispatchers.IO)
            }
        }
        return LivePagedListBuilder<String, Post>(dataSourceFactory, config)
    }
}