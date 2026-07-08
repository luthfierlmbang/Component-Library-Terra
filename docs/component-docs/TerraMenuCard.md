# TerraMenuCard

`components/src/main/java/com/terra/design/components/TerraMenuCard.kt` · base: `LinearLayout`

## Kapan Dipakai

Item menu navigasi bergaya list row (mis. "Pengaturan", "Bantuan") — lead icon + label + trailing icon (biasanya chevron), full-width row yang bisa diklik.

## Do

- Set `terraShowLeadIcon`/`terraShowRightIcon` di XML sesuai kebutuhan compose-time; kalau icon mau di-hide, JANGAN panggil `setLeadIconResource()`/`setRightIconResource()` sesudahnya (lihat Don't).
- Pakai untuk list menu/settings, bukan untuk card info/summary — untuk itu pakai `TerraInformationCard`/`TerraActivityCard`.

## Don't

- Jangan panggil `setLeadIconResource()`/`setRightIconResource()` kalau attr `terraShowLeadIcon`/`terraShowRightIcon` di-set `false` di XML — method ini SELALU paksa icon visible, override flag show (bug diketahui). Kalau icon harus tetap hidden, jangan panggil method-nya.
- Jangan tumpuk banyak field text/value di komponen ini — desainnya cuma single label + 2 icon slot, bukan card multi-info (pakai `TerraInformationCard` untuk itu).

## XML Attrs (styleable `TerraMenuCard`)

| Attr | Format | Default |
|---|---|---|
| `android:text` | string | — |
| `app:terraLeadIcon` / `terraShowLeadIcon` | reference / boolean | `true` |
| `app:terraRightIcon` / `terraShowRightIcon` | reference / boolean | `true` |

## Public API (Kotlin)

```kotlin
fun setText(text: CharSequence?)
fun setLeadIconResource(@DrawableRes resId: Int)
fun setRightIconResource(@DrawableRes resId: Int)
```

## Contoh XML

```xml
<com.terra.design.components.TerraMenuCard
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:text="Pengaturan"
    app:terraLeadIcon="@drawable/ic_settings"
    app:terraShowLeadIcon="true"
    app:terraShowRightIcon="true" />
```

## Token dipakai

- **Color**: `terra_color_bg_default`, `terra_color_icon_primary_default`, `terra_color_text_primary_default`
- **Dimens**: `terra_radius_8`, `terra_spacing_16`, `terra_spacing_24`
- **Typography**: `TextAppearance.Terra.Header.S`

## ⚠️ Known issue

`setLeadIconResource()` / `setRightIconResource()` dipanggil setelah init selalu paksa icon jadi visible, override attr `terraShowLeadIcon`/`terraShowRightIcon` yang di-set di XML.
