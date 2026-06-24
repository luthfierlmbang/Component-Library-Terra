package com.terra.design.components

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.drawable.GradientDrawable
import android.util.AttributeSet
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.annotation.DrawableRes
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.content.ContextCompat
import androidx.core.widget.TextViewCompat

class TerraBottomNavBar @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : LinearLayout(context, attrs, defStyleAttr) {

    enum class Item {
        HOME,
        ACTIVITY,
        NOTIFICATION,
        NONE,
    }

    private val homeIconContainer: FrameLayout
    private val activityIconContainer: FrameLayout
    private val notificationIconContainer: FrameLayout
    private val homeIconView: ImageView
    private val activityIconView: ImageView
    private val notificationIconView: ImageView
    private val homeTextView: TextView
    private val activityTextView: TextView
    private val notificationTextView: TextView

    var selectedItem: Item = Item.NONE
        set(value) {
            field = value
            applySelection()
        }

    init {
        orientation = HORIZONTAL
        inflate(context, R.layout.terra_bottom_nav_bar, this)

        homeIconContainer = findViewById(R.id.terraBottomNavHomeIconContainer)
        activityIconContainer = findViewById(R.id.terraBottomNavActivityIconContainer)
        notificationIconContainer = findViewById(R.id.terraBottomNavNotificationIconContainer)
        homeIconView = findViewById(R.id.terraBottomNavHomeIcon)
        activityIconView = findViewById(R.id.terraBottomNavActivityIcon)
        notificationIconView = findViewById(R.id.terraBottomNavNotificationIcon)
        homeTextView = findViewById(R.id.terraBottomNavHomeText)
        activityTextView = findViewById(R.id.terraBottomNavActivityText)
        notificationTextView = findViewById(R.id.terraBottomNavNotificationText)

        context.obtainStyledAttributes(attrs, R.styleable.TerraBottomNavBar).apply {
            selectedItem = Item.entries[getInt(R.styleable.TerraBottomNavBar_terraBottomNavSelectedItem, 3)]
            homeTextView.text = getString(R.styleable.TerraBottomNavBar_terraBottomNavHomeText) ?: homeTextView.text
            activityTextView.text = getString(R.styleable.TerraBottomNavBar_terraBottomNavActivityText) ?: activityTextView.text
            notificationTextView.text = getString(R.styleable.TerraBottomNavBar_terraBottomNavNotificationText) ?: notificationTextView.text
            setIcon(homeIconView, getResourceId(R.styleable.TerraBottomNavBar_terraBottomNavHomeIcon, 0), R.drawable.information)
            setIcon(activityIconView, getResourceId(R.styleable.TerraBottomNavBar_terraBottomNavActivityIcon, 0), R.drawable.search)
            setIcon(notificationIconView, getResourceId(R.styleable.TerraBottomNavBar_terraBottomNavNotificationIcon, 0), R.drawable.settings)
            recycle()
        }

        applyStyle()
        applySelection()
    }

    private fun setIcon(view: ImageView, @DrawableRes resId: Int, @DrawableRes fallbackResId: Int) {
        view.setImageDrawable(AppCompatResources.getDrawable(context, if (resId != 0) resId else fallbackResId))
    }

    private fun applyStyle() {
        setPaddingRelative(dp(45), dp(6), dp(45), dp(8))
        background = GradientDrawable().apply {
            shape = GradientDrawable.RECTANGLE
            setColor(color(com.terra.design.tokens.R.color.terra_color_bg_default))
        }
        elevation = dp(4).toFloat()

        listOf(homeTextView, activityTextView, notificationTextView).forEach {
            TextViewCompat.setTextAppearance(it, com.terra.design.tokens.R.style.TextAppearance_Terra_Caption)
        }
    }

    private fun applySelection() {
        applyItemStyle(homeIconContainer, homeIconView, homeTextView, selectedItem == Item.HOME)
        applyItemStyle(activityIconContainer, activityIconView, activityTextView, selectedItem == Item.ACTIVITY)
        applyItemStyle(notificationIconContainer, notificationIconView, notificationTextView, selectedItem == Item.NOTIFICATION)
    }

    private fun applyItemStyle(container: FrameLayout, icon: ImageView, text: TextView, selected: Boolean) {
        val selectedColor = color(com.terra.design.tokens.R.color.terra_color_bg_fill_primary_default)
        val unselectedColor = color(com.terra.design.tokens.R.color.terra_color_text_secondary_default)

        container.background = if (selected) {
            GradientDrawable().apply {
                shape = GradientDrawable.OVAL
                setColor(color(com.terra.design.tokens.R.color.terra_color_bg_default))
            }
        } else {
            null
        }
        container.elevation = if (selected) dp(6).toFloat() else 0f
        icon.imageTintList = ColorStateList.valueOf(if (selected) selectedColor else unselectedColor)
        text.setTextColor(if (selected) selectedColor else unselectedColor)
    }

    private fun color(resId: Int) = ContextCompat.getColor(context, resId)

    private fun dp(value: Int) = (value * resources.displayMetrics.density).toInt()
}
