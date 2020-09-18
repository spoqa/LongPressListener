package com.spoqa.longpresslistener

import android.view.View

fun View.onPress(action: () -> Unit, duration: Long = 3000L) {
    setOnTouchListener(OnLongPressListener(object : OnLongPressCallback {
        override fun onPress() {
            action.invoke()
        }
    }, duration))
}
