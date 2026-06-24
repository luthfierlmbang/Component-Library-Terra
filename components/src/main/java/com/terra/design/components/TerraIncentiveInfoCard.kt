package com.terra.design.components

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.drawable.GradientDrawable
import android.util.AttributeSet
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.annotation.DrawableRes
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.content.ContextCompat
import androidx.core.widget.TextViewCompat

class TerraIncentiveInfoCard @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : LinearLayout(context, attrs, defStyleAttr) {

    private val leadIconView: ImageView
    private val titleView: TextView
    private val descriptionView: TextView
    private val rightIconView: ImageView

    init {
        orientation = HORIZONTAL
        inflate(context, R.layout.terra_incentive_info_card, this)

        leadIconView = findViewById(R.id.terraIncentiveInfoCardLeadIcon)
        titleView = findViewById(R.id.terraIncentiveInfoCardTitle)
        descriptionView = findViewById(R.id.terraIncentiveInfoCardDescription)
        rightIconView = findViewById(R.id.terraIncentiveInfoCardRightIcon)

        context.obtainStyledAttributes(attrs, R.styleable.TerraIncentiveInfoCard).apply {
            titleView.text = getString(R.styleable.TerraIncentiveInfoCard_terraInfoCardTitle)
            descriptionView.text = getString(R.styleable.TerraIncentiveInfoCard_terraInfoCardDescription)
            setIcon(leadIconView, getResourceId(R.styleable.TerraIncentiveInfoCard_terraInfoCardLeadIcon, 0), true)
            setIcon(rightIconView, getResourceId(R.styleable.TerraIncentiveInfoCard_terraInfoCardRightIcon, 0), true)
            recycle()
        }

        applyStyle()
    }

    private fun setIcon(view: ImageView, @DrawableRes resId: Int, tint: Boolean) {
        view.setImageDrawable(AppCompatResources.getDrawable(context, resId))
        if (tint) {
            view.imageTintList = ColorStateList.valueOf(color(com.terra.design.tokens.R.color.terra_color_icon_primary_default))
        }
    }

    private fun applyStyle() {
        val paddingStart = dimen(com.terra.design.tokens.R.dimen.terra_spacing_16)
        val paddingTop = dimen(com.terra.design.tokens.R.dimen.terra_spacing_16)
        val paddingEnd = dimen(com.terra.design.tokens.R.dimen.terra_spacing_8)
        val paddingBottom = dimen(com.terra.design.tokens.R.dimen.terra_spacing_16)
        val radius = dimen(com.terra.design.tokens.R.dimen.terra_radius_8)

        setPaddingRelative(paddingStart, paddingTop, paddingEnd, paddingBottom)
        background = GradientDrawable().apply {
            shape = GradientDrawable.RECTANGLE
            setColor(color(com.terra.design.tokens.R.color.terra_color_bg_default))
            cornerRadius = radius.toFloat()
        }
        elevation = dp(4).toFloat()

        TextViewCompat.setTextAppearance(titleView, com.terra.design.tokens.R.style.TextAppearance_Terra_Header_S)
        TextViewCompat.setTextAppearance(descriptionView, com.terra.design.tokens.R.style.TextAppearance_Terra_Caption)

        titleView.setTextColor(color(com.terra.design.tokens.R.color.terra_color_text_primary_default))
        descriptionView.setTextColor(color(com.terra.design.tokens.R.color.terra_color_text_secondary_default))
    }

    private fun dimen(resId: Int) = resources.getDimensionPixelSize(resId)

    private fun color(resId: Int) = ContextCompat.getColor(context, resId)

    private fun dp(value: Int) = (value * resources.displayMetrics.density).toInt()
}
