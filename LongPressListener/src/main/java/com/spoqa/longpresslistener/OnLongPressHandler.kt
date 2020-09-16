package com.spoqa.longpresslistener

import android.os.Handler

/**
 * Handles the long press action and invokes the onLongPressCallback
 *
 * @param onLongPressCallback Callback to be invoked after long press event
 */
class OnLongPressHandler(private val onLongPressCallback: OnLongPressCallback) {

    var hasPerformedLongPress = false

    private val handler = Handler()

    private val longPressRunnable = Runnable {
        onLongPressCallback.onPress()
        hasPerformedLongPress = true
    }

    /**
     * @param delayMilliseconds Certain duration for handling long press
     */
    fun postCheckForLongPress(delayMilliseconds: Long) {
        hasPerformedLongPress = false
        handler.postDelayed(longPressRunnable, delayMilliseconds)
    }

    fun removeLongPressCallback() {
        handler.removeCallbacks(longPressRunnable)
    }
}
