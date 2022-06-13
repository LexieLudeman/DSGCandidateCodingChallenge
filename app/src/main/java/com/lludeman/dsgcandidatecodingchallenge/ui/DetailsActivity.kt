package com.lludeman.dsgcandidatecodingchallenge.ui

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.lludeman.dsgcandidatecodingchallenge.R
import com.lludeman.dsgcandidatecodingchallenge.common.Event
import com.squareup.picasso.Picasso


class DetailsActivity : AppCompatActivity() {

    private lateinit var titleText: TextView
    private lateinit var locationText: TextView
    private lateinit var imageView: ImageView
    private lateinit var dateText: TextView
    private val eventKey = "EVENT_KEY"
    private lateinit var event: Event
    private val picasso = Picasso.get()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.details_activity)
        val intent = intent
        event = intent.getSerializableExtra(eventKey) as Event

        titleText = findViewById(R.id.detailsTitle)
        locationText = findViewById(R.id.detailsLocation)
        imageView = findViewById(R.id.detailsImage)
        dateText = findViewById(R.id.detailsDate)

        titleText.text = event.title
        locationText.text = event.venue.displayLocation
        dateText.text = event.datetimeLocal
        picasso.load(event.performers[0].image).into(imageView)

    }

    fun favoritePressed(state: Boolean) {

    }
}