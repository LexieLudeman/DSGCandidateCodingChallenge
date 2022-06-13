package com.lludeman.dsgcandidatecodingchallenge.ui

import android.os.Bundle
import android.util.Log
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lludeman.dsgcandidatecodingchallenge.R
import com.lludeman.dsgcandidatecodingchallenge.data.RetrofitImpl
import com.lludeman.dsgcandidatecodingchallenge.respository.EventRepository
import com.lludeman.dsgcandidatecodingchallenge.viewmodel.MainViewModel


class MainActivity : AppCompatActivity() {

    private lateinit var retrofit: RetrofitImpl
    private lateinit var mainViewModel: MainViewModel
    private lateinit var adapter: EventAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var searchView: SearchView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        searchView = findViewById(R.id.searchView)
        retrofit = RetrofitImpl

        mainViewModel =
            MainViewModel(eventRepository = EventRepository(
                retrofit
            )
            )

        adapter = EventAdapter(
            allEvents = emptyList(),
            mainViewModel = mainViewModel,
            lifecycleOwner = this
        )

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
        mainViewModel.eventData.observe(this) { events ->
            adapter.updateItems(events = events)

        }

        val urlEnd = resources.getString(R.string.client_id)
        getEvents(resources.getString(R.string.client_id))

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                return false
            }
            override fun onQueryTextChange(newText: String): Boolean {
                getEvents(urlEnd+newText)
                return false
            }
        })

    }

    private fun getEvents(search: String) {
        mainViewModel.getEventData(search)
        val data = mainViewModel.eventData
        Log.d("Lexie", "Data contains")
    }



}