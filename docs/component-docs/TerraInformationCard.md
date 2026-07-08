# TerraInformationCard

Card profil + info key-value + tombol aksi. Reuse `TerraButton` untuk CTA.

`components/src/main/java/com/terra/design/components/TerraInformationCard.kt` · base: `LinearLayout`

## XML Attrs (styleable `TerraInformationCard`)

| Attr | Format | Default |
|---|---|---|
| `app:terraCardTitle` | string | — |
| `app:terraProfileName` | string | — |
| `app:terraProfileSubtitle` | string | — |
| `app:terraStatusLabel` | string | — |
| `app:terraInfoLabel1` / `terraInfoValue1` | string | — |
| `app:terraInfoLabel2` / `terraInfoValue2` | string | — |
| `app:terraButtonText` | string | — |
| `app:terraShowProfile` | boolean | `true` |
| `app:terraShowButton` | boolean | `true` |
| `app:terraProfileImage` | reference | — |

## Public API (Kotlin)

Tidak ada property/method publik selain attr — semua styling dibaca sekali saat `init`.

## Contoh XML

```xml
<com.terra.design.components.TerraInformationCard
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    app:terraCardTitle="Detail Pinjaman"
    app:terraProfileName="Budi Santoso"
    app:terraProfileSubtitle="Petani"
    app:terraStatusLabel="Aktif"
    app:terraInfoLabel1="Jumlah" app:terraInfoValue1="Rp1.000.000"
    app:terraInfoLabel2="Tenor" app:terraInfoValue2="12 bulan"
    app:terraShowProfile="true"
    app:terraShowButton="true"
    app:terraButtonText="Lihat Detail" />
```
