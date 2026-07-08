# TerraActivityCard

Card riwayat aktivitas: label, nama aktivitas, waktu, aksi opsional. Reuse `TerraButton` untuk CTA.

`components/src/main/java/com/terra/design/components/TerraActivityCard.kt` · base: `LinearLayout`

## Kapan Dipakai

Item riwayat/log aktivitas dalam list (mis. list transaksi, timeline notifikasi) — label kategori + nama aktivitas + waktu + aksi opsional (Lihat Detail). Biasanya dipakai berulang di `RecyclerView`.

## Do

- Set `terraShowAction="false"` kalau item riwayat gak punya aksi lanjutan (murni informasi).
- Pakai di dalam list/RecyclerView untuk riwayat berurutan — ini use-case utamanya.
- Isi `terraActivityTime` dengan format waktu yang konsisten di seluruh list (mis. selalu "HH:mm" atau selalu relatif "2 jam lalu"), jangan campur format dalam satu list.

## Don't

- Jangan pakai untuk card detail single-entity dengan profil — pakai `TerraInformationCard`.
- Jangan hardcode dp baru kalau nambah elemen ke layout ini — pakai token spacing (`terra_spacing_*`) yang sudah ada, ikutin token, jangan nambah hardcoded value baru (styling saat ini sendiri sudah menyalahi ini, jangan tambah lagi — lihat catatan di bawah).

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

## Token dipakai

- **Color**: `terra_color_bg_default`, `terra_color_bg_fill_secondary_default`, `terra_color_text_inverse_default`, `terra_color_text_primary_default`
- **Dimens**: `terra_radius_8`, `terra_spacing_16`, `terra_spacing_4`
- **Typography**: `TextAppearance.Terra.Body.Medium.Regular`

## Catatan

Banyak dimensi (padding, margin) di `terra_activity_card.xml` masih hardcoded dp (8dp, 2dp, 12dp, 4dp, 1dp, 16dp) — belum pakai token `terra_spacing_*`. Lihat [tokens.md](tokens.md).
