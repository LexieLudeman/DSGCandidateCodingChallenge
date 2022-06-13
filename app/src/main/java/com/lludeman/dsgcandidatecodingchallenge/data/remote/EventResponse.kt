package com.lludeman.dsgcandidatecodingchallenge.data.remote

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.lludeman.dsgcandidatecodingchallenge.common.Event

class EventResponse (
    @SerializedName("events")
    @Expose
    var events: List<Event>
)