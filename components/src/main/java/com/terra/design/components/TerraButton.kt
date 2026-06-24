package com.terra.design.components

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.drawable.GradientDrawable
import android.util.AttributeSet
import android.view.Gravity
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.annotation.DrawableRes
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.core.widget.TextViewCompat

class TerraButton @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : LinearLayout(context, attrs, defStyleAttr) {

    enum class Type {
        PRIMARY,
        SECONDARY,
        OUTLINED_PRIMARY,
        OUTLINED_SECONDARY,
        DANGER,
        TEXT_BUTTON,
    }

    enum class Size {
        NORMAL,
        SMALL,
    }

    private val leftIconView: ImageView
    private val labelView: TextView
    private val rightIconView: ImageView
    private var leftIconResId: Int = 0
    private var rightIconResId: Int = 0

    var terraButtonType: Type = Type.PRIMARY
        set(value) {
            field = value
            applyStyle()
        }

    var terraButtonSize: Size = Size.NORMAL
        set(value) {
            field = value
            applyStyle()
        }

    init {
        orientation = HORIZONTAL
        gravity = Gravity.CENTER
        isClickable = true
        isFocusable = true
        inflate(context, R.layout.terra_button, this)

        leftIconView = findViewById(R.id.terraButtonLeftIcon)
        labelView = findViewById(R.id.terraButtonLabel)
        rightIconView = findViewById(R.id.terraButtonRightIcon)

        context.obtainStyledAttributes(attrs, R.styleable.TerraButton).apply {
            terraButtonType = Type.entries[getInt(R.styleable.TerraButton_terraButtonType, 0)]
            terraButtonSize = Size.entries[getInt(R.styleable.TerraButton_terraButtonSize, 0)]
            setLeftIconResource(getResourceId(R.styleable.TerraButton_terraLeftIcon, 0))
            setRightIconResource(getResourceId(R.styleable.TerraButton_terraRightIcon, 0))
            labelView.text = getText(R.styleable.TerraButton_android_text) ?: labelView.text
            recycle()
        }

        applyStyle()
    }

    override fun setEnabled(enabled: Boolean) {
        super.setEnabled(enabled)
        labelView.isEnabled = enabled
        leftIconView.isEnabled = enabled
        rightIconView.isEnabled = enabled
        applyStyle()
    }

    override fun setClickable(clickable: Boolean) {
        super.setClickable(clickable)
        isFocusable = clickable
    }

    override fun setSelected(selected: Boolean) {
        super.setSelected(selected)
        refreshDrawableState()
    }

    fun setText(text: CharSequence?) {
        labelView.text = text
    }

    fun getText(): CharSequence = labelView.text

    fun setLeftIconResource(@DrawableRes resId: Int) {
        leftIconResId = resId
        setIconResource(leftIconView, resId)
    }

    fun setRightIconResource(@DrawableRes resId: Int) {
        rightIconResId = resId
        setIconResource(rightIconView, resId)
    }

    private fun setIconResource(view: ImageView, @DrawableRes resId: Int) {
        view.isVisible = resId != 0
        view.setImageDrawable(if (resId == 0) null else AppCompatResources.getDrawable(context, resId))
        view.contentDescription = null
        applyStyle()
    }

    private fun applyStyle() {
        val isSmall = terraButtonSize == Size.SMALL
        val contentPaddingVertical = dimen(if (isTextButton()) com.terra.design.tokens.R.dimen.terra_spacing_4 else if (isSmall) com.terra.design.tokens.R.dimen.terra_spacing_4 else com.terra.design.tokens.R.dimen.terra_spacing_8)
        val contentPaddingHorizontal = dimen(if (isTextButton()) com.terra.design.tokens.R.dimen.terra_spacing_16 else com.terra.design.tokens.R.dimen.terra_spacing_32)
        val cornerRadiusPx = dimen(if (isSmall) com.terra.design.tokens.R.dimen.terra_radius_6 else com.terra.design.tokens.R.dimen.terra_radius_8)
        val iconSize = dimen(if (isSmall) com.terra.design.tokens.R.dimen.terra_size_icon_20 else com.terra.design.tokens.R.dimen.terra_size_icon_24)
        val gap = dimen(com.terra.design.tokens.R.dimen.terra_spacing_8)

        val hasLeftIcon = leftIconResId != 0
        val hasRightIcon = rightIconResId != 0

        setPaddingRelative(contentPaddingHorizontal, contentPaddingVertical, contentPaddingHorizontal, contentPaddingVertical)
        minimumHeight = dimen(
            if (isSmall) {
                com.terra.design.tokens.R.dimen.terra_size_button_height_small
            } else {
                com.terra.design.tokens.R.dimen.terra_size_button_height_normal
            },
        )

        val backgroundColor: Int?
        val strokeColor: Int?
        val contentColor: Int

        if (!isEnabled) {
            backgroundColor = if (isOutlined() || isTextButton()) null else color(com.terra.design.tokens.R.color.terra_color_bg_disabled_default)
            strokeColor = if (isOutlined()) color(com.terra.design.tokens.R.color.terra_color_border_disabled_default) else null
            contentColor = color(com.terra.design.tokens.R.color.terra_color_text_primary_disabled)
        } else {
            when (terraButtonType) {
                Type.PRIMARY -> {
                    backgroundColor = color(com.terra.design.tokens.R.color.terra_color_bg_fill_secondary_default)
                    strokeColor = null
                    contentColor = color(com.terra.design.tokens.R.color.terra_color_text_inverse_default)
                }
                Type.SECONDARY -> {
                    backgroundColor = color(com.terra.design.tokens.R.color.terra_color_bg_fill_primary_default)
                    strokeColor = null
                    contentColor = color(com.terra.design.tokens.R.color.terra_color_text_inverse_default)
                }
                Type.OUTLINED_PRIMARY -> {
                    backgroundColor = null
                    strokeColor = color(com.terra.design.tokens.R.color.terra_color_border_action_primary_default)
                    contentColor = color(com.terra.design.tokens.R.color.terra_color_text_action_primary_default)
                }
                Type.OUTLINED_SECONDARY -> {
                    backgroundColor = null
                    strokeColor = color(com.terra.design.tokens.R.color.terra_color_border_action_secondary_default)
                    contentColor = color(com.terra.design.tokens.R.color.terra_color_bg_fill_primary_default)
                }
                Type.DANGER -> {
                    backgroundColor = color(com.terra.design.tokens.R.color.terra_color_bg_fill_danger_default)
                    strokeColor = null
                    contentColor = color(com.terra.design.tokens.R.color.terra_color_text_inverse_default)
                }
                Type.TEXT_BUTTON -> {
                    backgroundColor = null
                    strokeColor = null
                    contentColor = color(com.terra.design.tokens.R.color.terra_color_text_action_primary_default)
                }
            }
        }

        background = GradientDrawable().apply {
            shape = GradientDrawable.RECTANGLE
            setColor(backgroundColor ?: android.graphics.Color.TRANSPARENT)
            cornerRadius = cornerRadiusPx.toFloat()
            if (strokeColor != null) setStroke(dp(1), strokeColor)
        }

        TextViewCompat.setTextAppearance(
            labelView,
            if (isSmall) com.terra.design.tokens.R.style.TextAppearance_Terra_Button_Small else com.terra.design.tokens.R.style.TextAppearance_Terra_Button_Medium,
        )
        labelView.setTextColor(contentColor)

        leftIconView.imageTintList = ColorStateList.valueOf(contentColor)
        rightIconView.imageTintList = ColorStateList.valueOf(contentColor)

        updateIconLayout(leftIconView, iconSize, if (hasLeftIcon) gap else 0)
        updateLabelLayout(if (hasLeftIcon) gap else 0, if (hasRightIcon) gap else 0)
        updateIconLayout(rightIconView, iconSize, 0)

        val backgroundView = this
        backgroundView.isClickable = isEnabled
        backgroundView.isFocusable = isEnabled
        backgroundView.importantForAccessibility = View.IMPORTANT_FOR_ACCESSIBILITY_YES
        backgroundView.contentDescription = labelView.text
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

    private fun isOutlined() = terraButtonType == Type.OUTLINED_PRIMARY || terraButtonType == Type.OUTLINED_SECONDARY

    private fun isTextButton() = terraButtonType == Type.TEXT_BUTTON

    private fun dimen(resId: Int) = resources.getDimensionPixelSize(resId)

    private fun color(resId: Int) = ContextCompat.getColor(context, resId)

    private fun dp(value: Int) = (value * resources.displayMetrics.density).toInt()
}
