# TerraLabelBadge

Badge label kecil dengan 4 state warna + icon opsional.

`components/src/main/java/com/terra/design/components/TerraLabelBadge.kt` · base: `LinearLayout`

## Kapan Dipakai

Badge status read-only kecil (mis. "Aktif", "Gagal", "Pending") menempel di card/row lain. Bukan buat elemen interaktif/toggle-able — untuk itu pakai `TerraChip`.

## Do

- Pilih `state` sesuai makna status: `stateDefault`/`secondary` untuk status positif/netral, `neutral` untuk status non-committal, `failed` untuk status gagal/error/danger.
- Pakai sebagai anak/attachment ke komponen lain (card, row), bukan sebagai elemen berdiri sendiri di layout kosong.

## Don't

- Jangan bikin badge ini clickable/toggle — desainnya display-only. Kalau butuh interaktif, pakai `TerraChip`.
- Jangan pakai `state="failed"` buat status yang sebenarnya cuma netral/informational — bisa nyesatin user (warna failed biasanya merah/danger).

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

## Token dipakai

- **Color**: `terra_color_bg_fill_danger_default`, `terra_color_bg_fill_secondary_default`, `terra_color_grey_500` (⚠️ primitive), `terra_color_icon_inverse_default`, `terra_color_teal_700` (⚠️ primitive), `terra_color_text_inverse_default`
- **Dimens**: `terra_spacing_20`, `terra_spacing_4`, `terra_spacing_8`
- **Typography**: `TextAppearance.Terra.Caption`

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
