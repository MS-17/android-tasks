package com.randomfilm

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.activity.ComponentActivity

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