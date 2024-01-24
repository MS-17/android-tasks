package com.guessnum

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity

class GameActivity: ComponentActivity() {
    var begin: Int = 0
    var end: Int = 100

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.game_activity)
        begin = intent.getIntExtra("begin", 0)
        end = intent.getIntExtra("end", 100)
        Log.d("My info", "begin = $begin, end = $end")
    }
}