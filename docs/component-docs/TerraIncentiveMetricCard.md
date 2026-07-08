# TerraIncentiveMetricCard

Card metrik insentif dengan background gradient sesuai variant.

`components/src/main/java/com/terra/design/components/TerraIncentiveMetricCard.kt` · base: `LinearLayout`

## Kapan Dipakai

Card highlight metrik/angka besar dengan background gradient bertema (dashboard insentif: jumlah customer aktif, total ditabung, pencairan). Bukan untuk card info deskriptif biasa — untuk itu pakai `TerraIncentiveInfoCard`.

## Do

- Pilih `terraMetricVariant` sesuai makna metrik: `customerActive` (gradient teal), `totalDitabung` (gradient hijau), `pencairan` (gradient biru) — jangan pilih variant asal, warnanya punya makna semantik per konteks dashboard.
- Selalu set `terraMetricLeadIcon`/`terraMetricRightIcon` eksplisit di XML (lihat Known issue — kalau tidak di-set, berisiko behavior gak terduga).

## Don't

- Jangan biarkan `terraMetricLeadIcon`/`terraMetricRightIcon` kosong/tidak di-set — komponen ini TIDAK guard `resId == 0` (beda dari komponen lain), harus selalu diisi.
- Jangan pakai untuk card deskripsi teks panjang — desainnya untuk angka/metrik ringkas, pakai `TerraIncentiveInfoCard` untuk deskripsi.

## XML Attrs (styleable `TerraIncentiveMetricCard`)

| Attr | Format | Default |
|---|---|---|
| `app:terraMetricTitle` | string | — |
| `app:terraMetricPeriod` | string | — |
| `app:terraMetricAmount` | string | — |
| `app:terraMetricLeadIcon` | reference | — |
| `app:terraMetricRightIcon` | reference | — |
| `app:terraMetricVariant` | enum | `customerActive` |

`terraMetricVariant`: `customerActive` (gradient teal) \| `totalDitabung` (gradient hijau) \| `pencairan` (gradient biru) — lihat `applyGradient()`.

## Public API (Kotlin)

Tidak ada property/method publik selain attr. Variant dikelola via `Int` mentah dari attr enum, bukan enum Kotlin nested.

## Contoh XML

```xml
<com.terra.design.components.TerraIncentiveMetricCard
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    app:terraMetricTitle="Customer Aktif"
    app:terraMetricPeriod="Bulan ini"
    app:terraMetricAmount="120"
    app:terraMetricVariant="customerActive"
    app:terraMetricLeadIcon="@drawable/ic_user"
    app:terraMetricRightIcon="@drawable/ic_arrow" />
```

## Token dipakai

- **Dimens**: `terra_radius_8`, `terra_spacing_16`, `terra_spacing_8`
- **Typography**: `TextAppearance.Terra.Body.Medium.Bold`, `Caption`
- **Color**: tidak ada token semantic langsung — background gradient di-hardcode manual per variant di `applyGradient()`, bukan lewat token warna.

## ⚠️ Known issue

`setIcon()` internal tidak guard `resId == 0` (beda dari komponen lain yang pakai `isVisible = resId != 0`). Kalau `terraMetricLeadIcon`/`terraMetricRightIcon` tidak di-set di XML, berpotensi behavior gak terduga — selalu set attr icon eksplisit untuk komponen ini.
