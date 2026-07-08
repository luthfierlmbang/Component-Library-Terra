# TerraCheckbox

`components/src/main/java/com/terra/design/components/TerraCheckbox.kt` · base: `LinearLayout`

## Kapan Dipakai

Multi-select pilihan (agree to T&C, filter multi-pilih, checklist item). Kalau cuma butuh 1 pilihan dari banyak opsi, pakai [TerraRadioButton](TerraRadioButton.md), bukan ini.

## Do

- Set `showLabel = true` / `terraCheckboxShowLabel="true"` + `android:text` kalau checkbox butuh label teks di sampingnya (paling umum). Tanpa label cuma dipakai kalau label sudah ada di luar (mis. row list yang textnya terpisah).
- Baca state via property `checked`, bukan cast ke `CompoundButton`/`CheckBox` Android bawaan.

## Don't

- Jangan pakai `TerraCheckbox` buat single-choice — itu tugas `TerraRadioButton`.
- Jangan pakai `android.widget.CheckBox` bawaan Android di layout yang sudah pakai Terra design system — styling gak bakal konsisten (warna/font beda dari token Terra).

## XML Attrs (styleable `TerraCheckbox`)

| Attr | Format | Default |
|---|---|---|
| `android:text` | string | — |
| `android:checked` | boolean | `false` |
| `android:enabled` | boolean | — |
| `app:terraCheckboxShowLabel` | boolean | `false` |

## Public API (Kotlin)

```kotlin
var checked: Boolean = false
var showLabel: Boolean = false   // toggle visibility label

fun setText(text: CharSequence?)
override fun setEnabled(enabled: Boolean)
```

## Token dipakai

- **Color**: `terra_color_bg_default`, `terra_color_grey_400`, `terra_color_grey_500` (⚠️ primitive, bukan semantic), `terra_color_text_primary_default`, `terra_color_text_secondary_default`
- **Typography**: `TextAppearance.Terra.Body.Medium.Bold`, `Body.Medium.Regular`

## Contoh XML

```xml
<com.terra.design.components.TerraCheckbox
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:text="Setuju dengan syarat & ketentuan"
    android:checked="true"
    app:terraCheckboxShowLabel="true" />
```
