package com.terra.design.components

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.drawable.GradientDrawable
import android.util.AttributeSet
import android.view.Gravity
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.core.widget.TextViewCompat

class TerraCheckbox @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : LinearLayout(context, attrs, defStyleAttr) {

    private val indicatorView: FrameLayout
    private val boxView: android.view.View
    private val checkView: ImageView
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
        inflate(context, R.layout.terra_checkbox, this)

        indicatorView = findViewById(R.id.terraCheckboxIndicator)
        boxView = findViewById(R.id.terraCheckboxBox)
        checkView = findViewById(R.id.terraCheckboxCheck)
        labelView = findViewById(R.id.terraCheckboxLabel)

        context.obtainStyledAttributes(attrs, R.styleable.TerraCheckbox).apply {
            checked = getBoolean(R.styleable.TerraCheckbox_android_checked, false)
            isEnabled = getBoolean(R.styleable.TerraCheckbox_android_enabled, true)
            labelView.text = getString(R.styleable.TerraCheckbox_android_text) ?: labelView.text
            showLabel = getBoolean(R.styleable.TerraCheckbox_terraCheckboxShowLabel, false)
            recycle()
        }

        checkView.setImageDrawable(AppCompatResources.getDrawable(context, R.drawable.check))
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
        val strokeColor = when {
            !isEnabled -> color(com.terra.design.tokens.R.color.terra_color_grey_400)
            else -> color(com.terra.design.tokens.R.color.terra_color_grey_500)
        }
        val fillColor = if (isEnabled) {
            color(com.terra.design.tokens.R.color.terra_color_bg_default)
        } else {
            color(com.terra.design.tokens.R.color.terra_color_bg_default)
        }
        val iconColor = if (isEnabled) {
            color(com.terra.design.tokens.R.color.terra_color_text_primary_default)
        } else {
            color(com.terra.design.tokens.R.color.terra_color_text_secondary_default)
        }
        val labelColor = if (isEnabled) {
            color(com.terra.design.tokens.R.color.terra_color_text_secondary_default)
        } else {
            color(com.terra.design.tokens.R.color.terra_color_text_secondary_default)
        }

        boxView.background = GradientDrawable().apply {
            shape = GradientDrawable.RECTANGLE
            setColor(fillColor)
            cornerRadius = dp(3).toFloat()
            setStroke(dp(2), strokeColor)
        }

        checkView.isVisible = checked
        checkView.imageTintList = ColorStateList.valueOf(iconColor)

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
