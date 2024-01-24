package com.guessnum

import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.activity.ComponentActivity

class GameActivity: ComponentActivity() {
    var begin: Int = 0
    var end: Int = 100

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.game_activity)

        // get data
        begin = intent.getIntExtra("begin", 0)
        end = intent.getIntExtra("end", 100)
        Log.d("My info", "begin = $begin, end = $end")

        // set listeners
        findViewById<Button>(R.id.yes_button).setOnClickListener {
            Log.d("My info", "Yes button click")
        }
        findViewById<Button>(R.id.no_button).setOnClickListener {
            Log.d("My info", "No button click")
        }
    }
}