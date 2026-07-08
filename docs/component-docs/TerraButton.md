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

## Catatan

Paling matang di antara semua komponen — satu-satunya yang full token-driven dari sisi Kotlin (`applyStyle()`, `TerraButton.kt:126-135`) dan punya katalog preview sendiri (`terra_button_catalog.xml`).
