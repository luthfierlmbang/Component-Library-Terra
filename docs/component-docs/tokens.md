# Design Tokens

## Sumber token (dari mana ambilnya)

Semua token didefinisikan di module **`design-tokens`**, module paling bawah di dependency graph (`sample → components → design-tokens`). `components` mengonsumsi lewat `project(":design-tokens")` di `components/build.gradle.kts`, jadi tiap resource `R.color.terra_*` / `R.dimen.terra_*` / `R.style.TextAppearance_Terra_*` yang dipanggil di kode `components` sebenarnya datang dari module ini, bukan didefinisikan ulang di `components`.

File sumber per kategori:

| Kategori | File |
|---|---|
| Color (primitive + semantic) | `design-tokens/src/main/res/values/colors.xml` |
| Dimens (spacing, radius, size) | `design-tokens/src/main/res/values/dimens.xml` |
| Typography (`TextAppearance.Terra.*`) & shadow style | `design-tokens/src/main/res/values/styles.xml` |
| Font family (`jenius_sans_bold`/`_regular`) | `design-tokens/src/main/res/font/` |

2 layer warna: **primitive** (raw hex, mis. `terra_color_grey_500`) → **semantic** (alias by-purpose, mis. `terra_color_text_primary_default` → nunjuk ke primitive). Semua nama prefix `terra_*`. Komponen di module `components` **hanya boleh** pakai token semantic/dimens/typography ini (lewat `@color/`, `@dimen/`, `@style/` di XML atau `R.color.`/`R.dimen.`/`R.style.` di Kotlin) — bukan hardcode hex/dp, walau kenyataannya masih ada pelanggaran (lihat catatan tiap section & [README.md](README.md) known issues).

## Color

### Primitive — Neutral (`terra_color_grey_*`)

| Token | Hex |
|---|---|
| `terra_color_grey_50` | `#FFFFFF` |
| `terra_color_grey_100` | `#EBF0F4` |
| `terra_color_grey_200` | `#EDEDED` |
| `terra_color_grey_300` | `#DADADA` |
| `terra_color_grey_400` | `#CCCCCC` |
| `terra_color_grey_500` | `#999999` |
| `terra_color_grey_600` | `#7A7D7F` |
| `terra_color_grey_700` | `#555555` |
| `terra_color_grey_800` | `#333333` |
| `terra_color_grey_900` | `#000000` |

### Primitive — Surface / Overlay / Shadow

| Token | Hex |
|---|---|
| `terra_color_surface_blue_50` | `#F5FAFE` |
| `terra_color_surface_pink_50` | `#FFF8F8` |
| `terra_color_overlay_black_50` | `#80000000` |
| `terra_color_shadow_black_12` | `#1F000000` |

### Primitive — Brand Orange (`terra_color_orange_*`)

50→900: `#FFF5ED`, `#FDE8D6`, `#FBCFA8`, `#F8B67B`, `#F69C4D`, `#F4831F`, `#D2711B`, `#B15F16`, `#8F4D12`, `#6E3B0E`

### Primitive — Brand Teal (`terra_color_teal_*`)

50→900: `#EBF4F4`, `#D9EAEB`, `#B5D6D8`, `#91C3C5`, `#6DAFB2`, `#4A9B9F`, `#26888C`, `#027479`, `#015458`, `#013436`

### Primitive — Status Yellow (`terra_color_yellow_*`)

50→900: `#FEF9ED`, `#FEF0D5`, `#FCE0A6`, `#FACF77`, `#F9BF48`, `#F7AE19`, `#D59616`, `#B37E12`, `#91660F`, `#6F4E0B`

### Primitive — Status Red (`terra_color_red_*`)

50→900: `#FEEFEF`, `#FCDADA`, `#F9B1B1`, `#F68888`, `#F25F5F`, `#EF3636`, `#CE2F2F`, `#AD2727`, `#8C2020`, `#6C1818`

### Semantic — Background

| Token | Map ke |
|---|---|
| `terra_color_bg_default` | `grey_50` |
| `terra_color_bg_surface_primary_default` | `grey_50` |
| `terra_color_bg_surface_secondary_default` | `grey_200` |
| `terra_color_bg_surface_secondary_hover` | `grey_100` |
| `terra_color_bg_fill_primary_default` / `_active` | `orange_500` |
| `terra_color_bg_fill_secondary_default` | `teal_700` |
| `terra_color_bg_fill_danger_default` | `red_500` |
| `terra_color_bg_fill_warning_default` | `yellow_500` |
| `terra_color_bg_disabled_default` | `grey_400` |
| `terra_color_bg_overlay_default` | `overlay_black_50` |

### Semantic — Text

| Token | Map ke |
|---|---|
| `terra_color_text_primary_default` | `grey_800` |
| `terra_color_text_primary_disabled` | `grey_400` |
| `terra_color_text_secondary_default` | `grey_700` |
| `terra_color_text_inverse_default` | `grey_50` |
| `terra_color_text_action_primary_default` | `teal_700` |
| `terra_color_text_action_danger_default` | `red_500` |

### Semantic — Border

| Token | Map ke |
|---|---|
| `terra_color_border_default` | `grey_200` |
| `terra_color_border_secondary_default` | `grey_400` |
| `terra_color_border_tertiary_default` | `grey_300` |
| `terra_color_border_action_primary_default` | `teal_700` |
| `terra_color_border_action_secondary_default` | `orange_500` |
| `terra_color_border_disabled_default` | `grey_400` |

### Semantic — Icon

| Token | Map ke |
|---|---|
| `terra_color_icon_primary_default` | `grey_800` |
| `terra_color_icon_secondary_default` | `grey_700` |
| `terra_color_icon_secondary_hover` | `teal_700` |
| `terra_color_icon_inverse_default` | `grey_50` |
| `terra_color_icon_disabled_default` | `grey_400` |

## Dimens

| Kategori | Token | Value |
|---|---|---|
| Spacing | `terra_spacing_4` | 4dp |
| Spacing | `terra_spacing_8` | 8dp |
| Spacing | `terra_spacing_16` | 16dp |
| Spacing | `terra_spacing_20` | 20dp |
| Spacing | `terra_spacing_24` | 24dp |
| Spacing | `terra_spacing_32` | 32dp |
| Radius | `terra_radius_6` | 6dp |
| Radius | `terra_radius_8` | 8dp |
| Icon size | `terra_size_icon_20` | 20dp |
| Icon size | `terra_size_icon_24` | 24dp |
| Button height | `terra_size_button_height_small` | 28dp |
| Button height | `terra_size_button_height_normal` | 40dp |

⚠️ Cakupan dimens token masih tipis — cuma cukup dipakai penuh oleh `TerraButton`. Komponen lain (card, chip, bottom nav, dll) banyak pakai angka dp hardcoded langsung di layout XML, belum ditarik ke token. Lihat catatan di masing-masing file komponen kalau ada.

## Typography (`styles.xml`, prefix `TextAppearance.Terra.*`)

Font: `jenius_sans_bold` / `jenius_sans_regular`.

| Style | Size | Line height | Letter spacing | Weight |
|---|---|---|---|---|
| `TextAppearance.Terra.Header.XL` | 28sp | 32sp | 0.011 | bold |
| `TextAppearance.Terra.Header.M` | 20sp | 26sp | 0.0125 | bold |
| `TextAppearance.Terra.Header.S` | 18sp | 22sp | 0.0111 | bold |
| `TextAppearance.Terra.Body.Medium.Bold` | 16sp | 22sp | — | bold |
| `TextAppearance.Terra.Body.Medium.Regular` | 16sp | 22sp | — | normal |
| `TextAppearance.Terra.Body.Small.Bold` | 14sp | 22sp | — | bold |
| `TextAppearance.Terra.Body.Small.Regular` | 14sp | 20sp | — | normal |
| `TextAppearance.Terra.Caption` | 12sp | 20sp | — | normal |
| `TextAppearance.Terra.Button.Medium` | 16sp | 24sp | 0.02 | bold, `textAllCaps=false` |
| `TextAppearance.Terra.Button.Small` | 14sp | 24sp | 0.02 | bold, `textAllCaps=false` |

## Shadow / Elevation

`Widget.Terra.Shadow.Card` — cuma `android:elevation="4dp"`. Catatan di source: XML Android gak bisa reproduce shadow Figma 1:1, style ini cuma panduan implementasi, dipasangkan manual dengan shape/background/color token.

## Dipakai di mana (token usage per komponen)

Hasil grep aktual `R.color.terra_*` / `R.dimen.terra_*` / `R.style.TextAppearance_Terra_*` di tiap file Kotlin komponen (`components/src/main/java/com/terra/design/components/*.kt`). Semua 14 komponen konsumsi token dari `design-tokens` — tidak ada yang nol.

| Komponen | Token dipakai |
|---|---|
| `TerraButton` | `bg_disabled_default`, `bg_fill_danger_default`, `bg_fill_primary_default`, `bg_fill_secondary_default`, `border_action_primary_default`, `border_action_secondary_default`, `border_disabled_default`, `text_action_primary_default`, `text_inverse_default`, `text_primary_disabled`, `radius_6`, `radius_8`, `size_button_height_normal`, `size_button_height_small`, `size_icon_20`, `size_icon_24`, `spacing_16`, `spacing_32`, `spacing_4`, `spacing_8`, `Button.Medium`, `Button.Small` |
| `TerraTextField` | `bg_default`, `bg_fill_danger_default`, `bg_fill_primary_default`, `bg_surface_secondary_default`, `border_secondary_default`, `text_primary_default`, `text_primary_disabled`, `text_secondary_default`, `radius_8`, `Body.Medium.Bold`, `Body.Medium.Regular`, `Body.Small.Bold`, `Body.Small.Regular`, `Caption` |
| `TerraCheckbox` | `bg_default`, `grey_400`, `grey_500`, `text_primary_default`, `text_secondary_default`, `Body.Medium.Bold`, `Body.Medium.Regular` |
| `TerraRadioButton` | `bg_fill_primary_default`, `grey_400`, `grey_500`, `text_secondary_default`, `Body.Medium.Bold`, `Body.Medium.Regular` |
| `TerraChip` | `bg_fill_danger_default`, `border_action_primary_default`, `grey_500`, `teal_50`, `text_action_primary_default`, `radius_8`, `size_icon_20`, `spacing_16`, `spacing_4`, `spacing_8`, `Body.Small.Bold`, `Body.Small.Regular` |
| `TerraSearchBar` | `bg_default`, `bg_fill_primary_default`, `border_secondary_default`, `grey_200`, `text_primary_default`, `text_primary_disabled`, `radius_8`, `spacing_8`, `Body.Medium.Bold`, `Body.Medium.Regular` |
| `TerraMenuCard` | `bg_default`, `icon_primary_default`, `text_primary_default`, `radius_8`, `spacing_16`, `spacing_24`, `Header.S` |
| `TerraInformationCard` | `bg_default`, `text_primary_default`, `text_primary_disabled`, `text_secondary_default`, `radius_8`, `spacing_16`, `spacing_4`, `Body.Medium.Bold`, `Body.Medium.Regular`, `Body.Small.Bold`, `Body.Small.Regular` |
| `TerraActivityCard` | `bg_default`, `bg_fill_secondary_default`, `text_inverse_default`, `text_primary_default`, `radius_8`, `spacing_16`, `spacing_4`, `Body.Medium.Regular` |
| `TerraIncentiveMetricCard` | `radius_8`, `spacing_16`, `spacing_8`, `Body.Medium.Bold`, `Caption` — *(catatan: gradient background-nya di-hardcode manual di `applyGradient()`, bukan lewat token warna semantic)* |
| `TerraIncentiveInfoCard` | `bg_default`, `icon_primary_default`, `text_primary_default`, `text_secondary_default`, `radius_8`, `spacing_16`, `spacing_8`, `Caption`, `Header.S` |
| `TerraLabelBadge` | `bg_fill_danger_default`, `bg_fill_secondary_default`, `grey_500`, `icon_inverse_default`, `teal_700`, `text_inverse_default`, `spacing_20`, `spacing_4`, `spacing_8`, `Caption` |
| `TerraBottomNavBar` | `bg_default`, `bg_fill_primary_default`, `text_secondary_default`, `Caption` |
| `TerraImageUpload` | `bg_default`, `border_secondary_default`, `icon_primary_default`, `text_secondary_default`, `radius_8`, `Body.Small.Regular`, `Caption` |

Catatan: beberapa komponen (`TerraChip`, `TerraCheckbox`, `TerraRadioButton`, `TerraLabelBadge`, dll) langsung pakai **token primitive** (`grey_400`, `grey_500`, `teal_50`, `teal_700`) alih-alih semantic — sedikit inkonsisten dibanding komponen lain yang disiplin cuma pakai semantic layer.

## Status

Dokumen ini ditulis dari isi aktual `colors.xml`, `dimens.xml`, `styles.xml` per 2026-07-08. Catatan: `docs/token-structure.md` (dokumen lama di root `docs/`) sudah basi/gak sinkron — dokumen ini (`tokens.md`) yang jadi rujukan terbaru.
