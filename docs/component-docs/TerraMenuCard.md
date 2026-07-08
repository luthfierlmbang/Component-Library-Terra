# TerraMenuCard

`components/src/main/java/com/terra/design/components/TerraMenuCard.kt` · base: `LinearLayout`

## XML Attrs (styleable `TerraMenuCard`)

| Attr | Format | Default |
|---|---|---|
| `android:text` | string | — |
| `app:terraLeadIcon` / `terraShowLeadIcon` | reference / boolean | `true` |
| `app:terraRightIcon` / `terraShowRightIcon` | reference / boolean | `true` |

## Public API (Kotlin)

```kotlin
fun setText(text: CharSequence?)
fun setLeadIconResource(@DrawableRes resId: Int)
fun setRightIconResource(@DrawableRes resId: Int)
```

## Contoh XML

```xml
<com.terra.design.components.TerraMenuCard
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:text="Pengaturan"
    app:terraLeadIcon="@drawable/ic_settings"
    app:terraShowLeadIcon="true"
    app:terraShowRightIcon="true" />
```

## ⚠️ Known issue

`setLeadIconResource()` / `setRightIconResource()` dipanggil setelah init selalu paksa icon jadi visible, override attr `terraShowLeadIcon`/`terraShowRightIcon` yang di-set di XML.
