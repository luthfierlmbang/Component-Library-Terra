package com.terra.design.components

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.drawable.GradientDrawable
import android.util.AttributeSet
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.annotation.DrawableRes
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.core.widget.TextViewCompat

class TerraTextField @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : LinearLayout(context, attrs, defStyleAttr) {

    enum class Type {
        DEFAULT,
        TEXT_PREFIX,
        TEXT_SUFFIX,
        TEXT_PREFIX_ICON_PREFIX,
    }

    enum class State {
        DEFAULT,
        ACTIVE,
        FILLED,
        ERROR,
        DISABLED,
    }

    private val labelView: TextView
    private val containerView: LinearLayout
    private val leftIconView: ImageView
    private val prefixTextView: TextView
    private val prefixIconView: ImageView
    private val dividerView: View
    private val inputView: EditText
    private val suffixTextView: TextView
    private val rightIconView: ImageView
    private val helperView: TextView

    var terraTextFieldType: Type = Type.DEFAULT
        set(value) {
            field = value
            applyType()
            applyStyle()
        }

    var terraTextFieldState: State = State.DEFAULT
        set(value) {
            field = value
            applyStyle()
        }

    init {
        orientation = VERTICAL
        inflate(context, R.layout.terra_text_field, this)

        labelView = findViewById(R.id.terraTextFieldLabel)
        containerView = findViewById(R.id.terraTextFieldContainer)
        leftIconView = findViewById(R.id.terraTextFieldLeftIcon)
        prefixTextView = findViewById(R.id.terraTextFieldPrefixText)
        prefixIconView = findViewById(R.id.terraTextFieldPrefixIcon)
        dividerView = findViewById(R.id.terraTextFieldDivider)
        inputView = findViewById(R.id.terraTextFieldInput)
        suffixTextView = findViewById(R.id.terraTextFieldSuffixText)
        rightIconView = findViewById(R.id.terraTextFieldRightIcon)
        helperView = findViewById(R.id.terraTextFieldHelper)

        context.obtainStyledAttributes(attrs, R.styleable.TerraTextField).apply {
            labelView.text = getString(R.styleable.TerraTextField_terraTextFieldLabelText) ?: labelView.text
            labelView.isVisible = getBoolean(R.styleable.TerraTextField_terraTextFieldHasLabel, true)

            val helperText = getString(R.styleable.TerraTextField_terraTextFieldHelperText)
            helperView.text = helperText ?: helperView.text
            helperView.isVisible = getBoolean(R.styleable.TerraTextField_terraTextFieldHasHelperText, !helperText.isNullOrBlank())

            inputView.hint = getString(R.styleable.TerraTextField_android_hint) ?: inputView.hint
            inputView.setText(getString(R.styleable.TerraTextField_android_text) ?: "")
            prefixTextView.text = getString(R.styleable.TerraTextField_terraTextFieldPrefixText) ?: "Rp"
            suffixTextView.text = getString(R.styleable.TerraTextField_terraTextFieldSuffixText) ?: "Kg"
            terraTextFieldType = Type.entries[getInt(R.styleable.TerraTextField_terraTextFieldType, 0)]
            terraTextFieldState = State.entries[getInt(R.styleable.TerraTextField_terraTextFieldState, 0)]
            setOptionalIcon(leftIconView, getResourceId(R.styleable.TerraTextField_terraTextFieldLeftIcon, 0), getBoolean(R.styleable.TerraTextField_terraTextFieldHasLeftIcon, false))
            setOptionalIcon(rightIconView, getResourceId(R.styleable.TerraTextField_terraTextFieldRightIcon, 0), getBoolean(R.styleable.TerraTextField_terraTextFieldHasRightIcon, false))
            setOptionalIcon(prefixIconView, getResourceId(R.styleable.TerraTextField_terraTextFieldPrefixIcon, 0), getBoolean(R.styleable.TerraTextField_terraTextFieldHasPrefixIcon, false))
            recycle()
        }

        applyType()
        applyStyle()
    }

    private fun setOptionalIcon(view: ImageView, @DrawableRes resId: Int, visible: Boolean) {
        val drawable = if (resId != 0) AppCompatResources.getDrawable(context, resId) else null
        view.setImageDrawable(drawable)
        view.isVisible = visible && drawable != null
    }

    private fun applyType() {
        val isDefault = terraTextFieldType == Type.DEFAULT
        val isTextPrefix = terraTextFieldType == Type.TEXT_PREFIX
        val isTextSuffix = terraTextFieldType == Type.TEXT_SUFFIX
        val isPrefixIconPrefix = terraTextFieldType == Type.TEXT_PREFIX_ICON_PREFIX

        leftIconView.isVisible = isDefault && leftIconView.drawable != null
        rightIconView.isVisible = isDefault && rightIconView.drawable != null
        prefixTextView.isVisible = isTextPrefix || isPrefixIconPrefix
        suffixTextView.isVisible = isTextSuffix
        prefixIconView.isVisible = isPrefixIconPrefix && prefixIconView.drawable != null
        dividerView.isVisible = isPrefixIconPrefix && (prefixTextView.isVisible || prefixIconView.isVisible)
    }

    private fun applyStyle() {
        val bgColor = if (terraTextFieldState == State.DISABLED) {
            color(com.terra.design.tokens.R.color.terra_color_bg_surface_secondary_default)
        } else {
            color(com.terra.design.tokens.R.color.terra_color_bg_default)
        }
        val strokeColor = when (terraTextFieldState) {
            State.ACTIVE -> color(com.terra.design.tokens.R.color.terra_color_bg_fill_primary_default)
            State.ERROR -> color(com.terra.design.tokens.R.color.terra_color_bg_fill_danger_default)
            else -> color(com.terra.design.tokens.R.color.terra_color_border_secondary_default)
        }
        val textColor = if (terraTextFieldState == State.DISABLED) {
            color(com.terra.design.tokens.R.color.terra_color_text_primary_disabled)
        } else {
            color(com.terra.design.tokens.R.color.terra_color_text_secondary_default)
        }
        val inputColor = if (terraTextFieldState == State.FILLED || terraTextFieldState == State.ERROR || terraTextFieldState == State.ACTIVE) {
            color(com.terra.design.tokens.R.color.terra_color_text_primary_default)
        } else {
            color(com.terra.design.tokens.R.color.terra_color_text_primary_disabled)
        }
        val useBold = terraTextFieldState == State.FILLED || terraTextFieldState == State.ERROR

        containerView.background = GradientDrawable().apply {
            shape = GradientDrawable.RECTANGLE
            setColor(bgColor)
            cornerRadius = dimen(com.terra.design.tokens.R.dimen.terra_radius_8).toFloat()
            setStroke(dp(1), strokeColor)
        }

        TextViewCompat.setTextAppearance(labelView, com.terra.design.tokens.R.style.TextAppearance_Terra_Body_Small_Regular)
        TextViewCompat.setTextAppearance(helperView, com.terra.design.tokens.R.style.TextAppearance_Terra_Caption)
        TextViewCompat.setTextAppearance(prefixTextView, com.terra.design.tokens.R.style.TextAppearance_Terra_Body_Small_Bold)
        TextViewCompat.setTextAppearance(suffixTextView, com.terra.design.tokens.R.style.TextAppearance_Terra_Body_Small_Bold)
        TextViewCompat.setTextAppearance(
            inputView,
            if (useBold) com.terra.design.tokens.R.style.TextAppearance_Terra_Body_Medium_Bold else com.terra.design.tokens.R.style.TextAppearance_Terra_Body_Medium_Regular,
        )

        labelView.setTextColor(textColor)
        helperView.setTextColor(textColor)
        prefixTextView.setTextColor(color(com.terra.design.tokens.R.color.terra_color_text_primary_default))
        suffixTextView.setTextColor(color(com.terra.design.tokens.R.color.terra_color_text_primary_default))
        inputView.setTextColor(inputColor)
        inputView.setHintTextColor(color(com.terra.design.tokens.R.color.terra_color_text_primary_disabled))
        inputView.isEnabled = terraTextFieldState != State.DISABLED

        val iconTint = ColorStateList.valueOf(textColor)
        leftIconView.imageTintList = iconTint
        rightIconView.imageTintList = iconTint
        prefixIconView.imageTintList = iconTint
        dividerView.backgroundTintList = ColorStateList.valueOf(color(com.terra.design.tokens.R.color.terra_color_border_secondary_default))
    }

    private fun dimen(resId: Int) = resources.getDimensionPixelSize(resId)

    private fun color(resId: Int) = ContextCompat.getColor(context, resId)

    private fun dp(value: Int) = (value * resources.displayMetrics.density).toInt()
}
