package com.spoqa.longpresslistener

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val triggeredAction: () -> Unit = {
            Toast.makeText(
                this@MainActivity, R.string.triggered_toast, Toast.LENGTH_SHORT
            ).show()
        }

        button_1seconds.onPress(triggeredAction, 1000L)
        button_3seconds.onPress(triggeredAction, 3000L)
        button_5seconds.onPress(triggeredAction, 5000L)
    }
}
