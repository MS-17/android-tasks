package com.guessnum

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.activity.ComponentActivity

class GameActivity : ComponentActivity() {
    var begin: Int = 0
    var end: Int = 100
    var guessNum = 0
    var isEnd = false
    lateinit var questionTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.game_activity)

        // get data
        begin = intent.getIntExtra("begin", 0)
        end = intent.getIntExtra("end", 100)
        Log.d("My info", "begin = $begin, end = $end")

        // get question field
        questionTextView = findViewById(R.id.question_field)
        questionTextView.text = "Press yes to start the game"

        // set listeners
        findViewById<Button>(R.id.yes_button).setOnClickListener {
            if (!isEnd) {
                begin = guessNum
                onYesNoClick()
            } else {
                questionTextView.text = "I won"
            }
            Log.d("My info", "Begin: $begin, End: $end")
            Log.d("My info", "Yes button click")
        }
        findViewById<Button>(R.id.no_button).setOnClickListener {
            if (!isEnd) {
                end = guessNum
                onYesNoClick()
            } else {
                questionTextView.text = "I don't know"
            }
            Log.d("My info", "Begin: $begin, End: $end")
            Log.d("My info", "No button click")
        }
        onYesNoClick()
    }

    fun onYesNoClick() {
        if (end - begin > 1) {
            guessNum = (begin + end) / 2
            questionTextView.text = "Is the number greater than $guessNum?"
        } else {
            isEnd = true
            guessNum = end
            questionTextView.text = "Is it $guessNum?"
        }
    }
}
