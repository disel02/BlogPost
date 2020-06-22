package com.studio1hub.app.pagingapp.repository

import android.util.Log
import androidx.paging.PageKeyedDataSource
import com.studio1hub.app.pagingapp.model.Post
import com.studio1hub.app.pagingapp.remote.ApiClient
import com.studio1hub.app.pagingapp.remote.ApiService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class PostsDataSource(coroutineContext: CoroutineContext) : PageKeyedDataSource<String, Post>() {
    private val apiService = ApiClient.provideRetrofitService()

    private val job = Job()
    private val scope = CoroutineScope(coroutineContext + job)

    override fun loadInitial(params: LoadInitialParams<String>, callback: LoadInitialCallback<String, Post>) {
        scope.launch {
            try {
                val response = apiService.fetchPosts(loadSize = params.requestedLoadSize)
                when{
                    response.isSuccessful -> {
                        val listing = response.body()?.data
                        val blogPosts = listing?.children?.map { it.data }
                        callback.onResult(blogPosts ?: listOf(), listing?.before, listing?.after)
                    }
                }
            }catch (exception : Exception){
                Log.e("PostsDataSource01", "Failed to fetch data! $exception")
            }
        }
    }

    override fun loadAfter(params: LoadParams<String>, callback: LoadCallback<String, Post>) {
        scope.launch {
            try {
                val response = apiService.fetchPosts(loadSize = params.requestedLoadSize, after = params.key)
                when{
                    response.isSuccessful -> {
                        val listing = response.body()?.data
                        val blogPosts = listing?.children?.map { it.data }
                        callback.onResult(blogPosts ?: listOf(), listing?.after)
                    }
                }

            }catch (exception : Exception){
                Log.e("PostsDataSource02", "Failed to fetch data! $exception")
            }
        }

    }

    override fun loadBefore(params: LoadParams<String>, callback: LoadCallback<String, Post>) {
        scope.launch {
            try {
                val response =
                    apiService.fetchPosts(loadSize = params.requestedLoadSize, before = params.key)
                when{
                    response.isSuccessful -> {
                        val listing = response.body()?.data
                        val blogPosts = listing?.children?.map { it.data }
                        callback.onResult(blogPosts ?: listOf(), listing?.after)
                    }
                }

            }catch (exception : Exception){
                Log.e("PostsDataSource03", "Failed to fetch data! $exception")
            }
        }

    }

    override fun invalidate() {
        super.invalidate()
        job.cancel()
    }

}