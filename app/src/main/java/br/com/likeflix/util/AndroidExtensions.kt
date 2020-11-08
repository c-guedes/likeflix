package br.com.likeflix.util

import android.app.Activity
import android.content.Context
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat

fun Context.getResColor(@ColorRes idColor: Int) = ContextCompat.getColor(this, idColor)

fun Activity.setStatusBarColor(@ColorRes colorId: Int) {
        window.statusBarColor = getResColor(colorId)
}
