package com.terra.design.components

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.drawable.GradientDrawable
import android.util.AttributeSet
import android.view.Gravity
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.annotation.DrawableRes
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.core.widget.TextViewCompat

class TerraLabelBadge @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : LinearLayout(context, attrs, defStyleAttr) {

    enum class State {
        DEFAULT,
        SECONDARY,
        NEUTRAL,
        FAILED,
    }

    private val iconView: ImageView
    private val textView: TextView

    var state: State = State.DEFAULT
        set(value) {
            field = value
            applyStyle()
        }

    init {
        orientation = HORIZONTAL
        gravity = Gravity.CENTER_VERTICAL
        inflate(context, R.layout.terra_label_badge, this)

        iconView = findViewById(R.id.terraLabelBadgeIcon)
        textView = findViewById(R.id.terraLabelBadgeText)

        context.obtainStyledAttributes(attrs, R.styleable.TerraLabelBadge).apply {
            textView.text = getString(R.styleable.TerraLabelBadge_android_text) ?: textView.text
            state = State.entries[getInt(R.styleable.TerraLabelBadge_terraLabelBadgeState, 0)]
            setIcon(getResourceId(R.styleable.TerraLabelBadge_terraLabelBadgeIcon, 0), getBoolean(R.styleable.TerraLabelBadge_terraLabelBadgeShowIcon, true))
            recycle()
        }

        applyStyle()
    }

    fun setText(text: CharSequence?) {
        textView.text = text
    }

    fun setIcon(@DrawableRes resId: Int, visible: Boolean = true) {
        iconView.isVisible = visible && resId != 0
        iconView.setImageDrawable(if (iconView.isVisible) AppCompatResources.getDrawable(context, resId) else null)
        updateTextMargin()
        applyStyle()
    }

    private fun applyStyle() {
        val bgColor = when (state) {
            State.DEFAULT -> color(com.terra.design.tokens.R.color.terra_color_bg_fill_secondary_default)
            State.SECONDARY -> color(com.terra.design.tokens.R.color.terra_color_teal_700)
            State.NEUTRAL -> color(com.terra.design.tokens.R.color.terra_color_grey_500)
            State.FAILED -> color(com.terra.design.tokens.R.color.terra_color_bg_fill_danger_default)
        }

        setPaddingRelative(dimen(com.terra.design.tokens.R.dimen.terra_spacing_8), 0, dimen(com.terra.design.tokens.R.dimen.terra_spacing_8), 0)
        minimumHeight = dimen(com.terra.design.tokens.R.dimen.terra_spacing_20)
        background = GradientDrawable().apply {
            shape = GradientDrawable.RECTANGLE
            setColor(bgColor)
            cornerRadius = dimen(com.terra.design.tokens.R.dimen.terra_spacing_4).toFloat()
        }

        TextViewCompat.setTextAppearance(textView, com.terra.design.tokens.R.style.TextAppearance_Terra_Caption)
        textView.setTextColor(color(com.terra.design.tokens.R.color.terra_color_text_inverse_default))
        iconView.imageTintList = ColorStateList.valueOf(color(com.terra.design.tokens.R.color.terra_color_icon_inverse_default))
    }

    private fun updateTextMargin() {
        textView.layoutParams = (textView.layoutParams as LayoutParams).apply {
            marginStart = if (iconView.isVisible) dp(10) else 0
        }
    }

    private fun dimen(resId: Int) = resources.getDimensionPixelSize(resId)

    private fun color(resId: Int) = ContextCompat.getColor(context, resId)

    private fun dp(value: Int) = (value * resources.displayMetrics.density).toInt()
}
