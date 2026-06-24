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

class TerraChip @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : LinearLayout(context, attrs, defStyleAttr) {

    private val leftIconView: ImageView
    private val labelView: TextView
    private val rightIconView: ImageView
    private val counterView: TextView

    var terraChipSelected: Boolean = false
        set(value) {
            field = value
            applyStyle()
        }

    init {
        orientation = HORIZONTAL
        gravity = Gravity.CENTER_VERTICAL
        inflate(context, R.layout.terra_chip, this)

        leftIconView = findViewById(R.id.terraChipLeftIcon)
        labelView = findViewById(R.id.terraChipLabel)
        rightIconView = findViewById(R.id.terraChipRightIcon)
        counterView = findViewById(R.id.terraChipCounter)

        context.obtainStyledAttributes(attrs, R.styleable.TerraChip).apply {
            labelView.text = getText(R.styleable.TerraChip_android_text) ?: labelView.text
            terraChipSelected = getBoolean(R.styleable.TerraChip_terraChipSelected, false)
            setOptionalIcon(leftIconView, getResourceId(R.styleable.TerraChip_terraChipLeftIcon, 0), getBoolean(R.styleable.TerraChip_terraChipShowLeftIcon, false))
            setOptionalIcon(rightIconView, getResourceId(R.styleable.TerraChip_terraChipRightIcon, 0), getBoolean(R.styleable.TerraChip_terraChipShowRightIcon, false))
            counterView.isVisible = getBoolean(R.styleable.TerraChip_terraChipHasCounter, false)
            counterView.text = getString(R.styleable.TerraChip_terraChipCounterText) ?: counterView.text
            recycle()
        }

        applyStyle()
    }

    fun setText(text: CharSequence?) {
        labelView.text = text
    }

    fun setLeftIconResource(@DrawableRes resId: Int) {
        setOptionalIcon(leftIconView, resId, true)
    }

    fun setRightIconResource(@DrawableRes resId: Int) {
        setOptionalIcon(rightIconView, resId, true)
    }

    private fun setOptionalIcon(view: ImageView, @DrawableRes resId: Int, visible: Boolean) {
        view.isVisible = visible && resId != 0
        view.setImageDrawable(if (view.isVisible) AppCompatResources.getDrawable(context, resId) else null)
    }

    private fun applyStyle() {
        val horizontalPadding = dimen(com.terra.design.tokens.R.dimen.terra_spacing_16)
        val verticalPadding = dimen(com.terra.design.tokens.R.dimen.terra_spacing_8)
        val radius = dimen(com.terra.design.tokens.R.dimen.terra_radius_8)
        val gap = dimen(com.terra.design.tokens.R.dimen.terra_spacing_4)
        val iconSize = dimen(com.terra.design.tokens.R.dimen.terra_size_icon_20)

        setPaddingRelative(horizontalPadding, verticalPadding, horizontalPadding, verticalPadding)
        minimumHeight = 0

        val strokeColor = if (terraChipSelected) {
            color(com.terra.design.tokens.R.color.terra_color_border_action_primary_default)
        } else {
            color(com.terra.design.tokens.R.color.terra_color_grey_500)
        }
        val contentColor = if (terraChipSelected) {
            color(com.terra.design.tokens.R.color.terra_color_text_action_primary_default)
        } else {
            color(com.terra.design.tokens.R.color.terra_color_grey_500)
        }
        val backgroundColor = if (terraChipSelected) {
            color(com.terra.design.tokens.R.color.terra_color_teal_50)
        } else {
            android.graphics.Color.TRANSPARENT
        }

        background = GradientDrawable().apply {
            shape = GradientDrawable.RECTANGLE
            setColor(backgroundColor)
            cornerRadius = radius.toFloat()
            setStroke(dp(1), strokeColor)
        }

        TextViewCompat.setTextAppearance(
            labelView,
            if (terraChipSelected) com.terra.design.tokens.R.style.TextAppearance_Terra_Body_Small_Bold else com.terra.design.tokens.R.style.TextAppearance_Terra_Body_Small_Regular,
        )
        labelView.setTextColor(contentColor)
        leftIconView.imageTintList = ColorStateList.valueOf(contentColor)
        rightIconView.imageTintList = ColorStateList.valueOf(contentColor)

        updateIconLayout(leftIconView, iconSize, if (leftIconView.isVisible) gap else 0)
        updateLabelLayout(if (leftIconView.isVisible) gap else 0, if (rightIconView.isVisible || counterView.isVisible) gap else 0)
        updateIconLayout(rightIconView, iconSize, if (counterView.isVisible) gap else 0)
        updateCounterStyle(gap)
    }

    private fun updateIconLayout(view: ImageView, size: Int, marginEnd: Int) {
        view.layoutParams = LayoutParams(size, size).apply {
            this.marginEnd = marginEnd
        }
    }

    private fun updateLabelLayout(marginStart: Int, marginEnd: Int) {
        labelView.layoutParams = LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT).apply {
            this.marginStart = marginStart
            this.marginEnd = marginEnd
        }
    }

    private fun updateCounterStyle(marginStart: Int) {
        counterView.layoutParams = LayoutParams(dimen(com.terra.design.tokens.R.dimen.terra_spacing_16), dimen(com.terra.design.tokens.R.dimen.terra_spacing_16)).apply {
            this.marginStart = marginStart
        }
        counterView.background = GradientDrawable().apply {
            shape = GradientDrawable.OVAL
            setColor(color(com.terra.design.tokens.R.color.terra_color_bg_fill_danger_default))
        }
        counterView.typeface = androidx.core.content.res.ResourcesCompat.getFont(context, com.terra.design.tokens.R.font.jenius_sans_bold)
        counterView.textSize = 12f
    }

    private fun dimen(resId: Int) = resources.getDimensionPixelSize(resId)

    private fun color(resId: Int) = ContextCompat.getColor(context, resId)

    private fun dp(value: Int) = (value * resources.displayMetrics.density).toInt()
}
