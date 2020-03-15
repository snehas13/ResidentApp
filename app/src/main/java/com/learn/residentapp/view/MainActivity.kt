package com.learn.residentapp.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.learn.domain.entities.Resident
import com.learn.residentapp.R
import com.learn.residentapp.Utility.ADD
import com.learn.residentapp.Utility.DATA
import com.learn.residentapp.Utility.EDIT
import com.learn.residentapp.adapters.ResidentViewAdapter
import com.learn.residentapp.viewmodel.MainViewModel

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity(), ResidentViewAdapter.ItemClickListener {

    lateinit var viewModel: MainViewModel
    lateinit var residentViewAdapter: ResidentViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        initView()

        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)

        fab.setOnClickListener { view ->
            sendAddIntent()
        }

        viewModel.residentLiveData.observe(this, Observer {
            Log.i("MainActivity"," Received items through observer = ${it.size}")
            residentViewAdapter.update(it)
        })

    }

    override fun onResume() {
        super.onResume()
        viewModel.getAllResidents()
    }


    fun sendAddIntent() {
        var intent = Intent(this, ResidentActivity::class.java)
        intent.action = ADD
        startActivity(intent)
    }

    fun sendEditIntent(resident: Resident) {
        var intent = Intent(this, ResidentActivity::class.java)
        intent.action = EDIT
        intent.putExtra(DATA,resident)
        startActivity(intent)
    }

    fun initView() {
        recyclerView.layoutManager = LinearLayoutManager(this)
        residentViewAdapter = ResidentViewAdapter(this)
        recyclerView.adapter = residentViewAdapter
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onItemClick(resident: Resident) {
        sendEditIntent(resident)
    }
}
