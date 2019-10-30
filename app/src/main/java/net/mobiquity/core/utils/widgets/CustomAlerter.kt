package net.mobiquity.core.utils.widgets

import android.app.Activity
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.util.Log
import android.view.View
import android.view.ViewGroup

import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat

import com.tapadoo.alerter.Alert
import com.tapadoo.alerter.OnHideAlertListener
import com.tapadoo.alerter.OnShowAlertListener
import timber.log.Timber

import java.lang.ref.WeakReference

class CustomAlerter
/**
 * Constructor
 */
private constructor() { //Utility classes should not be instantiated

    private var alert: Alert? = null

    /**
     * Get the enclosing Decor View
     *
     * @return The Decor View of the Activity the CustomAlerter was called from
     */
    private val activityDecorView: ViewGroup?
        get() {
            var decorView: ViewGroup? = null
            activity {
                if (it.window.decorView is ViewGroup) {
                    decorView = it.window.decorView as? ViewGroup
                }
            }
            return decorView
        }

    /**
     * Shows the Alert, after it's built
     *
     * @return An Alert object check can be altered or hidden
     */
    fun show(): Alert? {
        //This will get the Activity Window's DecorView
        activity {
            it.runOnUiThread {
                //Add the new Alert to the View Hierarchy
                val decorView = activityDecorView
                if (decorView != null && alert!!.parent == null) {
                    decorView.addView(alert)
                }
            }
        }
        return alert
    }

    /**
     * Hides currently showing alert.
     */
    fun hide() {
        if (alert != null) {
            alert!!.hide()
        }
    }

    /**
     * Sets the title of the Alert
     *
     * @param titleId Title String Resource
     * @return This CustomAlerter
     */
    @Suppress("UNUSED")
    fun setTitle(@StringRes titleId: Int): CustomAlerter {
        if (alert != null) {
            alert!!.setTitle(titleId)
        }

        return this
    }

    /**
     * Set Title of the Alert
     *
     * @param title Title as a String
     * @return This CustomAlerter
     */
    fun setTitle(title: String): CustomAlerter {
        if (alert != null) {
            alert!!.setTitle(title)
        }

        return this
    }

    /**
     * Set Gravity of the Alert
     *
     * @param gravity Gravity of Alert
     * @return This CustomAlerter
     */
    fun setContentGravity(gravity: Int): CustomAlerter {
        if (alert != null) {
            alert!!.contentGravity = gravity
        }

        return this
    }

    /**
     * Sets the Alert Text
     *
     * @param textId Text String Resource
     * @return This CustomAlerter
     */
    @Suppress("UNUSED")
    fun setText(@StringRes textId: Int): CustomAlerter {
        if (alert != null) {
            alert!!.setText(textId)
        }

        return this
    }

    /**
     * Sets the Alert Text
     *
     * @param text String of Alert Text
     * @return This CustomAlerter
     */
    @Suppress("UNUSED")
    fun setText(text: String): CustomAlerter {
        if (alert != null) {
            alert!!.setText(text)
        }

        return this
    }

    /**
     * Set the Alert's Background Colour
     *
     * @param colorResId Colour Resource Id
     * @return This CustomAlerter
     */
    fun setBackgroundColor(@ColorRes colorResId: Int): CustomAlerter {
        activity { alert?.setAlertBackgroundColor(ContextCompat.getColor(it, colorResId)) }
        return this
    }

    /**
     * Set the Alert's Background Drawable
     *
     * @param drawable Drawable
     * @return This CustomAlerter
     */
    @Suppress("UNUSED")
    fun setBackgroundDrawable(drawable: Drawable): CustomAlerter {
        if (alert != null) {
            alert!!.setAlertBackgroundDrawable(drawable)
        }

        return this
    }

    /**
     * Set the Alert's Background Drawable Resource
     *
     * @param drawableResId Drawable Resource Id
     * @return This CustomAlerter
     */
    @Suppress("UNUSED")
    fun setBackgroundResource(@DrawableRes drawableResId: Int): CustomAlerter {
        if (alert != null) {
            alert!!.setAlertBackgroundResource(drawableResId)
        }

        return this
    }

    /**
     * Set the Alert's Icon
     *
     * @param iconId The Drawable's Resource Idw
     * @return This CustomAlerter
     */
    @Suppress("UNUSED")
    fun setIcon(@DrawableRes iconId: Int): CustomAlerter {
        if (alert != null) {
            alert!!.setIcon(iconId)
        }

        return this
    }

    /**
     * Set the Alert's Icon
     *
     * @param bitmap The Bitmap object to use for the icon.
     * @return This CustomAlerter
     */
    @Suppress("UNUSED")
    fun setIcon(bitmap: Bitmap): CustomAlerter {
        if (alert != null) {
            alert!!.setIcon(bitmap)
        }

        return this
    }

    /**
     * Hide the Icon
     *
     * @return This CustomAlerter
     */
    fun hideIcon(): CustomAlerter {
        if (alert != null) {
            alert!!.icon.visibility = View.GONE
        }

        return this
    }

    /**
     * Set the onClickListener for the Alert
     *
     * @param onClickListener The onClickListener for the Alert
     * @return This CustomAlerter
     */
    fun setOnClickListener(onClickListener: View.OnClickListener): CustomAlerter {
        if (alert != null) {
            alert!!.setOnClickListener(onClickListener)
        }

        return this
    }

    /**
     * Set the on screen duration of the alert
     *
     * @param milliseconds The duration in milliseconds
     * @return This CustomAlerter
     */
    fun setDuration(milliseconds: Int): CustomAlerter {
        if (alert != null) {
            alert!!.duration = milliseconds.toLong()
        }
        return this
    }

    /**
     * Enable or Disable Icon Pulse Animations
     *
     * @param pulse True if the icon should pulse
     * @return This CustomAlerter
     */
    @Suppress("UNUSED")
    fun enableIconPulse(pulse: Boolean): CustomAlerter {
        if (alert != null) {
            alert!!.pulseIcon(pulse)
        }
        return this
    }

    /**
     * Set whether to show the icon in the alert or not
     *
     * @param showIcon True to show the icon, false otherwise
     * @return This CustomAlerter
     */
    @Suppress("UNUSED")
    fun showIcon(showIcon: Boolean): CustomAlerter {
        if (alert != null) {
            alert!!.showIcon(showIcon)
        }
        return this
    }

    /**
     * Enable or disable infinite duration of the alert
     *
     * @param infiniteDuration True if the duration of the alert is infinite
     * @return This CustomAlerter
     */
    @Suppress("UNUSED")
    fun enableInfiniteDuration(infiniteDuration: Boolean): CustomAlerter {
        if (alert != null) {
            alert!!.setEnableInfiniteDuration(infiniteDuration)
        }
        return this
    }

    /**
     * Sets the Alert Shown Listener
     *
     * @param listener OnShowAlertListener of Alert
     * @return This CustomAlerter
     */
    @Suppress("UNUSED")
    fun setOnShowListener(listener: OnShowAlertListener): CustomAlerter {
        if (alert != null) {
            alert!!.setOnShowListener(listener)
        }
        return this
    }

    /**
     * Sets the Alert Hidden Listener
     *
     * @param listener OnHideAlertListener of Alert
     * @return This CustomAlerter
     */
    fun setOnHideListener(listener: OnHideAlertListener): CustomAlerter {
        if (alert != null) {
            alert!!.setOnHideListener(listener)
        }
        return this
    }

    /**
     * Enable or Disable Vibration
     *
     * @param enable True to enable, False to disable
     * @return This CustomAlerter
     */
    @Suppress("UNUSED")
    fun enableVibration(enable: Boolean): CustomAlerter {
        if (alert != null) {
            alert!!.setVibrationEnabled(enable)
        }

        return this
    }

    /**
     * Disable touch events outside of the Alert
     *
     * @return This CustomAlerter
     */
    @Suppress("UNUSED")
    fun disableOutsideTouch(): CustomAlerter {
        if (alert != null) {
            alert!!.disableOutsideTouch()
        }

        return this
    }

    /**
     * Creates a weak reference to the calling Activity
     *
     * @param activity The calling Activity
     */
    private fun setActivity(activity: Activity) {
        activityWeakReference = WeakReference(activity)
    }

    private fun activity(block: (activity: Activity) -> Unit) =
        activityWeakReference?.get()?.let { block(it) }


    companion object {
        private var activityWeakReference: WeakReference<Activity>? = null

        /**
         * Creates the Alert, and maintains a reference to the calling Activity
         *
         * @param activity The calling Activity
         * @return This CustomAlerter
         */
        fun create(activity: Activity): CustomAlerter {

            val alerter = CustomAlerter()

            //Clear Current Alert, if one is Active
            clearCurrent(activity)

            alerter.setActivity(activity)
            alerter.alert = Alert(activity)

            return alerter
        }

        /**
         * Cleans up the currently showing alert view, if one is present
         */
        private fun clearCurrent(activity: Activity) {
            try {
                val decorView = activity.window.decorView as ViewGroup

                //Find all Alert Views in Parent layout
                for (i in 0 until decorView.childCount) {
                    val childView =
                        if (decorView.getChildAt(i) is Alert) decorView.getChildAt(i) as Alert else null
                    if (childView != null && childView.windowToken != null) {
                        ViewCompat.animate(childView).alpha(0f)
                            .withEndAction(
                                getRemoveViewRunnable(
                                    childView
                                )
                            )
                    }
                }

            } catch (ex: Exception) {
                Timber.e(Log.getStackTraceString(ex))
            }

        }

        private fun getRemoveViewRunnable(childView: Alert?): Runnable {
            return Runnable {
                if (childView != null && childView.parent != null)
                    (childView.parent as ViewGroup).removeView(childView)

            }
        }
    }
}