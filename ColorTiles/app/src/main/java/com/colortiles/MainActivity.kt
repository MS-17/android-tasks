package com.colortiles

import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.ComponentActivity
import androidx.core.content.ContextCompat

data class Coordinates(val x: Int, val y: Int)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
    }

    private fun getCoordinatesFromString(s: String): Coordinates {
        Log.d("My info", "Chosen tile: $s")
        return Coordinates(s[0].toInt(), s[1].toInt())
    }

    private fun changeTilesColor(view: View) {
        Log.d("My info", "Changing tiles color")
        val redColor = ContextCompat.getColor(this, R.color.red)
        val greenColor = ContextCompat.getColor(this, R.color.green)
        val drawable = view.background as ColorDrawable
        if (drawable.color == redColor) {
            view.setBackgroundColor(greenColor)
        } else {
            view.setBackgroundColor(redColor)
        }
        Log.d("My info", "Background after changing color")
    }

    fun onTileClick(view: View) {
        val coordinates = getCoordinatesFromString(view.tag.toString())
        changeTilesColor(view)
    }
}

