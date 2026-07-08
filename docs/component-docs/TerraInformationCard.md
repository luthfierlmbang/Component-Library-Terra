# TerraInformationCard

Card profil + info key-value + tombol aksi. Reuse `TerraButton` untuk CTA.

`components/src/main/java/com/terra/design/components/TerraInformationCard.kt` · base: `LinearLayout`

## Kapan Dipakai

Card detail entitas: profil + status + sampai 2 pasang label-value + tombol aksi opsional (mis. detail nasabah/pinjaman). Bukan untuk log aktivitas berurutan (pakai `TerraActivityCard`) atau metrik angka besar (pakai `TerraIncentiveMetricCard`).

## Do

- Set `terraShowProfile="false"` kalau card gak butuh header foto+nama (mis. card info tanpa konteks personal).
- Set `terraShowButton="false"` kalau card murni display, tanpa aksi lanjutan.
- Manfaatkan 2 slot label-value (`terraInfoLabel1/Value1`, `terraInfoLabel2/Value2`) — jangan paksa lebih dari 2 pasang, kalau butuh lebih, pertimbangkan komponen/layout baru, jangan hack dengan concat string ke satu value.

## Don't

- Jangan pakai buat riwayat/log aktivitas berurutan — pakai `TerraActivityCard`.
- Jangan pakai buat angka metrik besar dengan gradient background — pakai `TerraIncentiveMetricCard`.
- Jangan lupa isi `terraButtonText` kalau `terraShowButton="true"` — tombol kosong tanpa label bakal aneh di UI.

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

## Token dipakai

- **Color**: `terra_color_bg_default`, `terra_color_text_primary_default`, `terra_color_text_primary_disabled`, `terra_color_text_secondary_default`
- **Dimens**: `terra_radius_8`, `terra_spacing_16`, `terra_spacing_4`
- **Typography**: `TextAppearance.Terra.Body.Medium.Bold`, `Body.Medium.Regular`, `Body.Small.Bold`, `Body.Small.Regular`

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
