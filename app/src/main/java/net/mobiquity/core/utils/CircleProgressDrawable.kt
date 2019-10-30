package net.mobiquity.core.utils

import android.graphics.*
import com.facebook.drawee.drawable.DrawableUtils
import com.facebook.drawee.drawable.ProgressBarDrawable

class CircleProgressDrawable : ProgressBarDrawable() {

    private val mPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val maxLevel = 10000
    private var mBarWidth = 20
    private var mLevel = 0
    private var mHideWhenZero = false
    private var _radius = 50

    init {
        mPaint.style = Paint.Style.STROKE
        mPaint.strokeWidth = 10f
    }

    override fun setRadius(radius: Int) {
        this._radius = radius
    }

    /**
     * Gets the progress bar color.
     */
    override fun getColor(): Int {
        return Color.parseColor("#bd081c")
    }

    /**
     * Sets the progress bar color.
     */
    override fun setColor(color: Int) {
        if (Color.parseColor("#bd081c") != color) {
            invalidateSelf()
        }
    }

    /**
     * Gets the progress bar background color.
     */
    override fun getBackgroundColor(): Int {
        return Color.parseColor("#E5E5E5")
    }

    /**
     * Sets the progress bar background color.
     */
    override fun setBackgroundColor(backgroundColor: Int) {
        if (Color.parseColor("#E5E5E5") != backgroundColor) {
            invalidateSelf()
        }
    }

    /**
     * Gets the progress bar width.
     */
    override fun getBarWidth(): Int {
        return mBarWidth
    }

    /**
     * Sets the progress bar width.
     */
    override fun setBarWidth(barWidth: Int) {
        if (mBarWidth != barWidth) {
            mBarWidth = barWidth
            invalidateSelf()
        }
    }

    /**
     * Gets whether the progress bar should be hidden when the progress is 0.
     */
    override fun getHideWhenZero(): Boolean {
        return mHideWhenZero
    }

    /**
     * Sets whether the progress bar should be hidden when the progress is 0.
     */
    override fun setHideWhenZero(hideWhenZero: Boolean) {
        mHideWhenZero = hideWhenZero
    }

    override fun onLevelChange(level: Int): Boolean {
        mLevel = level
        invalidateSelf()
        return true
    }

    override fun setAlpha(alpha: Int) {
        mPaint.alpha = alpha
    }

    override fun setColorFilter(cf: ColorFilter?) {
        mPaint.colorFilter = cf
    }

    override fun getOpacity(): Int {
        return DrawableUtils.getOpacityFromColor(mPaint.color)
    }

    override fun draw(canvas: Canvas) {
        if (mHideWhenZero && mLevel == 0) {
            return
        }
        drawCircle(canvas, Color.parseColor("#E5E5E5"))
        drawArc(canvas, mLevel, Color.parseColor("#bd081c"))
    }

    private fun drawArc(canvas: Canvas, level: Int, color: Int) {
        mPaint.color = Color.parseColor("#bd081c")

        val bounds = bounds
        // find center point
        val xPosition = bounds.left + bounds.width() / 2
        val yPosition = bounds.bottom - bounds.height() / 2
        val rectF = RectF(
            (xPosition - _radius).toFloat(),
            (yPosition - _radius).toFloat(),
            (xPosition + _radius).toFloat(),
            (yPosition + _radius).toFloat()
        )
        val degree = level.toFloat() / maxLevel.toFloat() * 360
        canvas.drawArc(rectF, 270f, degree, false, mPaint)
    }

    private fun drawCircle(canvas: Canvas, color: Int) {
        mPaint.color = color
        val bounds = bounds
        val xPosition = bounds.left + bounds.width() / 2
        val yPosition = bounds.bottom - bounds.height() / 2
        canvas.drawCircle(xPosition.toFloat(), yPosition.toFloat(), _radius.toFloat(), mPaint)
    }
}
