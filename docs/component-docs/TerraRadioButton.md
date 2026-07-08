# TerraRadioButton

`components/src/main/java/com/terra/design/components/TerraRadioButton.kt` · base: `LinearLayout`

## XML Attrs (styleable `TerraRadioButton`)

| Attr | Format | Default |
|---|---|---|
| `android:text` | string | — |
| `android:checked` | boolean | `false` |
| `android:enabled` | boolean | — |
| `app:terraRadioButtonShowLabel` | boolean | `false` |

## Public API (Kotlin)

```kotlin
var checked: Boolean = false
var showLabel: Boolean = false

fun setText(text: CharSequence?)
override fun setEnabled(enabled: Boolean)
```

## Token dipakai

- **Color**: `terra_color_bg_fill_primary_default`, `terra_color_grey_400` (⚠️ primitive), `terra_color_grey_500` (⚠️ primitive), `terra_color_text_secondary_default`
- **Typography**: `TextAppearance.Terra.Body.Medium.Bold`, `Body.Medium.Regular`

## Contoh XML

```xml
<com.terra.design.components.TerraRadioButton
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:text="Pilihan A"
    android:checked="true"
    app:terraRadioButtonShowLabel="true" />
```
