package com.terra.design.components

import android.content.Context
import android.graphics.drawable.GradientDrawable
import android.util.AttributeSet
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.core.widget.TextViewCompat

class TerraActivityCard @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : LinearLayout(context, attrs, defStyleAttr) {

    private val labelContainer: LinearLayout
    private val labelIconView: ImageView
    private val labelTextView: TextView
    private val rightIconView: ImageView
    private val activityNameView: TextView
    private val timeIconView: ImageView
    private val activityTimeView: TextView
    private val actionButton: TerraButton

    init {
        orientation = VERTICAL
        inflate(context, R.layout.terra_activity_card, this)

        labelContainer = findViewById(R.id.terraActivityCardLabel)
        labelIconView = findViewById(R.id.terraActivityCardLabelIcon)
        labelTextView = findViewById(R.id.terraActivityCardLabelText)
        rightIconView = findViewById(R.id.terraActivityCardRightIcon)
        activityNameView = findViewById(R.id.terraActivityCardActivityName)
        timeIconView = findViewById(R.id.terraActivityCardTimeIcon)
        activityTimeView = findViewById(R.id.terraActivityCardActivityTime)
        actionButton = findViewById(R.id.terraActivityCardAction)

        context.obtainStyledAttributes(attrs, R.styleable.TerraActivityCard).apply {
            labelTextView.text = getString(R.styleable.TerraActivityCard_terraLabelText)
            activityNameView.text = getString(R.styleable.TerraActivityCard_terraActivityName)
            activityTimeView.text = getString(R.styleable.TerraActivityCard_terraActivityTime)
            actionButton.setText(getString(R.styleable.TerraActivityCard_terraActionText))
            actionButton.isVisible = getBoolean(R.styleable.TerraActivityCard_terraShowAction, true)
            setOptionalIcon(labelIconView, getResourceId(R.styleable.TerraActivityCard_terraLabelIcon, 0))
            setOptionalIcon(rightIconView, getResourceId(R.styleable.TerraActivityCard_terraRightIcon, 0))
            setOptionalIcon(timeIconView, getResourceId(R.styleable.TerraActivityCard_terraTimeIcon, 0))
            recycle()
        }

        applyStyle()
    }

    private fun setOptionalIcon(view: ImageView, resId: Int) {
        view.isVisible = resId != 0
        if (view.isVisible) {
            view.setImageDrawable(AppCompatResources.getDrawable(context, resId))
        }
    }

    private fun applyStyle() {
        val padding = dimen(com.terra.design.tokens.R.dimen.terra_spacing_16)
        val radius = dimen(com.terra.design.tokens.R.dimen.terra_radius_8)
        setPadding(padding, padding, padding, padding)
        background = GradientDrawable().apply {
            shape = GradientDrawable.RECTANGLE
            setColor(color(com.terra.design.tokens.R.color.terra_color_bg_default))
            cornerRadius = radius.toFloat()
        }
        elevation = dp(4).toFloat()

        labelContainer.background = GradientDrawable().apply {
            shape = GradientDrawable.RECTANGLE
            setColor(color(com.terra.design.tokens.R.color.terra_color_bg_fill_secondary_default))
            cornerRadius = dimen(com.terra.design.tokens.R.dimen.terra_spacing_4).toFloat()
        }
        labelTextView.setTextColor(color(com.terra.design.tokens.R.color.terra_color_text_inverse_default))

        TextViewCompat.setTextAppearance(activityNameView, com.terra.design.tokens.R.style.TextAppearance_Terra_Body_Medium_Regular)
        TextViewCompat.setTextAppearance(activityTimeView, com.terra.design.tokens.R.style.TextAppearance_Terra_Body_Medium_Regular)
        activityNameView.setTextColor(color(com.terra.design.tokens.R.color.terra_color_text_primary_default))
        activityTimeView.setTextColor(color(com.terra.design.tokens.R.color.terra_color_text_primary_default))

        actionButton.terraButtonType = TerraButton.Type.SECONDARY
        actionButton.terraButtonSize = TerraButton.Size.SMALL
    }

    private fun dimen(resId: Int) = resources.getDimensionPixelSize(resId)

    private fun color(resId: Int) = ContextCompat.getColor(context, resId)

    private fun dp(value: Int) = (value * resources.displayMetrics.density).toInt()
}
