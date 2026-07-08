# TerraButton

Custom button, 6 type x 2 size dalam satu View.

`components/src/main/java/com/terra/design/components/TerraButton.kt` · base: `LinearLayout`

## XML Attrs (styleable `TerraButton`)

| Attr | Format | Default |
|---|---|---|
| `android:text` | string | — |
| `app:terraButtonType` | enum | `primary` |
| `app:terraButtonSize` | enum | `normal` |
| `app:terraLeftIcon` | reference | — |
| `app:terraRightIcon` | reference | — |

`terraButtonType`: `primary` \| `secondary` \| `outlinedPrimary` \| `outlinedSecondary` \| `danger` \| `textButton`
`terraButtonSize`: `normal` \| `small`

## Public API (Kotlin)

```kotlin
var terraButtonType: Type = Type.PRIMARY
var terraButtonSize: Size = Size.NORMAL

override fun setEnabled(enabled: Boolean)
override fun setClickable(clickable: Boolean)
override fun setSelected(selected: Boolean)
fun setText(text: CharSequence?)
fun getText(): CharSequence
fun setLeftIconResource(@DrawableRes resId: Int)
fun setRightIconResource(@DrawableRes resId: Int)
```

Enum: `Type { PRIMARY, SECONDARY, OUTLINED_PRIMARY, OUTLINED_SECONDARY, DANGER, TEXT_BUTTON }`, `Size { NORMAL, SMALL }`.

## Contoh XML

```xml
<com.terra.design.components.TerraButton
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:text="Kirim"
    app:terraButtonType="primary"
    app:terraButtonSize="normal"
    app:terraLeftIcon="@drawable/ic_send" />
```

## Token dipakai

Semua dari module `design-tokens` (lihat [tokens.md](tokens.md) untuk detail nilai + sumber file).

- **Color**: `terra_color_bg_disabled_default`, `terra_color_bg_fill_danger_default`, `terra_color_bg_fill_primary_default`, `terra_color_bg_fill_secondary_default`, `terra_color_border_action_primary_default`, `terra_color_border_action_secondary_default`, `terra_color_border_disabled_default`, `terra_color_text_action_primary_default`, `terra_color_text_inverse_default`, `terra_color_text_primary_disabled`
- **Dimens**: `terra_radius_6`, `terra_radius_8`, `terra_size_button_height_normal`, `terra_size_button_height_small`, `terra_size_icon_20`, `terra_size_icon_24`, `terra_spacing_16`, `terra_spacing_32`, `terra_spacing_4`, `terra_spacing_8`
- **Typography**: `TextAppearance.Terra.Button.Medium`, `TextAppearance.Terra.Button.Small`

## Catatan

Paling matang di antara semua komponen — satu-satunya yang full token-driven dari sisi Kotlin (`applyStyle()`, `TerraButton.kt:126-135`) dan punya katalog preview sendiri (`terra_button_catalog.xml`).
