# TerraChip

`components/src/main/java/com/terra/design/components/TerraChip.kt` · base: `LinearLayout`

## Kapan Dipakai

Filter/tag toggle-able (kategori filter, tag pilihan) dengan opsi counter (badge angka). Bukan buat status statis non-interaktif — untuk itu pakai [TerraLabelBadge](TerraLabelBadge.md).

## Do

- Toggle `terraChipSelected` sebagai response ke tap user (biasanya di `setOnClickListener`, toggle manual — component gak auto-toggle sendiri saat diklik).
- Pakai `terraChipHasCounter` + `terraChipCounterText` buat nunjukin jumlah (mis. "Kategori (3)").
- Set icon lewat attr XML di compose-time bila statis.

## Don't

- Jangan panggil `setLeftIconResource()`/`setRightIconResource()` kalau maksudnya icon harus tetap hidden sesuai attr `terraChipShowLeftIcon="false"`/`terraChipShowRightIcon="false"` — method ini SELALU paksa `visible=true`, override attr (lihat Known issue di bawah). Kalau butuh icon hidden secara kondisional, jangan panggil method-nya sama sekali di kondisi itu.
- Jangan pakai `TerraChip` buat badge status read-only (mis. "Aktif"/"Gagal") — pakai `TerraLabelBadge`.

## XML Attrs (styleable `TerraChip`)

| Attr | Format | Default |
|---|---|---|
| `android:text` | string | — |
| `app:terraChipSelected` | boolean | `false` |
| `app:terraChipLeftIcon` / `terraChipShowLeftIcon` | reference / boolean | — |
| `app:terraChipRightIcon` / `terraChipShowRightIcon` | reference / boolean | — |
| `app:terraChipHasCounter` | boolean | `false` |
| `app:terraChipCounterText` | string | — |

## Public API (Kotlin)

```kotlin
var terraChipSelected: Boolean = false

fun setText(text: CharSequence?)
fun setLeftIconResource(@DrawableRes resId: Int)
fun setRightIconResource(@DrawableRes resId: Int)
```

## Contoh XML

```xml
<com.terra.design.components.TerraChip
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:text="Filter"
    app:terraChipSelected="true"
    app:terraChipShowLeftIcon="true"
    app:terraChipLeftIcon="@drawable/ic_filter"
    app:terraChipHasCounter="true"
    app:terraChipCounterText="3" />
```

## Token dipakai

- **Color**: `terra_color_bg_fill_danger_default`, `terra_color_border_action_primary_default`, `terra_color_grey_500` (⚠️ primitive), `terra_color_teal_50` (⚠️ primitive), `terra_color_text_action_primary_default`
- **Dimens**: `terra_radius_8`, `terra_size_icon_20`, `terra_spacing_16`, `terra_spacing_4`, `terra_spacing_8`
- **Typography**: `TextAppearance.Terra.Body.Small.Bold`, `Body.Small.Regular`

## ⚠️ Known issue

`setLeftIconResource()` / `setRightIconResource()` dipanggil setelah init **selalu paksa icon jadi visible**, override attr `terraChipShowLeftIcon`/`terraChipShowRightIcon` yang di-set di XML. Kalau butuh icon tersembunyi dinamis, jangan pakai method ini setelah constructor selesai.
