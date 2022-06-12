package com.lludeman.dsgcandidatecodingchallenge.data

import retrofit2.Response
import retrofit2.http.GET

interface EventApiCall {
    @GET("events?client_id=Mjc0MDE3ODh8MTY1NDk3MjQwNS4yNzE2ODEz")
    suspend fun getEvents(): Response<EventResponse>
}