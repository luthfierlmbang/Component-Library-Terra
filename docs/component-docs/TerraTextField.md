# TerraTextField

Custom text input, support label, helper text, prefix/suffix text, prefix/left/right icon.

`components/src/main/java/com/terra/design/components/TerraTextField.kt` · base: `LinearLayout`

## XML Attrs (styleable `TerraTextField`)

| Attr | Format | Default |
|---|---|---|
| `android:text` | string | — |
| `android:hint` | string | — |
| `app:terraTextFieldType` | enum | `typeDefault` |
| `app:terraTextFieldState` | enum | `stateDefault` |
| `app:terraTextFieldLabelText` | string | — |
| `app:terraTextFieldHasLabel` | boolean | `true` |
| `app:terraTextFieldHelperText` | string | — |
| `app:terraTextFieldHasHelperText` | boolean | `true` kalau helperText tidak kosong |
| `app:terraTextFieldPrefixText` | string | `"Rp"` |
| `app:terraTextFieldSuffixText` | string | `"Kg"` |
| `app:terraTextFieldLeftIcon` / `terraTextFieldHasLeftIcon` | reference / boolean | — |
| `app:terraTextFieldRightIcon` / `terraTextFieldHasRightIcon` | reference / boolean | — |
| `app:terraTextFieldPrefixIcon` / `terraTextFieldHasPrefixIcon` | reference / boolean | — |

`terraTextFieldType`: `typeDefault` \| `textPrefix` \| `textSuffix` \| `textPrefixIconPrefix`
`terraTextFieldState`: `stateDefault` \| `active` \| `filled` \| `error` \| `disabled`

## Public API (Kotlin)

```kotlin
var terraTextFieldType: Type = Type.DEFAULT
var terraTextFieldState: State = State.DEFAULT
```

Enum: `Type { DEFAULT, TEXT_PREFIX, TEXT_SUFFIX, TEXT_PREFIX_ICON_PREFIX }`, `State { DEFAULT, ACTIVE, FILLED, ERROR, DISABLED }`.

## Contoh XML

```xml
<com.terra.design.components.TerraTextField
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:hint="Masukkan jumlah"
    app:terraTextFieldLabelText="Jumlah"
    app:terraTextFieldType="textPrefix"
    app:terraTextFieldState="active"
    app:terraTextFieldPrefixText="Rp"
    app:terraTextFieldHasLabel="true" />
```

## ⚠️ Known issue

Satu-satunya text input di library ini yang **tidak** expose `setText`/`getText`/`setHint` publik — beda dari `TerraSearchBar` yang punya API itu. Text runtime cuma bisa diakses lewat `findViewById` manual ke `EditText` internal (tidak direkomendasikan, karena internal view bersifat private/implementation detail).
