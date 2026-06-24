package com.terra.design.components

import android.content.Context
import android.graphics.drawable.GradientDrawable
import android.util.AttributeSet
import android.view.Gravity
import android.view.View
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.core.widget.TextViewCompat

class TerraRadioButton @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : LinearLayout(context, attrs, defStyleAttr) {

    private val indicatorView: FrameLayout
    private val outerView: View
    private val innerView: View
    private val labelView: TextView

    var checked: Boolean = false
        set(value) {
            field = value
            applyStyle()
        }

    var showLabel: Boolean = false
        set(value) {
            field = value
            labelView.isVisible = value
            applyStyle()
        }

    init {
        orientation = HORIZONTAL
        gravity = Gravity.CENTER_VERTICAL
        inflate(context, R.layout.terra_radio_button, this)

        indicatorView = findViewById(R.id.terraRadioButtonIndicator)
        outerView = findViewById(R.id.terraRadioButtonOuter)
        innerView = findViewById(R.id.terraRadioButtonInner)
        labelView = findViewById(R.id.terraRadioButtonLabel)

        context.obtainStyledAttributes(attrs, R.styleable.TerraRadioButton).apply {
            checked = getBoolean(R.styleable.TerraRadioButton_android_checked, false)
            isEnabled = getBoolean(R.styleable.TerraRadioButton_android_enabled, true)
            labelView.text = getString(R.styleable.TerraRadioButton_android_text) ?: labelView.text
            showLabel = getBoolean(R.styleable.TerraRadioButton_terraRadioButtonShowLabel, false)
            recycle()
        }

        applyStyle()
    }

    override fun setEnabled(enabled: Boolean) {
        super.setEnabled(enabled)
        applyStyle()
    }

    fun setText(text: CharSequence?) {
        labelView.text = text
    }

    private fun applyStyle() {
        val outerStrokeColor = when {
            !isEnabled -> color(com.terra.design.tokens.R.color.terra_color_grey_400)
            checked -> color(com.terra.design.tokens.R.color.terra_color_bg_fill_primary_default)
            else -> color(com.terra.design.tokens.R.color.terra_color_grey_500)
        }
        val innerFillColor = if (isEnabled) {
            if (checked) color(com.terra.design.tokens.R.color.terra_color_bg_fill_primary_default) else color(com.terra.design.tokens.R.color.terra_color_grey_400)
        } else {
            color(com.terra.design.tokens.R.color.terra_color_grey_400)
        }
        val labelColor = when {
            !isEnabled && checked -> color(com.terra.design.tokens.R.color.terra_color_text_secondary_default)
            !isEnabled -> color(com.terra.design.tokens.R.color.terra_color_text_secondary_default)
            else -> color(com.terra.design.tokens.R.color.terra_color_text_secondary_default)
        }

        outerView.background = GradientDrawable().apply {
            shape = GradientDrawable.OVAL
            setColor(android.graphics.Color.TRANSPARENT)
            setStroke(dp(2), outerStrokeColor)
        }

        innerView.isVisible = checked
        innerView.background = GradientDrawable().apply {
            shape = GradientDrawable.OVAL
            setColor(innerFillColor)
        }

        TextViewCompat.setTextAppearance(
            labelView,
            if (checked) com.terra.design.tokens.R.style.TextAppearance_Terra_Body_Medium_Bold else com.terra.design.tokens.R.style.TextAppearance_Terra_Body_Medium_Regular,
        )
        labelView.setTextColor(labelColor)
        labelView.isVisible = showLabel
    }

    private fun color(resId: Int) = ContextCompat.getColor(context, resId)

    private fun dp(value: Int) = (value * resources.displayMetrics.density).toInt()
}
