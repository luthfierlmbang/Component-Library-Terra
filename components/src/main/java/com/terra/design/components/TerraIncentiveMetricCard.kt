package com.terra.design.components

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.util.AttributeSet
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.annotation.DrawableRes
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.content.ContextCompat
import androidx.core.widget.TextViewCompat

class TerraIncentiveMetricCard @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : LinearLayout(context, attrs, defStyleAttr) {

    private val leadIconView: ImageView
    private val titleView: TextView
    private val periodView: TextView
    private val amountView: TextView
    private val rightIconView: ImageView

    init {
        orientation = HORIZONTAL
        inflate(context, R.layout.terra_incentive_metric_card, this)

        leadIconView = findViewById(R.id.terraIncentiveMetricCardLeadIcon)
        titleView = findViewById(R.id.terraIncentiveMetricCardTitle)
        periodView = findViewById(R.id.terraIncentiveMetricCardPeriod)
        amountView = findViewById(R.id.terraIncentiveMetricCardAmount)
        rightIconView = findViewById(R.id.terraIncentiveMetricCardRightIcon)

        context.obtainStyledAttributes(attrs, R.styleable.TerraIncentiveMetricCard).apply {
            titleView.text = getString(R.styleable.TerraIncentiveMetricCard_terraMetricTitle)
            periodView.text = getString(R.styleable.TerraIncentiveMetricCard_terraMetricPeriod)
            amountView.text = getString(R.styleable.TerraIncentiveMetricCard_terraMetricAmount)
            setIcon(leadIconView, getResourceId(R.styleable.TerraIncentiveMetricCard_terraMetricLeadIcon, 0))
            setIcon(rightIconView, getResourceId(R.styleable.TerraIncentiveMetricCard_terraMetricRightIcon, 0))
            applyGradient(getInt(R.styleable.TerraIncentiveMetricCard_terraMetricVariant, 0))
            recycle()
        }

        applyStyle()
    }

    private fun setIcon(view: ImageView, @DrawableRes resId: Int) {
        view.setImageDrawable(AppCompatResources.getDrawable(context, resId))
        view.imageTintList = ColorStateList.valueOf(Color.WHITE)
    }

    private fun applyStyle() {
        val paddingStart = dimen(com.terra.design.tokens.R.dimen.terra_spacing_16)
        val paddingTop = dimen(com.terra.design.tokens.R.dimen.terra_spacing_16)
        val paddingEnd = dimen(com.terra.design.tokens.R.dimen.terra_spacing_8)
        val paddingBottom = dimen(com.terra.design.tokens.R.dimen.terra_spacing_16)
        val radius = dimen(com.terra.design.tokens.R.dimen.terra_radius_8)

        setPaddingRelative(paddingStart, paddingTop, paddingEnd, paddingBottom)
        clipToOutline = false
        (background as? GradientDrawable)?.cornerRadius = radius.toFloat()

        TextViewCompat.setTextAppearance(titleView, com.terra.design.tokens.R.style.TextAppearance_Terra_Body_Medium_Bold)
        TextViewCompat.setTextAppearance(periodView, com.terra.design.tokens.R.style.TextAppearance_Terra_Caption)
        TextViewCompat.setTextAppearance(amountView, com.terra.design.tokens.R.style.TextAppearance_Terra_Body_Medium_Bold)

        titleView.setTextColor(Color.WHITE)
        periodView.setTextColor(Color.WHITE)
        amountView.setTextColor(Color.WHITE)
    }

    private fun applyGradient(variant: Int) {
        val colors = when (variant) {
            1 -> intArrayOf(Color.parseColor("#027479"), Color.parseColor("#00C781"))
            2 -> intArrayOf(Color.parseColor("#0092E6"), Color.parseColor("#8BCDF3"))
            else -> intArrayOf(Color.parseColor("#027479"), Color.parseColor("#49BCC2"))
        }
        background = GradientDrawable(GradientDrawable.Orientation.TL_BR, colors)
    }

    private fun dimen(resId: Int) = resources.getDimensionPixelSize(resId)
}
