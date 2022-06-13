package com.lludeman.dsgcandidatecodingchallenge.common

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Venue(
    @SerializedName("display_location")
    @Expose
    var displayLocation: String
) : Serializable