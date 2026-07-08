# TerraIncentiveInfoCard

Card info insentif sederhana: title, description, lead icon, right icon.

`components/src/main/java/com/terra/design/components/TerraIncentiveInfoCard.kt` · base: `LinearLayout`

## Kapan Dipakai

Card info deskriptif ringkas terkait insentif (title + description + 2 icon), bukan untuk angka metrik besar (pakai `TerraIncentiveMetricCard`).

## Do

- Selalu set `terraInfoCardLeadIcon`/`terraInfoCardRightIcon` eksplisit di XML (lihat Known issue — tidak ada guard `resId == 0`, wajib diisi).
- Pakai buat pesan informasi singkat 1-2 kalimat (`terraInfoCardDescription`) — bukan paragraf panjang.

## Don't

- Jangan biarkan attr icon kosong — beda dari komponen lain, ini gak ada fallback aman kalau `resId` gak di-set.
- Jangan pakai untuk highlight angka/metrik — pakai `TerraIncentiveMetricCard`.

## XML Attrs (styleable `TerraIncentiveInfoCard`)

| Attr | Format | Default |
|---|---|---|
| `app:terraInfoCardTitle` | string | — |
| `app:terraInfoCardDescription` | string | — |
| `app:terraInfoCardLeadIcon` | reference | — |
| `app:terraInfoCardRightIcon` | reference | — |

## Public API (Kotlin)

Tidak ada property/method publik selain attr.

## Contoh XML

```xml
<com.terra.design.components.TerraIncentiveInfoCard
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    app:terraInfoCardTitle="Info Insentif"
    app:terraInfoCardDescription="Insentif cair tiap tanggal 1"
    app:terraInfoCardLeadIcon="@drawable/ic_info"
    app:terraInfoCardRightIcon="@drawable/ic_arrow" />
```

## Token dipakai

- **Color**: `terra_color_bg_default`, `terra_color_icon_primary_default`, `terra_color_text_primary_default`, `terra_color_text_secondary_default`
- **Dimens**: `terra_radius_8`, `terra_spacing_16`, `terra_spacing_8`
- **Typography**: `TextAppearance.Terra.Caption`, `Header.S`

## ⚠️ Known issue

Icon selalu di-tint (hardcode `tint=true`), dan `setIcon()` tidak guard `resId == 0` — beda dari pola aman di komponen lain (`isVisible = resId != 0`). Selalu set attr icon eksplisit di XML untuk komponen ini.
