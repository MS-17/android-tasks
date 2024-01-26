package com.guessnum

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.ComponentActivity

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById<Button>(R.id.guess_button).setOnClickListener {
            onGuessBtnClick()
        }
    }

    fun onGuessBtnClick() {
        // onclick listener function for a guess button
        val gameActivity = Intent(this, GameActivity::class.java)
        val begin = findViewById<EditText>(R.id.begin).text.toString().toInt()
        val end = findViewById<EditText>(R.id.end).text.toString().toInt()
        gameActivity.putExtra("begin", begin)
        gameActivity.putExtra("end", end)
        startActivity(gameActivity)
    }
}
