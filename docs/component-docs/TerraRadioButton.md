# TerraRadioButton

`components/src/main/java/com/terra/design/components/TerraRadioButton.kt` · base: `LinearLayout`

## Kapan Dipakai

Single-choice dari sekumpulan opsi (pilih metode pembayaran, pilih 1 dari beberapa jawaban). Kalau multi-select, pakai [TerraCheckbox](TerraCheckbox.md).

## Do

- Grup beberapa `TerraRadioButton` secara manual (component ini TIDAK auto-exclusive kayak `RadioGroup` Android) — agent/developer harus handle logic "cuma 1 yang checked" sendiri di level parent (uncheck yang lain saat salah satu dipilih).
- Set `showLabel = true` + `android:text` kalau butuh label di samping radio.

## Don't

- Jangan asumsikan taruh banyak `TerraRadioButton` otomatis mutually-exclusive — tidak ada `RadioGroup` wrapper built-in di library ini, beda dari `android.widget.RadioButton`. Kalau lupa handle exclusivity manual, user bisa checked lebih dari satu.
- Jangan pakai buat multi-select — pakai `TerraCheckbox`.

## XML Attrs (styleable `TerraRadioButton`)

| Attr | Format | Default |
|---|---|---|
| `android:text` | string | — |
| `android:checked` | boolean | `false` |
| `android:enabled` | boolean | — |
| `app:terraRadioButtonShowLabel` | boolean | `false` |

## Public API (Kotlin)

```kotlin
var checked: Boolean = false
var showLabel: Boolean = false

fun setText(text: CharSequence?)
override fun setEnabled(enabled: Boolean)
```

## Token dipakai

- **Color**: `terra_color_bg_fill_primary_default`, `terra_color_grey_400` (⚠️ primitive), `terra_color_grey_500` (⚠️ primitive), `terra_color_text_secondary_default`
- **Typography**: `TextAppearance.Terra.Body.Medium.Bold`, `Body.Medium.Regular`

## Contoh XML

```xml
<com.terra.design.components.TerraRadioButton
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:text="Pilihan A"
    android:checked="true"
    app:terraRadioButtonShowLabel="true" />
```
