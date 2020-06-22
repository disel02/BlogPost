package com.studio1hub.app.pagingapp.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object ApiClient{

    private const val URL = "https://www.reddit.com/"

    fun provideRetrofitService():ApiService = Retrofit.Builder()
        .baseUrl(URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(ApiService::class.java)

}
