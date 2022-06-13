package com.lludeman.dsgcandidatecodingchallenge.data

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface EventApiCall {
    @GET()
    suspend fun getEvents(@Url url: String): Response<EventResponse>

    /*
       @GET
    public Call<ResponseBody> profilePicture(@Url String url);
     */
}