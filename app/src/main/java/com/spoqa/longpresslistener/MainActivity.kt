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

        button_1seconds.setOnTouchListener(OnLongPressListener(
            object : OnLongPressCallback {
                override fun onPress() {
                    triggeredAction.invoke()
                }
            },
            duration = 1000L
        ))

        button_3seconds.setOnTouchListener(OnLongPressListener(
            object : OnLongPressCallback {
                override fun onPress() {
                    triggeredAction.invoke()
                }
            },
            duration = 3000L
        ))

        button_5seconds.setOnTouchListener(OnLongPressListener(
            object : OnLongPressCallback {
                override fun onPress() {
                    triggeredAction.invoke()
                }
            },
            duration = 5000L
        ))
    }
}
