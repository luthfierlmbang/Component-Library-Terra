# TerraImageUpload

Komponen upload gambar dengan 3 state (upload/proses/attached), sampai 3 tombol aksi. Reuse `TerraButton` untuk baris aksi.

`components/src/main/java/com/terra/design/components/TerraImageUpload.kt` · base: `LinearLayout`

## XML Attrs (styleable `TerraImageUpload`)

| Attr | Format | Default |
|---|---|---|
| `app:terraImageUploadLabel` | string | — |
| `app:terraImageUploadHelperText` | string | — |
| `app:terraImageUploadShowHelper` | boolean | `true` |
| `app:terraImageUploadPrimaryActionText` | string | `"Ambil Gambar"` |
| `app:terraImageUploadSecondaryActionText` | string | `"Ubah"` |
| `app:terraImageUploadTertiaryActionText` | string | `"Lihat"` |
| `app:terraImageUploadLoadingText` | string | `"Dalam Proses..."` |
| `app:terraImageUploadPreviewSrc` | reference | — |
| `app:terraImageUploadActionCount` | integer | `1` |
| `app:terraImageUploadState` | enum | `imageUpload` |

`terraImageUploadState`: `imageUpload` \| `attachProcess` \| `imageAttached`

## Public API (Kotlin)

```kotlin
var state: State = State.IMAGE_UPLOAD

fun setPreviewImage(@DrawableRes resId: Int)
fun setCameraIconResource(@DrawableRes resId: Int)
fun setActionCount(count: Int)   // kontrol visibility tertiaryButton/dividerView (count > 1)
```

Enum: `State { IMAGE_UPLOAD, ATTACH_PROCESS, IMAGE_ATTACHED }`.

## Contoh XML

```xml
<com.terra.design.components.TerraImageUpload
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    app:terraImageUploadLabel="Foto KTP"
    app:terraImageUploadState="imageUpload"
    app:terraImageUploadActionCount="2" />
```

## Catatan

Icon kamera default selalu hardcode ke `R.drawable.information` (bukan icon kamera beneran) — belum ada attr khusus untuk override ini di XML, harus dipanggil manual lewat `setCameraIconResource()`.
