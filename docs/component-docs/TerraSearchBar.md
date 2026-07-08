# TerraSearchBar

`components/src/main/java/com/terra/design/components/TerraSearchBar.kt` · base: `LinearLayout`

## XML Attrs (styleable `TerraSearchBar`)

| Attr | Format | Default |
|---|---|---|
| `android:hint` | string | — |
| `android:text` | string | — |
| `app:terraSearchBarState` | enum | `stateDefault` |
| `app:terraSearchBarTrailingIcon` | reference | — |

`terraSearchBarState`: `stateDefault` \| `focus` \| `result` \| `disabled`

## Public API (Kotlin)

```kotlin
var state: State = State.DEFAULT

fun setText(text: CharSequence?)
fun setHint(text: CharSequence?)
fun setTrailingIcon(@DrawableRes resId: Int)
```

Enum: `State { DEFAULT, FOCUS, RESULT, DISABLED }`.

## Contoh XML

```xml
<com.terra.design.components.TerraSearchBar
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:hint="Cari..."
    app:terraSearchBarState="focus" />
```

## Token dipakai

- **Color**: `terra_color_bg_default`, `terra_color_bg_fill_primary_default`, `terra_color_border_secondary_default`, `terra_color_grey_200` (⚠️ primitive), `terra_color_text_primary_default`, `terra_color_text_primary_disabled`
- **Dimens**: `terra_radius_8`, `terra_spacing_8`
- **Typography**: `TextAppearance.Terra.Body.Medium.Bold`, `Body.Medium.Regular`

## ⚠️ Known issue (bug)

Set property `state` setelah init memanggil ulang `applyStyle()`, yang di dalamnya memanggil `setTrailingIcon(0)` (`TerraSearchBar.kt:109`) — ini **menimpa balik** trailing icon custom (baik dari attr `terraSearchBarTrailingIcon` maupun dari `setTrailingIcon()` publik) ke default tiap kali `state` di-ubah setelah constructor. Belum ada fix; kalau butuh custom trailing icon + dynamic state change, panggil `setTrailingIcon()` LAGI setelah set `state`.
