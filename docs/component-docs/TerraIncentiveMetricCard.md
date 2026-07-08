# TerraIncentiveMetricCard

Card metrik insentif dengan background gradient sesuai variant.

`components/src/main/java/com/terra/design/components/TerraIncentiveMetricCard.kt` · base: `LinearLayout`

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

## ⚠️ Known issue

`setIcon()` internal tidak guard `resId == 0` (beda dari komponen lain yang pakai `isVisible = resId != 0`). Kalau `terraMetricLeadIcon`/`terraMetricRightIcon` tidak di-set di XML, berpotensi behavior gak terduga — selalu set attr icon eksplisit untuk komponen ini.
