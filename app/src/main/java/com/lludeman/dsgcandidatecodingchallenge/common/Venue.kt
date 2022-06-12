package com.lludeman.dsgcandidatecodingchallenge.common

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Venue(
    @SerializedName("display_location")
    @Expose
    var displayLocation: String
)