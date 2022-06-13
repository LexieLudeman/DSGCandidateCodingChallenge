package com.lludeman.dsgcandidatecodingchallenge.data.remote

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface EventApiCall {
    @GET
    suspend fun getEvents(@Url url: String): Response<EventResponse>
}