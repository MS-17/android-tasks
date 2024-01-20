package com.randomfilm

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.activity.ComponentActivity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class MainActivity : ComponentActivity() {

    companion object {
        var usedIndices: MutableList<Int> = mutableListOf()
        var counter: Int = 0
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val resultTextView = this.findViewById<TextView>(R.id.result_text_view)

        val filmsList = resources.getStringArray(R.array.films)

        val resetBtn = findViewById<Button>(R.id.reset_button)
        resetBtn.setOnClickListener {
            Log.d("My Info", "Restarting")
            findViewById<TextView>(R.id.result_text_view).text = "Let's start again"
            counter = 0
            usedIndices.clear()
        }

        val gson = Gson()
        val filmsJson = resources.openRawResource(R.raw.films)
                             .bufferedReader(Charsets.UTF_8)
                             .use { it.readText() }
        Log.d("My info", filmsJson)
        //val type = object : TypeToken<Map<String, Array<Film>>>() {}.type
        //val films = gson.fromJson<Map<String, Array<Film>>>(filmsJson, type)
        //Log.d("My info", films.toString())
        /*val a = "{\n\"films\":[{\n\"title\": \"Film F\",\n" +
                "\"description\": \"Desc\",\n" +
                "\"rating\": 9.8,\n" +
                "\"year\": 2004,\n" +
                " \"starring\": [\"A\", \"B\", \"C\"]\n}," +
                "{\n\"title\": \"Film F\",\n" +
                "\"description\": \"Desc\",\n" +
                "\"rating\": 9.8,\n" +
                "\"year\": 2004,\n" +
                " \"starring\": [\"A\", \"B\", \"C\"]\n}]\n}"*/
        val a = "{\n[{\n\"title\": \"Film F\",\n" +
                "\"description\": \"Desc\",\n" +
                "\"rating\": 9.8,\n" +
                "\"year\": 2004,\n" +
                " \"starring\": [\"A\", \"B\", \"C\"]\n}," +
                "{\n\"title\": \"Film F\",\n" +
                "\"description\": \"Desc\",\n" +
                "\"rating\": 9.8,\n" +
                "\"year\": 2004,\n" +
                " \"starring\": [\"A\", \"B\", \"C\"]\n}]\n}"
        //val t = object : TypeToken<Map<String, List<Film>>>() {}.type
        //val b = gson.fromJson(a, Map<String, Array<Film>>::class.java).toList()
        //val b = gson.fromJson(a, t::class.java)
        val c = "{\n\"films\":[\"A\"]\n}"
        //val b = gson.fromJson(a, Array<Film>::class.java).toList()
        val type = object : TypeToken<Map<String, Array<String>>>(){}.type
        val b = gson.fromJson(c, type::class.java)
        Log.d("My info", b.toString())


        val submitBtn = findViewById<Button>(R.id.submit_button)
        submitBtn.setOnClickListener {
            if (counter == filmsList.size) {
                resultTextView.text = "No films left, reset"
            } else {
                Log.d("My Info", "Generate indeces")
                var idx: Int = 0
                while (idx in usedIndices) {
                    Log.d("My Info", "Loop")
                    idx = (filmsList.indices).random()
                }
                Log.d("My Info", "Index: ${idx}")
                resultTextView.text = filmsList[idx]
                usedIndices.add(idx)
                counter++
            }
        }
    }
}