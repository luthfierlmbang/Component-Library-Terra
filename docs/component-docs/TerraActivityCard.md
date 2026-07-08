# TerraActivityCard

Card riwayat aktivitas: label, nama aktivitas, waktu, aksi opsional. Reuse `TerraButton` untuk CTA.

`components/src/main/java/com/terra/design/components/TerraActivityCard.kt` · base: `LinearLayout`

## XML Attrs (styleable `TerraActivityCard`)

| Attr | Format | Default |
|---|---|---|
| `app:terraLabelText` | string | — |
| `app:terraLabelIcon` | reference | — |
| `app:terraRightIcon` | reference | — |
| `app:terraActivityName` | string | — |
| `app:terraActivityTime` | string | — |
| `app:terraTimeIcon` | reference | — |
| `app:terraActionText` | string | — |
| `app:terraShowAction` | boolean | `true` |

## Public API (Kotlin)

Tidak ada property/method publik selain attr.

## Contoh XML

```xml
<com.terra.design.components.TerraActivityCard
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    app:terraLabelText="Transaksi"
    app:terraActivityName="Setor Simpanan"
    app:terraActivityTime="10:00 WIB"
    app:terraActionText="Lihat Detail"
    app:terraShowAction="true" />
```

## Catatan

Banyak dimensi (padding, margin) di `terra_activity_card.xml` masih hardcoded dp (8dp, 2dp, 12dp, 4dp, 1dp, 16dp) — belum pakai token `terra_spacing_*`. Lihat [tokens.md](tokens.md).
