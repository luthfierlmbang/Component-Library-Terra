package com.terra.design.components

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.drawable.GradientDrawable
import android.util.AttributeSet
import android.view.Gravity
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.annotation.DrawableRes
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.content.ContextCompat
import androidx.core.widget.TextViewCompat

class TerraSearchBar @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : LinearLayout(context, attrs, defStyleAttr) {

    enum class State {
        DEFAULT,
        FOCUS,
        RESULT,
        DISABLED,
    }

    private val inputView: EditText
    private val trailingIconView: ImageView

    var state: State = State.DEFAULT
        set(value) {
            field = value
            applyStyle()
        }

    init {
        orientation = HORIZONTAL
        gravity = Gravity.CENTER_VERTICAL
        inflate(context, R.layout.terra_search_bar, this)

        inputView = findViewById(R.id.terraSearchBarInput)
        trailingIconView = findViewById(R.id.terraSearchBarTrailingIcon)

        context.obtainStyledAttributes(attrs, R.styleable.TerraSearchBar).apply {
            inputView.hint = getString(R.styleable.TerraSearchBar_android_hint) ?: inputView.hint
            inputView.setText(getString(R.styleable.TerraSearchBar_android_text) ?: "")
            state = State.entries[getInt(R.styleable.TerraSearchBar_terraSearchBarState, 0)]
            setTrailingIcon(getResourceId(R.styleable.TerraSearchBar_terraSearchBarTrailingIcon, 0))
            recycle()
        }

        applyStyle()
    }

    fun setText(text: CharSequence?) {
        inputView.setText(text)
    }

    fun setHint(text: CharSequence?) {
        inputView.hint = text
    }

    fun setTrailingIcon(@DrawableRes resId: Int) {
        trailingIconView.setImageDrawable(
            AppCompatResources.getDrawable(
                context,
                if (resId != 0) resId else if (state == State.FOCUS) R.drawable.close else R.drawable.search,
            ),
        )
    }

    private fun applyStyle() {
        val bgColor = if (state == State.DISABLED) {
            color(com.terra.design.tokens.R.color.terra_color_grey_200)
        } else {
            color(com.terra.design.tokens.R.color.terra_color_bg_default)
        }
        val strokeColor = if (state == State.FOCUS) {
            color(com.terra.design.tokens.R.color.terra_color_bg_fill_primary_default)
        } else {
            color(com.terra.design.tokens.R.color.terra_color_border_secondary_default)
        }
        val textColor = if (state == State.DISABLED) {
            color(com.terra.design.tokens.R.color.terra_color_text_primary_disabled)
        } else {
            color(com.terra.design.tokens.R.color.terra_color_text_primary_default)
        }
        val hintColor = color(com.terra.design.tokens.R.color.terra_color_text_primary_disabled)
        val useBold = state == State.FOCUS || state == State.RESULT

        setPaddingRelative(dimen(com.terra.design.tokens.R.dimen.terra_spacing_8), dimen(com.terra.design.tokens.R.dimen.terra_spacing_8), dimen(com.terra.design.tokens.R.dimen.terra_spacing_8), dimen(com.terra.design.tokens.R.dimen.terra_spacing_8))
        background = GradientDrawable().apply {
            shape = GradientDrawable.RECTANGLE
            setColor(bgColor)
            cornerRadius = dimen(com.terra.design.tokens.R.dimen.terra_radius_8).toFloat()
            setStroke(dp(1), strokeColor)
        }

        TextViewCompat.setTextAppearance(
            inputView,
            if (useBold) com.terra.design.tokens.R.style.TextAppearance_Terra_Body_Medium_Bold else com.terra.design.tokens.R.style.TextAppearance_Terra_Body_Medium_Regular,
        )
        inputView.setTextColor(textColor)
        inputView.setHintTextColor(hintColor)
        inputView.isEnabled = state != State.DISABLED
        trailingIconView.imageTintList = ColorStateList.valueOf(textColor)
        setTrailingIcon(0)
    }

    private fun dimen(resId: Int) = resources.getDimensionPixelSize(resId)

    private fun color(resId: Int) = ContextCompat.getColor(context, resId)

    private fun dp(value: Int) = (value * resources.displayMetrics.density).toInt()
}
