package com.lludeman.dsgcandidatecodingchallenge.ui

import android.os.Bundle
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lludeman.dsgcandidatecodingchallenge.CodingChallengeApplication
import com.lludeman.dsgcandidatecodingchallenge.R
import com.lludeman.dsgcandidatecodingchallenge.data.remote.RetrofitImpl
import com.lludeman.dsgcandidatecodingchallenge.respository.EventRepository
import com.lludeman.dsgcandidatecodingchallenge.viewmodel.MainViewModel


class MainActivity : AppCompatActivity() {

    private lateinit var mainViewModel: MainViewModel
    private lateinit var adapter: EventAdapter
    private lateinit var recyclerView: RecyclerView
    lateinit var searchView: androidx.appcompat.widget.SearchView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainViewModel =
            MainViewModel(eventRepository = EventRepository(
                serviceImpl = RetrofitImpl

            )
            )

        adapter = EventAdapter(
            allEvents = emptyList(),
            eventsDao = (applicationContext as CodingChallengeApplication).database.eventsDao()
        )

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
        mainViewModel.eventData.observe(this) { events ->
            adapter.updateItems(events = events)

        }

        getEvents(resources.getString(R.string.client_id))

    }

    private fun getEvents(search: String) {
        mainViewModel.getEventData(search)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {

        menuInflater.inflate(R.menu.menu, menu)
        val menuItem = menu.findItem(R.id.action_search)
        searchView = menuItem.actionView as androidx.appcompat.widget.SearchView
        searchView.queryHint = "Type here to search"
        val urlEnd = resources.getString(R.string.client_id)

        searchView.setOnQueryTextListener(object : androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                return false
            }
            override fun onQueryTextChange(newText: String): Boolean {
                getEvents(urlEnd+newText)
                return false
            }
        })

        return super.onCreateOptionsMenu(menu)
    }

}