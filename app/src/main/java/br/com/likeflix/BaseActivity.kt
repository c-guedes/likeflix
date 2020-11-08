package br.com.likeflix

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import br.com.likeflix.util.setStatusBarColor

abstract class BaseActivity(
    @LayoutRes private val layoutId: Int? = null,
    private val statusBarColor: Int? = null
) : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        layoutId?.let { setContentView(it) }
        statusBarColor?.let { setStatusBarColor(statusBarColor) }
    }
}