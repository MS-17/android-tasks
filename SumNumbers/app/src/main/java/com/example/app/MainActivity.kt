package com.example.app

import android.os.Bundle
import android.os.PersistableBundle
import android.provider.ContactsContract.CommonDataKinds.StructuredName
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.ComponentActivity

class MainActivity : ComponentActivity() {

    companion object {
        var result: String = ""
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btn = findViewById<Button>(R.id.submit_button)
        btn.setOnClickListener {

            val editTextA = findViewById<EditText>(R.id.edit_textA).text.toString()
            val editTextB = findViewById<EditText>(R.id.edit_textB).text.toString()
            val resultTextView = findViewById<TextView>(R.id.result_text_view)

            if (editTextA.isBlank() || editTextB.isBlank()) {
                resultTextView.text = "Empty string"
            } else {
                result = (editTextA.toFloat() + editTextB.toFloat()).toString()
                resultTextView.text = result
                Log.d("MY INFO", "Successfully printed a result")
            }
        }

    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Log.d("MY INFO", "Execute on save instance state")
        outState.run {
            putString("RESULT_STATE", result)
        }
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        Log.d("MY INFO", "Execute on restore instance state")
        savedInstanceState.run {
            result = getString("RESULT_STATE").toString()
        }
        findViewById<TextView>(R.id.result_text_view).text = result
    }
}
