package com.studio1hub.app.pagingapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.studio1hub.app.pagingapp.R
import com.studio1hub.app.pagingapp.ui.adapter.CoffeePostsAdapter
import com.studio1hub.app.pagingapp.utils.CheckNetwork
import com.studio1hub.app.pagingapp.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val coffeePostsAdapter = CoffeePostsAdapter()
    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.title = getString(R.string.appbar_blog_post)

        mainViewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)

        initializeList()

        if(CheckNetwork.isNetworkAvailable(this))
            observeLiveData()
        else
            Toast.makeText(this,"Please check your network connection!",Toast.LENGTH_SHORT).show()
    }

    private fun observeLiveData() {
        mainViewModel.getPosts().observe(this, Observer {
            coffeePostsAdapter.submitList(it)
        })
    }

    private fun initializeList() {
        rvlist.layoutManager = LinearLayoutManager(this)
        rvlist.adapter = coffeePostsAdapter
    }

}