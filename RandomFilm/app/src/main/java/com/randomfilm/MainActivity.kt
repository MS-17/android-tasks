package com.randomfilm

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.activity.ComponentActivity
import kotlinx.serialization.json.*

class MainActivity : ComponentActivity() {

    companion object {
        var usedIndices: MutableList<Int> = mutableListOf()
        var counter: Int = 0
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // views to fill later
        val resultTextView = this.findViewById<TextView>(R.id.result_text_view)
        val filmTitleTextView = findViewById<TextView>(R.id.title)
        val descTextView = findViewById<TextView>(R.id.description)
        val ratingTextView = findViewById<TextView>(R.id.rating)
        val yearTextView = findViewById<TextView>(R.id.year)
        val starringTextView = findViewById<TextView>(R.id.starring)

        //val filmsList = resources.getStringArray(R.array.films)

        // parse json with kotlinx.serialization
        val filmsJson = resources.openRawResource(R.raw.films)
                             .bufferedReader(Charsets.UTF_8)
                             .use { it.readText() }
        Log.d("My info", filmsJson)
        val filmsList = Json.decodeFromString<Map<String, Array<Film>>>(filmsJson)["films"]
        Log.d("My info", filmsList.toString())

        // buttons
        val resetBtn = findViewById<Button>(R.id.reset_button)
        resetBtn.setOnClickListener {
            Log.d("My Info", "Restarting")
            findViewById<TextView>(R.id.result_text_view).text = "Let's start again"
            filmTitleTextView.text = "Title: -"
            descTextView.text = "Description: -"
            ratingTextView.text = "Rating: -"
            yearTextView.text = "Year: -"
            starringTextView.text = "Starring: -"
            counter = 0
            usedIndices.clear()
        }

        val submitBtn = findViewById<Button>(R.id.submit_button)
        submitBtn.setOnClickListener {
            if (counter == filmsList!!.size) {
                resultTextView.text = "No films left, reset"
            } else {
                Log.d("My Info", "Generate indeces")
                var idx = 0
                while (idx in usedIndices) {
                    Log.d("My Info", "Loop")
                    idx = (filmsList.indices).random()
                }
                Log.d("My Info", "Index: $idx")
                val f = filmsList[idx]
                resultTextView.text = "Films"
                filmTitleTextView.text = "Title: " + f.title
                descTextView.text = "Description: " + f.description
                ratingTextView.text = "Rating: " + f.rating.toString()
                yearTextView.text = "Year: " + f.year.toString()
                starringTextView.text = "Starring: " + f.starring.toString()
                                                                 .replace("[", "")
                                                                 .replace("]", "")
                usedIndices.add(idx)
                counter++
            }
        }
    }
}