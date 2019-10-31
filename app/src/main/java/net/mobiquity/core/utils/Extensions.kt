package net.mobiquity.core.utils

import android.app.Activity
import android.net.Uri
import android.view.Gravity
import android.view.View
import com.facebook.drawee.backends.pipeline.Fresco
import com.facebook.drawee.view.SimpleDraweeView
import com.facebook.imagepipeline.request.ImageRequestBuilder
import com.tapadoo.alerter.OnHideAlertListener
import net.mobiquity.BuildConfig
import net.mobiquity.core.utils.widgets.CustomAlerter

fun isDevelopmentDebug(block: () -> Unit) {
    if (BuildConfig.DEBUG && BuildConfig.FLAVOR.contains("develop", ignoreCase = true)) {
        block.invoke()
    }
}

@Suppress("UNUSED")
fun loadImage(imgUrl: String, imageView: SimpleDraweeView) {
    val uri = Uri.parse(imgUrl)
    imageView.hierarchy.setProgressBarImage(CircleProgressDrawable())
    val imageRequest = ImageRequestBuilder
        .newBuilderWithSource(uri)
        .build()
    imageView.controller = Fresco.newDraweeControllerBuilder()
        .setOldController(imageView.controller)
        .setImageRequest(imageRequest)
        .setAutoPlayAnimations(true)
        .build()

}

fun Activity?.showAlert(text: String, color: Int) {
    this.showAlert(
        text,
        3000,
        color,
        null,
        null
    )
}

fun Activity?.showAlert(
    text: String,
    duration: Int,
    color: Int,
    onHideAlertListener: OnHideAlertListener?,
    onClickListener: View.OnClickListener?
) {
    if (this == null) return

    val alerter = CustomAlerter.create(this)
        .hideIcon()
        .setContentGravity(Gravity.CENTER)
        .setTitle(text)
        .setBackgroundColor(color)
        .setDuration(duration)

    onHideAlertListener?.let { alerter.setOnHideListener(onHideAlertListener) }

    alerter.setOnClickListener(View.OnClickListener { view ->
        alerter.hide()
        onClickListener?.let { onClickListener.onClick(view) }
    })

    alerter.show()
}