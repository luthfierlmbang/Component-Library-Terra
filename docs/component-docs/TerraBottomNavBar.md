# TerraBottomNavBar

Bottom navigation bar 3 item (Home/Activity/Notification) + state none.

`components/src/main/java/com/terra/design/components/TerraBottomNavBar.kt` · base: `LinearLayout`

## Kapan Dipakai

Bottom navigation bar utama app, fixed 3 tab (Home/Activity/Notification). Bukan buat tab bar custom dengan jumlah item beda — komponen ini hardcode 3 slot, gak bisa ditambah/dikurangi tab-nya.

## Do

- Set `terraBottomNavHomeIcon`/`terraBottomNavActivityIcon`/`terraBottomNavNotificationIcon` eksplisit di XML — jangan andalkan fallback default (`information`/`search`/`settings` icon), itu placeholder generik yang salah kontekstual buat production.
- Update `selectedItem` mengikuti navigasi aktual (fragment/activity yang lagi aktif), satu Activity/Fragment root biasanya set ini sekali saat entry.

## Don't

- Jangan biarkan icon nav pakai fallback default di production — selalu override ketiga attr icon.
- Jangan pakai komponen ini kalau app butuh lebih/kurang dari 3 tab — struktur ini fixed 3 item + state none, bukan dynamic list.

## XML Attrs (styleable `TerraBottomNavBar`)

| Attr | Format | Default |
|---|---|---|
| `app:terraBottomNavHomeText` | string | — |
| `app:terraBottomNavActivityText` | string | — |
| `app:terraBottomNavNotificationText` | string | — |
| `app:terraBottomNavHomeIcon` | reference | fallback `R.drawable.information` |
| `app:terraBottomNavActivityIcon` | reference | fallback `R.drawable.search` |
| `app:terraBottomNavNotificationIcon` | reference | fallback `R.drawable.settings` |
| `app:terraBottomNavSelectedItem` | enum | `none` |

`terraBottomNavSelectedItem`: `home` \| `activity` \| `notification` \| `none`

## Public API (Kotlin)

```kotlin
var selectedItem: Item = Item.NONE   // set memicu applySelection()
```

Enum: `Item { HOME, ACTIVITY, NOTIFICATION, NONE }`.

## Contoh XML

```xml
<com.terra.design.components.TerraBottomNavBar
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    app:terraBottomNavSelectedItem="home"
    app:terraBottomNavHomeText="Beranda"
    app:terraBottomNavActivityText="Aktivitas"
    app:terraBottomNavNotificationText="Notifikasi" />
```

## Token dipakai

- **Color**: `terra_color_bg_default`, `terra_color_bg_fill_primary_default`, `terra_color_text_secondary_default`
- **Typography**: `TextAppearance.Terra.Caption`
- **Dimens**: tidak ada token dimens dipakai di Kotlin — semua padding/margin hardcoded langsung di `terra_bottom_nav_bar.xml` (lihat catatan di bawah).

## Catatan

Fallback icon default (`R.drawable.information`, `R.drawable.search`, `R.drawable.settings`) dipakai kalau attr icon tidak di-set — perhatikan ini bisa salah kontekstual kalau lupa override icon per item. Ada juga hardcoded dimens (32dp, 5dp, 2dp) di `terra_bottom_nav_bar.xml`, belum pakai token spacing.
