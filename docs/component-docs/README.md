# Terra Component Docs

Dokumentasi API tiap komponen di module `components` + design token di module `design-tokens`.

Sumber: source code aktual (`components/src/main/java/com/terra/design/components/`, `components/src/main/res/values/attrs.xml`, `design-tokens/src/main/res/values/`), bukan hasil generate otomatis — jadi dijaga sinkron manual tiap ada perubahan komponen.

## Untuk AI agent

Dokumen ini ditulis buat jadi rujukan AI agent (Claude Code atau sejenis) saat generate/edit layout & kode yang pakai Terra design system. Tiap file komponen punya section **"Kapan Dipakai"** dan **"Do"/"Don't"** — baca itu SEBELUM pilih komponen atau nulis kode:

- Sebelum bikin View/komponen baru dari nol, cek dulu apa sudah ada komponen Terra yang cocok (lihat daftar di bawah + "Kapan Dipakai" tiap file).
- Ikuti "Do" — itu pola pemakaian yang benar sesuai desain komponennya.
- Jangan langgar "Don't" — biasanya nunjuk ke bug/limitation nyata di source (lihat juga Known issues di bawah), bukan sekadar gaya preferensi.
- Kalau butuh styling baru (warna/spacing/font), cek [tokens.md](tokens.md) dulu — jangan hardcode value kalau token yang setara sudah ada.

## Isi

- [tokens.md](tokens.md) — design token: color, dimens, typography
- Komponen (satu file per komponen):
  - [TerraButton.md](TerraButton.md)
  - [TerraTextField.md](TerraTextField.md)
  - [TerraCheckbox.md](TerraCheckbox.md)
  - [TerraRadioButton.md](TerraRadioButton.md)
  - [TerraChip.md](TerraChip.md)
  - [TerraSearchBar.md](TerraSearchBar.md)
  - [TerraMenuCard.md](TerraMenuCard.md)
  - [TerraInformationCard.md](TerraInformationCard.md)
  - [TerraActivityCard.md](TerraActivityCard.md)
  - [TerraIncentiveMetricCard.md](TerraIncentiveMetricCard.md)
  - [TerraIncentiveInfoCard.md](TerraIncentiveInfoCard.md)
  - [TerraLabelBadge.md](TerraLabelBadge.md)
  - [TerraBottomNavBar.md](TerraBottomNavBar.md)
  - [TerraImageUpload.md](TerraImageUpload.md)

## Known issues lintas-komponen

Dicatat di sini biar gak keulang di tiap file:

1. **`TerraChip.setLeftIconResource/setRightIconResource`** dan **`TerraMenuCard.setLeadIconResource/setRightIconResource`** — dipanggil setelah init selalu paksa icon jadi visible, override attr `terraShowXIcon` yang di-set di XML.
2. **`TerraSearchBar`** — set property `state` setelah init memanggil ulang `applyStyle()`, yang di dalamnya memanggil `setTrailingIcon(0)` → trailing icon custom (dari attr atau `setTrailingIcon()`) ketimpa balik ke default tiap `state` berubah. Known limitation, belum ada fix.
3. **`TerraIncentiveInfoCard`** & **`TerraIncentiveMetricCard`** — `setIcon()` tidak guard `resId == 0` (beda dari komponen lain yang pakai `isVisible = resId != 0`). Kalau attr icon gak di-set di XML, berpotensi crash/behavior gak terduga.
4. **`TerraTextField`** — satu-satunya text input yang TIDAK expose `setText`/`getText`/`setHint` publik (beda dari `TerraSearchBar` yang punya). Text runtime cuma bisa diakses lewat `findViewById` manual ke child EditText, gak direkomendasikan dipakai dari luar.
5. Semua komponen: **tidak ada listener setter custom** — click handling pakai `View.setOnClickListener` bawaan Android langsung di root view komponen.
