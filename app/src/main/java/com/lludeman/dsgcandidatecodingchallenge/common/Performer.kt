package com.lludeman.dsgcandidatecodingchallenge.common

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Performer (
    @SerializedName("image")
    @Expose
    var image: String
)