# TerraCheckbox

`components/src/main/java/com/terra/design/components/TerraCheckbox.kt` · base: `LinearLayout`

## XML Attrs (styleable `TerraCheckbox`)

| Attr | Format | Default |
|---|---|---|
| `android:text` | string | — |
| `android:checked` | boolean | `false` |
| `android:enabled` | boolean | — |
| `app:terraCheckboxShowLabel` | boolean | `false` |

## Public API (Kotlin)

```kotlin
var checked: Boolean = false
var showLabel: Boolean = false   // toggle visibility label

fun setText(text: CharSequence?)
override fun setEnabled(enabled: Boolean)
```

## Contoh XML

```xml
<com.terra.design.components.TerraCheckbox
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:text="Setuju dengan syarat & ketentuan"
    android:checked="true"
    app:terraCheckboxShowLabel="true" />
```
