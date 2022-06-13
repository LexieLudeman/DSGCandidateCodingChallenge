package com.lludeman.dsgcandidatecodingchallenge.respository

import android.util.Log
import com.lludeman.dsgcandidatecodingchallenge.data.remote.EventResponse
import com.lludeman.dsgcandidatecodingchallenge.data.remote.RetrofitImpl
import com.lludeman.dsgcandidatecodingchallenge.common.Event
import com.lludeman.dsgcandidatecodingchallenge.data.database.EventsDao
import retrofit2.Response

class EventRepository(
    private val serviceImpl : RetrofitImpl
) {
    suspend fun getEvents(search: String): List<Event> {
        var eventData : List<Event> = ArrayList()
        val response: Response<EventResponse> = serviceImpl.instance.getEvents(search)
        val body = response.body()
        Log.d("Lexie", "The response is ${response.body()}!!")

        if (response.isSuccessful && body != null) {
            eventData = body.events
        }
        return eventData
    }
}