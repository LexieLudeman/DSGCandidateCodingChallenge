package com.lludeman.dsgcandidatecodingchallenge.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import com.lludeman.dsgcandidatecodingchallenge.CodingChallengeApplication
import com.lludeman.dsgcandidatecodingchallenge.R
import com.lludeman.dsgcandidatecodingchallenge.common.Event
import com.lludeman.dsgcandidatecodingchallenge.data.database.EventsDao
import com.squareup.picasso.Picasso
import java.text.SimpleDateFormat
import java.util.*


class DetailsActivity : AppCompatActivity() {

    private lateinit var titleText: TextView
    private lateinit var locationText: TextView
    private lateinit var imageView: ImageView
    private lateinit var dateText: TextView
    private lateinit var favoriteButton: ToggleButton
    private lateinit var adapter: EventAdapter
    private val eventKey = "EVENT_KEY"
    private lateinit var event: Event
    private val picasso = Picasso.get()
    private lateinit var eventsDao: EventsDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.details_activity)
        val intent = intent
        event = intent.getSerializableExtra(eventKey) as Event

        eventsDao = (applicationContext as CodingChallengeApplication).database.eventsDao()
        titleText = findViewById(R.id.detailsTitle)
        locationText = findViewById(R.id.detailsLocation)
        imageView = findViewById(R.id.detailsImage)
        dateText = findViewById(R.id.detailsDate)
        favoriteButton = findViewById(R.id.favorite)

        async
//        val favoriteEvent = checkIfFavorite(event.id)

        titleText.text = event.title
        locationText.text = event.venue.displayLocation

        if (event.isDatetimeTbd) {
            dateText.text = "TBD"
        } else {
            dateText.text = getDate(event.datetimeLocal.toString())
        }

        picasso
            .load(event.performers[0]
            .detailsImage.image)
            .resize(800, 500)
            .centerCrop()
            .into(imageView)

        val actionBar = supportActionBar!!
        actionBar.setDisplayHomeAsUpEnabled(true)
        actionBar.setDisplayShowHomeEnabled(true)
        actionBar.title = "Details"

        favoriteButton.setOnClickListener {
            if (favoriteButton.isChecked) {
                favoriteButton.background = ResourcesCompat.getDrawable(resources, R.drawable.filledhearticon, null)
            }
            else {
                favoriteButton.background = ResourcesCompat.getDrawable(resources, R.drawable.hearticon, null)
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    suspend fun checkIfFavorite(eventId: Int) : Boolean{
        if (eventsDao.checkIfEventFavorite(eventId) > 0) {
            return true
        }
        return false
    }


    private fun getDate(dateStr: String) : String{
        val currentFormat = SimpleDateFormat("yyyy-MM-DD HH:MM:SS")
        return try {
            val date = currentFormat.parse(dateStr.replace('T', ' '))
            val formatter = SimpleDateFormat("EEE, MMM dd yyyy h:mm a", Locale.ENGLISH)
            (formatter.format(date))
        } catch (e: Exception){
            Log.d("Lexie",e.toString())
            dateStr
        }
    }

    private fun setFavoriteState(event: Event) {

    }

}