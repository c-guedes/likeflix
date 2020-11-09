package br.com.likeflix.customview

import android.app.Activity
import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isVisible
import br.com.likeflix.R
import br.com.likeflix.util.bind
import br.com.likeflix.util.configureBackAction

class SimpleHeader @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private val tvTitle: TextView by bind(R.id.tvHeaderTitle)
    private val btBack: ImageView by bind(R.id.btBackButton)

    private var title: String
        get() = tvTitle.text.toString()
        set(value) {
            tvTitle.text = value
        }

    private var backIsVisible: Boolean
        get() = btBack.isVisible
        set(value) {
            btBack.isVisible = value
        }

    init {
        val inflater = LayoutInflater.from(context)
        inflater.inflate(R.layout.custom_header, this, true)
        attrs?.let { attributeSet ->
            context.theme.obtainStyledAttributes(
                attributeSet,
                R.styleable.SimpleHeader,
                defStyleAttr,
                0
            )
                .apply { initAttrs(this) }
                .also { it.recycle() }
        }
        setupListener()
    }

    fun updateHeader(headerTitle: String) {
        title = headerTitle
    }

    private fun setupListener() {
        btBack.run {
            (context as? Activity)?.let { configureBackAction(it) }
        }
    }

    private fun initAttrs(typedAttrs: TypedArray) {
        with(typedAttrs) {
            (0 until indexCount).forEach { position ->
                when (val index = getIndex(position)) {
                    R.styleable.SimpleHeader_headerTitle -> {
                        title = getString(index) ?: DEFAULT_TEXT
                    }
                    R.styleable.SimpleHeader_showBackButton -> {
                        backIsVisible = getBoolean(index, true)
                    }
                }
            }
        }
    }

    companion object {
        const val DEFAULT_TEXT = ""
    }
}