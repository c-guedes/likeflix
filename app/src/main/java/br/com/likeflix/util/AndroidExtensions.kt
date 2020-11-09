package br.com.likeflix.util

import android.app.Activity
import android.content.Context
import android.view.View
import android.widget.ImageView
import androidx.annotation.ColorRes
import androidx.annotation.IdRes
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import br.com.likeflix.BASE_URL_IMG
import com.bumptech.glide.Glide
import org.jetbrains.anko.find

fun Context.getResColor(@ColorRes idColor: Int) = ContextCompat.getColor(this, idColor)

fun Activity.setStatusBarColor(@ColorRes colorId: Int) {
    window.statusBarColor = getResColor(colorId)
}

fun ImageView.loadPic(imagePath: String) {
    Glide
        .with(this)
        .load(BASE_URL_IMG + imagePath)
        .into(this)
}

fun String.toQuery(): String =
    trim().toLowerCase().split(" ").joinToString("+")

fun View.configureBackAction(activity: Activity) = setOnClickListener {
    activity.onBackPressed()
}

fun View.toggleVisibility(visible: Boolean) {
    isVisible = visible
}


inline fun <reified T : View> View.bind(@IdRes id: Int): Lazy<T> = lazy { find<T>(id) }
inline fun <reified T : View> Activity.bind(@IdRes id: Int): Lazy<T> = lazy { find<T>(id) }
inline fun <reified T : View> Fragment.bind(@IdRes id: Int): Lazy<T> =
    lazy { requireView().find<T>(id) }

inline fun <reified T : Activity> Activity.extra(key: String, defaultValue: T): Lazy<T> =
    lazy { intent.extras?.get(key) as? T ?: defaultValue }

inline fun <reified T : Any> Activity.extraOrThrow(key: String): Lazy<T> =
    lazy { intent.extras?.get(key) as T }