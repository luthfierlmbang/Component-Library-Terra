# TerraButton

Custom button, 6 type x 2 size dalam satu View.

`components/src/main/java/com/terra/design/components/TerraButton.kt` · base: `LinearLayout`

## Kapan Dipakai

Semua tombol aksi (CTA) di layout Terra — primary action, secondary action, aksi danger (hapus/tolak), tombol text-only, tombol outline. Ini komponen tombol satu-satunya di library; jangan bikin `Button`/`MaterialButton` baru buat kebutuhan yang bisa ditutup 6 `terraButtonType` yang ada.

## Do

- Pakai `terraButtonType` sesuai hierarki aksi: `primary` untuk aksi utama layar (max 1 per layar/section), `secondary` untuk aksi kedua, `outlinedPrimary`/`outlinedSecondary` untuk aksi tersier, `danger` khusus aksi destruktif (hapus/batal/tolak), `textButton` untuk aksi low-emphasis (skip, lihat semua).
- Pakai `terraButtonSize="small"` di ruang sempit (row list, inline card action); default `normal` untuk CTA utama.
- Set icon lewat attr XML (`app:terraLeftIcon`/`app:terraRightIcon`) saat compose-time; pakai `setLeftIconResource()`/`setRightIconResource()` cuma kalau icon berubah dinamis runtime.
- Reuse `TerraButton` sebagai child di komponen card lain (`TerraInformationCard`, `TerraActivityCard`, `TerraImageUpload` sudah begini) — jangan bikin tombol custom baru di dalam card baru.

## Don't

- Jangan bikin View button baru (`Button`, `MaterialButton`, custom) buat use-case yang sudah tercover 6 type di atas.
- Jangan hardcode warna/dp/font di tombol baru — semua styling `TerraButton` sudah token-driven (`applyStyle()`), contoh yang harus diikuti komponen lain.
- Jangan pasang lebih dari satu `primary` button yang keliatan bersamaan di satu layar/section — rusak hierarki visual.
- Jangan panggil `setLeftIconResource`/`setRightIconResource` berulang di loop/onBind tanpa perlu — icon-nya statis per state, cukup di-set sekali.

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
