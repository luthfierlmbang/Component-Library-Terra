# TerraSearchBar

`components/src/main/java/com/terra/design/components/TerraSearchBar.kt` · base: `LinearLayout`

## Kapan Dipakai

Search input di top bar / halaman list yang bisa difilter. Punya 4 state visual: `stateDefault` (belum interaksi), `focus` (lagi diketik), `result` (ada hasil/query terisi), `disabled`.

## Do

- Update `state` mengikuti lifecycle interaksi: `stateDefault` → `focus` saat field di-tap → `result` saat ada teks/hasil pencarian.
- Kalau butuh custom trailing icon (misal icon clear "x" custom) DAN state berubah-ubah runtime, panggil `setTrailingIcon()` ULANG setiap habis ganti `state` (lihat Known issue — jangan cuma set sekali di awal lalu berharap bertahan).

## Don't

- Jangan set `terraSearchBarTrailingIcon` di XML lalu ubah `state` secara runtime dan berharap custom icon-nya tetap — bakal ke-reset ke default tiap `state` berubah (bug diketahui, `TerraSearchBar.kt:109`). Selalu re-set trailing icon manual setelah ganti state kalau butuh custom icon.
- Jangan pakai buat text input biasa (nama, form) — pakai `TerraTextField`.

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
