package com.terra.design.components

import android.content.Context
import android.graphics.drawable.GradientDrawable
import android.util.AttributeSet
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.annotation.DrawableRes
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.core.widget.TextViewCompat

class TerraImageUpload @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : LinearLayout(context, attrs, defStyleAttr) {

    enum class State {
        IMAGE_UPLOAD,
        ATTACH_PROCESS,
        IMAGE_ATTACHED,
    }

    private val labelView: TextView
    private val frameView: FrameLayout
    private val previewView: ImageView
    private val emptyStateView: LinearLayout
    private val cameraIconView: ImageView
    private val primaryButton: TerraButton
    private val bottomActionsView: LinearLayout
    private val secondaryButton: TerraButton
    private val dividerView: android.view.View
    private val tertiaryButton: TerraButton
    private val loadingStateView: LinearLayout
    private val loadingTextView: TextView
    private val helperView: TextView

    var state: State = State.IMAGE_UPLOAD
        set(value) {
            field = value
            applyState()
        }

    init {
        orientation = VERTICAL
        inflate(context, R.layout.terra_image_upload, this)

        labelView = findViewById(R.id.terraImageUploadLabel)
        frameView = findViewById(R.id.terraImageUploadFrame)
        previewView = findViewById(R.id.terraImageUploadPreview)
        emptyStateView = findViewById(R.id.terraImageUploadEmptyState)
        cameraIconView = findViewById(R.id.terraImageUploadCameraIcon)
        primaryButton = findViewById(R.id.terraImageUploadPrimaryButton)
        bottomActionsView = findViewById(R.id.terraImageUploadBottomActions)
        secondaryButton = findViewById(R.id.terraImageUploadSecondaryButton)
        dividerView = findViewById(R.id.terraImageUploadDivider)
        tertiaryButton = findViewById(R.id.terraImageUploadTertiaryButton)
        loadingStateView = findViewById(R.id.terraImageUploadLoadingState)
        loadingTextView = findViewById(R.id.terraImageUploadLoadingText)
        helperView = findViewById(R.id.terraImageUploadHelper)

        context.obtainStyledAttributes(attrs, R.styleable.TerraImageUpload).apply {
            labelView.text = getString(R.styleable.TerraImageUpload_terraImageUploadLabel)
            helperView.text = getString(R.styleable.TerraImageUpload_terraImageUploadHelperText)
            helperView.isVisible = getBoolean(R.styleable.TerraImageUpload_terraImageUploadShowHelper, true)
            primaryButton.setText(getString(R.styleable.TerraImageUpload_terraImageUploadPrimaryActionText) ?: "Ambil Gambar")
            secondaryButton.setText(getString(R.styleable.TerraImageUpload_terraImageUploadSecondaryActionText) ?: "Ubah")
            tertiaryButton.setText(getString(R.styleable.TerraImageUpload_terraImageUploadTertiaryActionText) ?: "Lihat")
            loadingTextView.text = getString(R.styleable.TerraImageUpload_terraImageUploadLoadingText) ?: "Dalam Proses..."
            state = State.entries[getInt(R.styleable.TerraImageUpload_terraImageUploadState, 0)]

            val previewRes = getResourceId(R.styleable.TerraImageUpload_terraImageUploadPreviewSrc, 0)
            if (previewRes != 0) {
                setPreviewImage(previewRes)
            }
            setActionCount(getInt(R.styleable.TerraImageUpload_terraImageUploadActionCount, 1))
            recycle()
        }

        setCameraIconResource(R.drawable.information)
        applyStyle()
        applyState()
    }

    fun setPreviewImage(@DrawableRes resId: Int) {
        previewView.setImageDrawable(AppCompatResources.getDrawable(context, resId))
    }

    fun setCameraIconResource(@DrawableRes resId: Int) {
        cameraIconView.setImageDrawable(AppCompatResources.getDrawable(context, resId))
        cameraIconView.imageTintList = android.content.res.ColorStateList.valueOf(color(com.terra.design.tokens.R.color.terra_color_icon_primary_default))
    }

    private fun applyStyle() {
        frameView.background = GradientDrawable().apply {
            shape = GradientDrawable.RECTANGLE
            setColor(color(com.terra.design.tokens.R.color.terra_color_bg_default))
            cornerRadius = dimen(com.terra.design.tokens.R.dimen.terra_radius_8).toFloat()
            setStroke(dp(1), color(com.terra.design.tokens.R.color.terra_color_border_secondary_default))
        }

        TextViewCompat.setTextAppearance(labelView, com.terra.design.tokens.R.style.TextAppearance_Terra_Caption)
        TextViewCompat.setTextAppearance(helperView, com.terra.design.tokens.R.style.TextAppearance_Terra_Caption)
        TextViewCompat.setTextAppearance(loadingTextView, com.terra.design.tokens.R.style.TextAppearance_Terra_Body_Small_Regular)

        labelView.setTextColor(color(com.terra.design.tokens.R.color.terra_color_text_secondary_default))
        helperView.setTextColor(color(com.terra.design.tokens.R.color.terra_color_text_secondary_default))
        loadingTextView.setTextColor(color(com.terra.design.tokens.R.color.terra_color_text_secondary_default))

        primaryButton.terraButtonType = TerraButton.Type.OUTLINED_PRIMARY
        primaryButton.terraButtonSize = TerraButton.Size.SMALL
        secondaryButton.terraButtonType = TerraButton.Type.TEXT_BUTTON
        secondaryButton.terraButtonSize = TerraButton.Size.SMALL
        tertiaryButton.terraButtonType = TerraButton.Type.TEXT_BUTTON
        tertiaryButton.terraButtonSize = TerraButton.Size.SMALL
    }

    private fun applyState() {
        when (state) {
            State.IMAGE_UPLOAD -> {
                previewView.isVisible = false
                emptyStateView.isVisible = true
                bottomActionsView.isVisible = false
                loadingStateView.isVisible = false
            }
            State.ATTACH_PROCESS -> {
                previewView.isVisible = true
                emptyStateView.isVisible = false
                bottomActionsView.isVisible = false
                loadingStateView.isVisible = true
            }
            State.IMAGE_ATTACHED -> {
                previewView.isVisible = true
                emptyStateView.isVisible = false
                bottomActionsView.isVisible = true
                loadingStateView.isVisible = false
            }
        }
    }

    fun setActionCount(count: Int) {
        tertiaryButton.isVisible = count > 1
        dividerView.isVisible = count > 1
    }

    private fun dimen(resId: Int) = resources.getDimensionPixelSize(resId)

    private fun color(resId: Int) = ContextCompat.getColor(context, resId)

    private fun dp(value: Int) = (value * resources.displayMetrics.density).toInt()
}
