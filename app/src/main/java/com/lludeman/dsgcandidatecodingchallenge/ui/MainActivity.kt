package com.lludeman.dsgcandidatecodingchallenge.ui

import android.os.Bundle
import android.util.Log
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
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
        getEvents()
    }

    private fun getEvents() {
        mainViewModel.getEventData()
        val data = mainViewModel.eventData
        Log.d("Lexie", "Data contains")
    }

}