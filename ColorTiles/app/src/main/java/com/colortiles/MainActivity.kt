package com.colortiles

import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.compose.runtime.referentialEqualityPolicy
import androidx.core.content.ContextCompat

data class Coordinates(val x: Int, val y: Int)

class MainActivity : ComponentActivity() {

    private lateinit var tiles: Array<Array<View>>
    private var solve = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        tiles = Array(4) { row ->
            Array(4) { col ->
                findViewById<View>(resources.getIdentifier("t$row$col", "id", packageName))
            }
        }
        Log.d("My info", "Tiles size: ${tiles.size.toString()}, ${tiles[0].size.toString()}")
        if (solve) {
            solvePuzzle()
            Log.d("My info", "Auto win")
        }
    }

    private fun getCoordinatesFromString(s: String): Coordinates {
        Log.d("My info", "Chosen tile: $s")
        return Coordinates(s[0] - '0', s[1] - '0')
    }

    private fun changeTileColor(view: View) {
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
        Log.d("My info", "Coordinates: $coordinates")
        changeTileColor(view)
        for (i in 0..3) {
            changeTileColor(tiles[coordinates.x][i])
            changeTileColor(tiles[i][coordinates.y])
        }
        if (checkVictory()) {
            if (!solve) {
                Log.d("My info", "Won")
            }
            Toast.makeText(this, "Solved, congratulations!", Toast.LENGTH_SHORT).show()
        }
    }

    private fun getTileColor(view: View): ColorDrawable {
        return view.background as ColorDrawable
    }

    private fun checkVictory(): Boolean {
        val leadingTile = getTileColor(tiles[0][0]).color
        for ( i in 0..3) {
            for (j in 0..3) {
                if (getTileColor(tiles[i][j]).color != leadingTile) {
                    return false
                }
            }
        }
        return true
    }

    private fun solvePuzzle() {
        Log.d("My info", "Solving puzzle")
        val rows = tiles.size
        val cols = tiles[0].size
        while (!checkVictory()) {
            for (i in 0 until rows) {
                for (j in 0 until cols) {
                    if (getTileColor(tiles[i][j]).color == resources.getColor(R.color.green)) {
                        tiles[i][j].callOnClick()
                    }
                }
            }
        }
        Log.d("My info", "Solved")
    }
}

