package com.guessnum

import android.os.Bundle
import androidx.activity.ComponentActivity

class GameActivity: ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.game_activity)
    }
}