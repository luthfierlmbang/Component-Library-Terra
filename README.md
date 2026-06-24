# TERRA Design System

Android XML design system library built from a single shared Figma source of truth.

Tujuan repo ini adalah nyediain fondasi UI yang bisa dipakai banyak squad dalam satu aplikasi Android, dengan output utama berupa:
- `design-tokens` untuk token desain
- `components` untuk reusable custom view XML
- `sample` untuk preview dan eksplorasi komponen di Android Studio
- artefak library yang nantinya bisa didistribusikan sebagai `AAR`

## Apa itu token?

Token adalah representasi teknis dari keputusan desain yang dipakai berulang.

Versi gampangnya:
- designer ngomong: "pakai orange utama", "radius 8", "body medium bold"
- di code, itu jangan di-hardcode berkali-kali
- keputusan itu disimpan sebagai token, lalu dipakai ulang di semua komponen

Contoh token:
- color token: `terra_color_text_primary_default`
- spacing token: `terra_spacing_8`
- radius token: `terra_radius_8`
- size token: `terra_size_icon_24`
- typography token: `TextAppearance.Terra.Body.Medium.Bold`

## Kenapa token penting?

Karena token bikin library ini:
- **konsisten** — warna, font, spacing, radius tidak liar per squad
- **mudah diubah** — kalau desain berubah, update di token dulu
- **mudah diskalakan** — komponen baru tinggal konsumsi token yang sudah ada
- **lebih aman** — ngurangin hardcoded value yang bikin UI cepat berantakan

## Arsitektur repo

Repo ini sekarang dibagi jadi 3 module utama:

### `design-tokens`
Android library yang berisi resource token reusable:
- colors
- dimens
- typography styles
- fonts
- shadow reference

Ini layer paling bawah. Komponen visual seharusnya ambil nilai dari sini dulu.

### `components`
Android library yang berisi reusable custom views berbasis XML + Kotlin.

Module ini mengonsumsi `design-tokens` dan membungkus hasil slicing Figma jadi komponen seperti:
- `TerraButton`
- `TerraMenuCard`
- `TerraInformationCard`
- `TerraActivityCard`
- `TerraChip`
- `TerraIncentiveMetricCard`
- `TerraIncentiveInfoCard`
- `TerraImageUpload`
- `TerraLabelBadge`
- `TerraBottomNavBar`
- `TerraSearchBar`
- `TerraRadioButton`
- `TerraCheckbox`
- `TerraTextField`

### `sample`
Android app kecil untuk preview komponen di Android Studio, emulator, atau device.

Ini bukan library yang akan didistribusikan. Fungsinya buat:
- cek visual cepat
- lihat state/variant komponen
- bantu slicing iteration tanpa harus nunggu integrasi ke app utama

## Struktur file

Struktur utamanya sekarang kurang lebih begini:

```text
.
├── README.md
├── docs/
│   ├── current-summary.md
│   └── icon-index.md
├── design-tokens/
│   └── src/main/res/
│       ├── font/
│       └── values/
│           ├── colors.xml
│           ├── dimens.xml
│           └── styles.xml
├── components/
│   └── src/main/
│       ├── java/com/terra/design/components/
│       │   ├── TerraButton.kt
│       │   ├── TerraChip.kt
│       │   ├── TerraTextField.kt
│       │   └── ...
│       └── res/
│           ├── drawable/
│           ├── layout/
│           └── values/
│               └── attrs.xml
├── sample/
│   └── src/main/
│       ├── java/com/terra/design/sample/
│       └── res/layout/
│           └── activity_main.xml
├── build.gradle.kts
└── settings.gradle.kts
```

## Detail token yang sudah ada

### Typography
Typography tokens sudah disiapkan sebagai Android `TextAppearance`, termasuk:
- `TextAppearance.Terra.Header.XL`
- `TextAppearance.Terra.Header.M`
- `TextAppearance.Terra.Header.S`
- `TextAppearance.Terra.Body.Medium.Bold`
- `TextAppearance.Terra.Body.Medium.Regular`
- `TextAppearance.Terra.Body.Small.Bold`
- `TextAppearance.Terra.Body.Small.Regular`
- `TextAppearance.Terra.Caption`
- `TextAppearance.Terra.Button.Medium`
- `TextAppearance.Terra.Button.Small`

### Fonts
Bundled Android fonts:
- `@font/jenius_sans_regular`
- `@font/jenius_sans_bold`

### Colors
Token warna dibagi dua level:

#### Primitive colors
Warna mentah dasar, misalnya:
- neutral / grey
- surface
- overlay
- shadow
- orange brand
- teal brand
- yellow status
- red status

#### Semantic colors
Warna yang sudah diberi makna penggunaan, misalnya:
- background
- text
- border
- icon

Contoh prinsipnya:
- primitive = "warna aslinya apa"
- semantic = "dipakai untuk apa"

Jadi komponen sebaiknya lebih sering pakai semantic token daripada primitive token langsung.

### Dimension tokens
Beberapa dimension token yang sudah tersedia:
- `terra_spacing_4`
- `terra_spacing_8`
- `terra_spacing_16`
- `terra_spacing_20`
- `terra_spacing_24`
- `terra_spacing_32`
- `terra_radius_6`
- `terra_radius_8`
- `terra_size_icon_20`
- `terra_size_icon_24`
- `terra_size_button_height_small`
- `terra_size_button_height_normal`

## Prinsip implementasi komponen

Semua komponen hasil slicing Figma idealnya mengikuti aturan ini:
- pakai tokenized colors
- pakai tokenized typography
- pakai bundled fonts
- pakai tokenized spacing/radius/size kalau sudah tersedia
- hindari hardcoded value kalau token yang setara sudah ada
- satu reusable component lebih baik daripada banyak layout terpisah kalau bedanya cuma variant/state

## Komponen yang sudah ada

Saat ini first-pass implementation yang sudah ada di module `components`:
- `TerraButton`
- `TerraMenuCard`
- `TerraInformationCard`
- `TerraActivityCard`
- `TerraChip`
- `TerraIncentiveMetricCard`
- `TerraIncentiveInfoCard`
- `TerraImageUpload`
- `TerraLabelBadge`
- `TerraBottomNavBar`
- `TerraSearchBar`
- `TerraRadioButton`
- `TerraCheckbox`
- `TerraTextField`

Status detail progress dan keputusan slicing ada di:
- `docs/current-summary.md`

Daftar icon hasil extract ada di:
- `docs/icon-index.md`

## Cara preview di Android Studio

### Opsi 1 — buka sample app
1. Sync Gradle
2. Jalankan module `sample`
3. Buka `sample/src/main/res/layout/activity_main.xml` untuk lihat preview XML yang dipasang
4. Run ke emulator/device kalau mau lihat interaksi runtime

### Opsi 2 — lihat layout komponen langsung
Beberapa layout komponen bisa dibuka langsung di Layout Editor untuk cek struktur dasarnya.

## Cara pakai module komponen

Kalau nanti module ini dipakai dari project Android lain, pola dasarnya seperti ini:

```kotlin
implementation(project(":design-tokens"))
implementation(project(":components"))
```

Contoh pemakaian komponen:

```xml
<com.terra.design.components.TerraButton
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:text="Button"
    app:terraButtonType="primary"
    app:terraButtonSize="normal" />
```

Contoh text field:

```xml
<com.terra.design.components.TerraTextField
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:hint="Place Holder"
    app:terraTextFieldHasLabel="true"
    app:terraTextFieldLabelText="Label"
    app:terraTextFieldState="default"
    app:terraTextFieldType="default" />
```

## Build AAR

Karena library dipisah per module, AAR akan dihasilkan per library module:
- `design-tokens`
- `components`

Command umum:

```bash
./gradlew :design-tokens:assembleRelease :components:assembleRelease
```

Output biasanya ada di:
- `design-tokens/build/outputs/aar/`
- `components/build/outputs/aar/`

Untuk distribusi final, module `components` adalah artefak utama UI component library, sedangkan `design-tokens` bisa tetap dipublish terpisah atau dijadikan dependency internal sesuai strategi release.

## Catatan icon

- icon vector yang aman untuk Android sebaiknya dikonversi ke `VectorDrawable XML`
- asset yang image-based lebih aman tetap `PNG`
- jangan taruh raw `.svg` langsung sebagai drawable produksi tanpa conversion path yang jelas

## Roadmap pendek

Next yang paling masuk akal sekarang:
1. refine parity visual komponen yang sudah ada
2. lanjut slicing komponen Figma berikutnya
3. siapkan publishing flow ke Nexus
4. rapihin API dan dokumentasi konsumsi library

## Source of truth

Design source utama berasal dari satu shared Figma file yang dipakai sebagai source of truth untuk:
- visual appearance
- variant structure
- nested structure
- token mapping

Kalau mau lihat progress implementasi paling update, cek:
- `docs/current-summary.md`
