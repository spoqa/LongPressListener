package com.spoqa.longpresslistener

import android.view.MotionEvent
import android.view.View

/**
 * Called when a long press event with certain dutation is dispatched to a view
 *
 * @param onLongPressCallback Callback to be invoked after long press event
 * @param duration Certain duration for handling long press after delay
 *                 (Default value is 3 seconds)
 */
class OnLongPressListener @JvmOverloads constructor(
    onLongPressCallback: OnLongPressCallback,
    private val duration: Long = 3000L
) : View.OnTouchListener {

    private var longClickActionMoveOutsideFlag = false
    private val onLongPressHandler = OnLongPressHandler(onLongPressCallback)

    override fun onTouch(v: View, event: MotionEvent): Boolean {
        onLongPressHandler.apply {
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    v.isPressed = true
                    hasPerformedLongPress = false
                    postCheckForLongPress(duration)
                    longClickActionMoveOutsideFlag = false
                }
                MotionEvent.ACTION_CANCEL -> {
                    v.isPressed = false
                    if (!hasPerformedLongPress) removeLongPressCallback()
                }
                MotionEvent.ACTION_MOVE -> {
                    // Don't invoke onLongPressCallback when the touch area
                    // is moved outside current view
                    if (event.x < 0 || event.y < 0 ||
                        event.x > v.measuredWidth || event.y > v.measuredHeight) {
                        v.isPressed = false
                        longClickActionMoveOutsideFlag = true
                        if (!hasPerformedLongPress) {
                            removeLongPressCallback()
                        }
                    } else {
                        longClickActionMoveOutsideFlag = false
                    }
                }
                MotionEvent.ACTION_UP -> {
                    v.isPressed = false
                    if (!hasPerformedLongPress) {
                        removeLongPressCallback()
                        // Same as onClick Action
                        if (!longClickActionMoveOutsideFlag) {
                            v.performClick()
                        }
                    }
                }
            }
        }
        return true
    }
}
