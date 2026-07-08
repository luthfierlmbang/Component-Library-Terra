# TerraLabelBadge

Badge label kecil dengan 4 state warna + icon opsional.

`components/src/main/java/com/terra/design/components/TerraLabelBadge.kt` · base: `LinearLayout`

## XML Attrs (styleable `TerraLabelBadge`)

| Attr | Format | Default |
|---|---|---|
| `android:text` | string | — |
| `app:terraLabelBadgeState` | enum | `stateDefault` |
| `app:terraLabelBadgeIcon` | reference | — |
| `app:terraLabelBadgeShowIcon` | boolean | `true` |

`terraLabelBadgeState`: `stateDefault` \| `secondary` \| `neutral` \| `failed`

## Public API (Kotlin)

```kotlin
var state: State = State.DEFAULT

fun setText(text: CharSequence?)
fun setIcon(@DrawableRes resId: Int, visible: Boolean = true)
```

Enum: `State { DEFAULT, SECONDARY, NEUTRAL, FAILED }`.

## Contoh XML

```xml
<com.terra.design.components.TerraLabelBadge
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:text="Baru"
    app:terraLabelBadgeState="secondary"
    app:terraLabelBadgeIcon="@drawable/ic_star"
    app:terraLabelBadgeShowIcon="true" />
```
