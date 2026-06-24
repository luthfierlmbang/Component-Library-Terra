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

class TerraMenuCard @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : LinearLayout(context, attrs, defStyleAttr) {

    private val leadIconView: ImageView
    private val titleView: TextView
    private val rightIconView: ImageView

    init {
        orientation = HORIZONTAL
        gravity = Gravity.CENTER_VERTICAL
        inflate(context, R.layout.terra_menu_card, this)

        leadIconView = findViewById(R.id.terraMenuCardLeadIcon)
        titleView = findViewById(R.id.terraMenuCardTitle)
        rightIconView = findViewById(R.id.terraMenuCardRightIcon)

        context.obtainStyledAttributes(attrs, R.styleable.TerraMenuCard).apply {
            titleView.text = getText(R.styleable.TerraMenuCard_android_text) ?: titleView.text
            setIcon(leadIconView, getResourceId(R.styleable.TerraMenuCard_terraLeadIcon, 0), getBoolean(R.styleable.TerraMenuCard_terraShowLeadIcon, true))
            setIcon(rightIconView, getResourceId(R.styleable.TerraMenuCard_terraRightIcon, 0), getBoolean(R.styleable.TerraMenuCard_terraShowRightIcon, true))
            recycle()
        }

        applyStyle()
    }

    fun setText(text: CharSequence?) {
        titleView.text = text
    }

    fun setLeadIconResource(@DrawableRes resId: Int) {
        setIcon(leadIconView, resId, true)
    }

    fun setRightIconResource(@DrawableRes resId: Int) {
        setIcon(rightIconView, resId, true)
    }

    private fun setIcon(view: ImageView, @DrawableRes resId: Int, visible: Boolean) {
        view.isVisible = visible && resId != 0
        view.setImageDrawable(if (view.isVisible) AppCompatResources.getDrawable(context, resId) else null)
        view.imageTintList = ColorStateList.valueOf(color(com.terra.design.tokens.R.color.terra_color_icon_primary_default))
    }

    private fun applyStyle() {
        val paddingHorizontal = dimen(com.terra.design.tokens.R.dimen.terra_spacing_24)
        val paddingVertical = dimen(com.terra.design.tokens.R.dimen.terra_spacing_16)
        val radius = dimen(com.terra.design.tokens.R.dimen.terra_radius_8)

        setPaddingRelative(paddingHorizontal, paddingVertical, paddingHorizontal, paddingVertical)
        background = GradientDrawable().apply {
            shape = GradientDrawable.RECTANGLE
            setColor(color(com.terra.design.tokens.R.color.terra_color_bg_default))
            cornerRadius = radius.toFloat()
        }
        elevation = dp(4).toFloat()

        TextViewCompat.setTextAppearance(titleView, com.terra.design.tokens.R.style.TextAppearance_Terra_Header_S)
        titleView.setTextColor(color(com.terra.design.tokens.R.color.terra_color_text_primary_default))
        if (!leadIconView.isVisible) titleView.layoutParams = (titleView.layoutParams as LayoutParams).apply { marginStart = 0 }
    }

    private fun dimen(resId: Int) = resources.getDimensionPixelSize(resId)

    private fun color(resId: Int) = ContextCompat.getColor(context, resId)

    private fun dp(value: Int) = (value * resources.displayMetrics.density).toInt()
}
