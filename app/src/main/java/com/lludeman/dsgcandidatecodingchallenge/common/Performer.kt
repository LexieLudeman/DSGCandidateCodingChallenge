package com.lludeman.dsgcandidatecodingchallenge.common

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Performer (
    @SerializedName("image")
    @Expose
    var image: String
) : Serializable