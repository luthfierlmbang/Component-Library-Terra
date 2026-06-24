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

class TerraInformationCard @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : LinearLayout(context, attrs, defStyleAttr) {

    private val headerView: LinearLayout
    private val profileImageView: ImageView
    private val profileNameView: TextView
    private val profileSubtitleView: TextView
    private val statusView: TextView
    private val titleView: TextView
    private val infoRow1: LinearLayout
    private val infoLabel1View: TextView
    private val infoValue1View: TextView
    private val infoRow2: LinearLayout
    private val infoLabel2View: TextView
    private val infoValue2View: TextView
    private val buttonView: TerraButton

    init {
        orientation = VERTICAL
        inflate(context, R.layout.terra_information_card, this)

        headerView = findViewById(R.id.terraInformationCardHeader)
        profileImageView = findViewById(R.id.terraInformationCardProfileImage)
        profileNameView = findViewById(R.id.terraInformationCardProfileName)
        profileSubtitleView = findViewById(R.id.terraInformationCardProfileSubtitle)
        statusView = findViewById(R.id.terraInformationCardStatus)
        titleView = findViewById(R.id.terraInformationCardTitle)
        infoRow1 = findViewById(R.id.terraInformationCardInfoRow1)
        infoLabel1View = findViewById(R.id.terraInformationCardInfoLabel1)
        infoValue1View = findViewById(R.id.terraInformationCardInfoValue1)
        infoRow2 = findViewById(R.id.terraInformationCardInfoRow2)
        infoLabel2View = findViewById(R.id.terraInformationCardInfoLabel2)
        infoValue2View = findViewById(R.id.terraInformationCardInfoValue2)
        buttonView = findViewById(R.id.terraInformationCardButton)

        context.obtainStyledAttributes(attrs, R.styleable.TerraInformationCard).apply {
            titleView.text = getString(R.styleable.TerraInformationCard_terraCardTitle)
            profileNameView.text = getString(R.styleable.TerraInformationCard_terraProfileName)
            profileSubtitleView.text = getString(R.styleable.TerraInformationCard_terraProfileSubtitle)
            statusView.text = getString(R.styleable.TerraInformationCard_terraStatusLabel)
            infoLabel1View.text = getString(R.styleable.TerraInformationCard_terraInfoLabel1)
            infoValue1View.text = getString(R.styleable.TerraInformationCard_terraInfoValue1)
            infoLabel2View.text = getString(R.styleable.TerraInformationCard_terraInfoLabel2)
            infoValue2View.text = getString(R.styleable.TerraInformationCard_terraInfoValue2)
            buttonView.setText(getString(R.styleable.TerraInformationCard_terraButtonText))
            headerView.isVisible = getBoolean(R.styleable.TerraInformationCard_terraShowProfile, true)
            buttonView.isVisible = getBoolean(R.styleable.TerraInformationCard_terraShowButton, true)

            val profileImageRes = getResourceId(R.styleable.TerraInformationCard_terraProfileImage, 0)
            if (profileImageRes != 0) {
                profileImageView.setImageDrawable(AppCompatResources.getDrawable(context, profileImageRes))
            }
            recycle()
        }

        applyStyle()
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

        TextViewCompat.setTextAppearance(profileNameView, com.terra.design.tokens.R.style.TextAppearance_Terra_Body_Medium_Bold)
        TextViewCompat.setTextAppearance(profileSubtitleView, com.terra.design.tokens.R.style.TextAppearance_Terra_Body_Medium_Regular)
        TextViewCompat.setTextAppearance(titleView, com.terra.design.tokens.R.style.TextAppearance_Terra_Body_Medium_Bold)
        TextViewCompat.setTextAppearance(infoLabel1View, com.terra.design.tokens.R.style.TextAppearance_Terra_Body_Small_Regular)
        TextViewCompat.setTextAppearance(infoValue1View, com.terra.design.tokens.R.style.TextAppearance_Terra_Body_Small_Bold)
        TextViewCompat.setTextAppearance(infoLabel2View, com.terra.design.tokens.R.style.TextAppearance_Terra_Body_Small_Regular)
        TextViewCompat.setTextAppearance(infoValue2View, com.terra.design.tokens.R.style.TextAppearance_Terra_Body_Small_Bold)

        profileNameView.setTextColor(color(com.terra.design.tokens.R.color.terra_color_text_primary_default))
        profileSubtitleView.setTextColor(color(com.terra.design.tokens.R.color.terra_color_text_secondary_default))
        titleView.setTextColor(color(com.terra.design.tokens.R.color.terra_color_text_primary_default))
        infoLabel1View.setTextColor(color(com.terra.design.tokens.R.color.terra_color_text_primary_default))
        infoValue1View.setTextColor(color(com.terra.design.tokens.R.color.terra_color_text_primary_default))
        infoLabel2View.setTextColor(color(com.terra.design.tokens.R.color.terra_color_text_primary_default))
        infoValue2View.setTextColor(color(com.terra.design.tokens.R.color.terra_color_text_primary_default))

        statusView.background = GradientDrawable().apply {
            shape = GradientDrawable.RECTANGLE
            setColor(color(com.terra.design.tokens.R.color.terra_color_text_primary_disabled))
            cornerRadius = dimen(com.terra.design.tokens.R.dimen.terra_spacing_4).toFloat()
        }
        buttonView.terraButtonType = TerraButton.Type.PRIMARY
        buttonView.terraButtonSize = TerraButton.Size.NORMAL
    }

    private fun dimen(resId: Int) = resources.getDimensionPixelSize(resId)

    private fun color(resId: Int) = ContextCompat.getColor(context, resId)

    private fun dp(value: Int) = (value * resources.displayMetrics.density).toInt()
}
