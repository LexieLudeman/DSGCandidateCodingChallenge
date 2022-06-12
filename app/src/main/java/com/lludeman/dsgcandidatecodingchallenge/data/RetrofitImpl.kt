package com.lludeman.dsgcandidatecodingchallenge.data

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitImpl {

    lateinit var instance: EventApiCall

    init {
        create()
    }

    private fun create(): EventApiCall {
        val baseURL = "https://api.seatgeek.com/2/"
        val retrofit = Retrofit.Builder()
            .baseUrl(baseURL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        instance = retrofit.create(EventApiCall::class.java)
        return instance
    }
}