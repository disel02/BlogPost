package com.studio1hub.app.pagingapp.remote

import com.studio1hub.app.pagingapp.model.BlogApiResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("/r/aww/hot.json")
    suspend fun fetchPosts(
        @Query("limit") loadSize: Int = 10,
        @Query("after") after: String? = null,
        @Query("before") before: String? = null
    ): Response<BlogApiResponse>
}