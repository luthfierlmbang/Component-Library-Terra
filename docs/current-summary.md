# Current Summary

## Goal
Build an Android XML design system library that:
- uses a single Figma file as the source of truth
- is shared across multiple squads working on the same application
- is distributed as an AAR
- is later published to Nexus

## Token foundation status

### Typography
Typography tokens have been prepared as Android `TextAppearance` styles:
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
Bundled Android font resources are available for:
- `@font/jenius_sans_regular`
- `@font/jenius_sans_bold`

### Primitive colors
Primitive color tokens have been added to `design-tokens/src/main/res/values/colors.xml`, covering:
- neutral / grey
- surface
- overlay
- shadow
- brand orange
- brand teal
- status yellow
- status red

### Semantic colors
Semantic color tokens have been added and mapped to primitive colors, covering:
- background
- text
- border
- icon

### Shadow
A draft shadow reference style is available:
- `Widget.Terra.Shadow.Card`

### Spacing and dimensions
Current token coverage now includes:
- `terra_spacing_4`
- `terra_spacing_8`
- `terra_spacing_16`
- `terra_spacing_24`
- `terra_spacing_32`
- `terra_radius_6`
- `terra_radius_8`
- `terra_size_icon_20`
- `terra_size_icon_24`
- `terra_size_button_height_small`
- `terra_size_button_height_normal`

## Implementation principle for components
All sliced components from Figma should:
- use tokenized colors
- use tokenized typography
- use bundled Jenius Sans fonts
- use tokenized dimension, radius, and spacing values whenever available
- avoid hardcoded style values if matching tokens already exist

## Design source
All components will be sliced from one shared Figma file, which acts as the source of truth for:
- visual appearance
- variant structure
- nested structure
- token mapping

## Current component under analysis

### Button
Figma node:
- `570:4177`

### Button analysis result
This is not 24 separate components. It is one reusable button component with combinations of:

#### Types
- primary
- secondary
- outlined primary
- outlined secondary
- danger
- text button

#### Sizes
- normal
- small

#### States
- default / enabled
- disabled

#### Icon permutations
- no icon
- left icon only
- right icon only
- left and right icons

### Nested structure for button
- container
- content row
- label
- left icon slot
- right icon slot

## Planned Button implementation

### Component name
- `TerraButton`

### XML/API direction
Planned attributes:
- `terraButtonType`
- `terraButtonSize`
- `terraLeftIcon`
- `terraRightIcon`
- `android:text`
- `android:enabled`

### Token usage inside button
The component should consume:

#### Colors
Semantic tokens for:
- background
- text
- border
- icon

#### Typography
- `TextAppearance.Terra.Button.Medium`
- `TextAppearance.Terra.Button.Small`

#### Dimensions
The button will also need tokenized values for:
- min height
- horizontal padding
- vertical padding
- corner radius
- icon size
- internal spacing

## Gaps to complete before full button implementation

### Missing dimension tokens
The current token set is not yet complete enough for a fully tokenized button.

Likely required additions:
- `terra_spacing_4`
- `terra_spacing_8`
- `terra_radius_6`
- `terra_radius_8`
- `terra_size_icon_20`
- `terra_size_icon_24`
- `terra_size_button_height_small`
- `terra_size_button_height_normal`
- `terra_padding_button_horizontal`
- `terra_padding_button_text_horizontal`

### Possible semantic color gap
Some variants such as outlined secondary may benefit from a more explicit semantic action-secondary token set if stricter semantic separation is needed.

## Button implementation progress

### Current implementation status
`TerraButton` has been started in the `components` module with:
- type support:
  - primary
  - secondary
  - outlined primary
  - outlined secondary
  - danger
  - text button
- size support:
  - normal
  - small
- icon support:
  - left icon
  - right icon
  - both icons
- enabled / disabled handling
- sample screen and catalog XML for preview
- explicit button height tokens:
  - `terra_size_button_height_small`
  - `terra_size_button_height_normal`

### Icon extraction progress
Icons from the shared Figma icon page have been extracted and indexed.
Current progress:
- icon asset index documented in `docs/icon-index.md`
- button demo now references local extracted icons
- image-based icons remain PNG
- initial VectorDrawable XML conversion has started for button-relevant icons:
  - `plus`
  - `arrow_left`
  - `chevron_right`
  - `check`
  - `close`
  - `search`
  - `information`
  - `settings`

## Card implementation progress

### Current implementation status
Card component set from Figma node `48:1567` has been started in the `components` module with reusable views:
- `TerraMenuCard`
- `TerraInformationCard`
- `TerraActivityCard`

### Reuse decisions
- `TerraMenuCard` reuses local icon drawables and optional trailing icon support
- `TerraInformationCard` reuses `TerraButton` for the CTA
- `TerraActivityCard` reuses `TerraButton` for the action button
- sample screen now previews button and card components together

## Chip implementation progress

### Current component under analysis
#### Chips
Figma node:
- `148:15986`

### Chip analysis result
This is one reusable chip component with combinations of:
- selected off / on
- label text
- optional left icon
- optional right icon
- optional counter badge

### Current implementation status
`TerraChip` has been started in the `components` module with:
- selected and unselected state support
- `android:text` support
- optional left icon
- optional right icon
- optional counter badge
- sample preview wired in the sample app

### Current XML/API direction
Implemented attributes:
- `android:text`
- `terraChipSelected`
- `terraChipLeftIcon`
- `terraChipRightIcon`
- `terraChipShowLeftIcon`
- `terraChipShowRightIcon`
- `terraChipHasCounter`
- `terraChipCounterText`

## Incentive card implementation progress

### Current component under analysis
#### Incentive Cards
Figma node:
- `234:121`

### Incentive card analysis result
This Figma set is not one single card. It contains:
- 3 gradient metric card variants:
  - Customer Active
  - Total Ditabung
  - Pencairan
- 1 plain info card variant:
  - Kinerjaku

### Current implementation status
First-pass reusable components have been added in the `components` module:
- `TerraIncentiveMetricCard`
- `TerraIncentiveInfoCard`

### Reuse decisions
- one reusable gradient metric card handles the three amount-based variants with a `terraMetricVariant` enum
- one reusable plain info card handles the `Kinerjaku` variant
- sample screen now previews all four incentive card states
- current lead icons reuse already available local drawables for preview and can be swapped later with more exact extracted icons

## Image upload implementation progress

### Current component under analysis
#### Image Upload
Figma node:
- `235:735`

### Image upload analysis result
This Figma set is one reusable image-upload component with combinations of:
- state:
  - image upload
  - attach process
  - image attached
- CTA options:
  - no CTA
  - 1 CTA
  - 2 CTA
- optional helper text
- label text
- preview image area

### Current implementation status
A first-pass reusable component has been added in the `components` module:
- `TerraImageUpload`

### Current XML/API direction
Implemented attributes:
- `terraImageUploadLabel`
- `terraImageUploadHelperText`
- `terraImageUploadShowHelper`
- `terraImageUploadPrimaryActionText`
- `terraImageUploadSecondaryActionText`
- `terraImageUploadTertiaryActionText`
- `terraImageUploadLoadingText`
- `terraImageUploadPreviewSrc`
- `terraImageUploadActionCount`
- `terraImageUploadState`

### Reuse decisions
- one reusable view handles all Figma states instead of separate layouts
- attached preview currently reuses local drawable resources for sample preview
- loading state uses native Android `ProgressBar` for a minimal first pass
- CTA row reuses `TerraButton`

## Label badge implementation progress

### Current component under analysis
#### Label
Figma node:
- `140:4703`

### Label analysis result
This Figma set is one small reusable label badge with combinations of:
- state:
  - default
  - secondary
  - neutral
  - failed
- label text
- optional leading icon

### Current implementation status
A reusable component has been added in the `components` module:
- `TerraLabelBadge`

### Current XML/API direction
Implemented attributes:
- `android:text`
- `terraLabelBadgeIcon`
- `terraLabelBadgeShowIcon`
- `terraLabelBadgeState`

### Reuse decisions
- one reusable badge handles all color variants
- current sample reuses local existing icons for preview
- a `20dp` spacing token was added because the badge height needs it and it is a valid reusable token size

## Bottom navigation implementation progress

### Current component under analysis
#### Navbar
Figma node:
- `45:139`

### Navbar analysis result
This Figma set is one reusable 3-item bottom navigation bar with combinations of:
- selected item:
  - home
  - activity
  - notification
  - none
- customizable labels
- customizable item icons

### Current implementation status
A reusable component has been added in the `components` module:
- `TerraBottomNavBar`

### Current XML/API direction
Implemented attributes:
- `terraBottomNavHomeText`
- `terraBottomNavActivityText`
- `terraBottomNavNotificationText`
- `terraBottomNavHomeIcon`
- `terraBottomNavActivityIcon`
- `terraBottomNavNotificationIcon`
- `terraBottomNavSelectedItem`

### Reuse decisions
- one reusable view handles all 4 navbar states from Figma
- current sample reuses available local icons for preview instead of exact Figma nav icons
- the selected item uses a simple elevated circular highlight approximation for the active icon state

## Search bar implementation progress

### Current component under analysis
#### Search Bar
Figma node:
- `127:260`

### Search bar analysis result
This Figma set is one reusable search bar component with combinations of:
- state:
  - default
  - focus
  - result
  - disabled
- text / hint content
- trailing icon that switches between search and close based on state

### Current implementation status
A reusable component has been added in the `components` module:
- `TerraSearchBar`

### Current XML/API direction
Implemented attributes:
- `android:text`
- `android:hint`
- `terraSearchBarTrailingIcon`
- `terraSearchBarState`

### Reuse decisions
- one reusable view handles all 4 search bar states from Figma
- implementation uses `EditText` inside a styled container for the lightest reusable Android XML approach
- trailing icon defaults to search, and switches to close for the focus state unless explicitly overridden

## Radio button implementation progress

### Current component under analysis
#### Radio Button
Figma node:
- `176:4345`

### Radio button analysis result
This Figma set is one reusable radio button component with combinations of:
- checked on / off
- disabled on / off
- label shown / hidden
- configurable label text

### Current implementation status
A reusable component has been added in the `components` module:
- `TerraRadioButton`

### Current XML/API direction
Implemented attributes:
- `android:text`
- `android:checked`
- `android:enabled`
- `terraRadioButtonShowLabel`

### Reuse decisions
- one reusable view handles all 8 visual combinations from Figma
- implementation uses a simple outer ring + inner dot structure instead of multiple drawable files
- label typography automatically switches between regular and bold based on checked state

## Checkbox implementation progress

### Current component under analysis
#### Checkbox
Figma node:
- `123:270`

### Checkbox analysis result
This Figma set is one reusable checkbox component with combinations of:
- checked on / off
- disabled on / off
- label shown / hidden
- configurable label text

### Current implementation status
A reusable component has been added in the `components` module:
- `TerraCheckbox`

### Current XML/API direction
Implemented attributes:
- `android:text`
- `android:checked`
- `android:enabled`
- `terraCheckboxShowLabel`

### Reuse decisions
- one reusable view handles all 8 visual combinations from Figma
- implementation uses a simple box + check icon structure instead of multiple drawable files
- label typography automatically switches between regular and bold based on checked state

## Text field implementation progress

### Current component under analysis
#### Text Field
Figma node:
- `734:846`

### Text field analysis result
This Figma set is one reusable text field component with combinations of:
- type:
  - default
  - text prefix
  - text suffix
  - text prefix + icon prefix
- state:
  - default
  - active
  - filled
  - error
  - disabled
- optional parts:
  - label
  - helper text
  - left icon
  - right icon
  - prefix icon
  - prefix text
  - suffix text

### Current implementation status
A first-pass reusable component has been added in the `components` module:
- `TerraTextField`

### Current XML/API direction
Implemented attributes:
- `android:text`
- `android:hint`
- `terraTextFieldType`
- `terraTextFieldState`
- `terraTextFieldLabelText`
- `terraTextFieldHelperText`
- `terraTextFieldPrefixText`
- `terraTextFieldSuffixText`
- `terraTextFieldLeftIcon`
- `terraTextFieldRightIcon`
- `terraTextFieldPrefixIcon`
- `terraTextFieldHasLabel`
- `terraTextFieldHasHelperText`
- `terraTextFieldHasLeftIcon`
- `terraTextFieldHasRightIcon`
- `terraTextFieldHasPrefixIcon`

### Reuse decisions
- one reusable view handles all main Figma text field variants instead of separate layouts
- implementation uses native `EditText` inside a token-styled container for the lightest first pass
- icons are optional and only shown when explicitly wired
- sample screen now includes preview states for default, active, filled, error, disabled, prefix, suffix, and prefix+icon variants

## Planned next steps
1. refine text field spacing and exact active/error visual parity if stricter Figma parity is needed
2. refine checkbox exact checkmark visual and disabled colors if stricter Figma parity is needed
3. refine radio button orange gradient dot and exact disabled colors if stricter Figma parity is needed
4. continue the next Figma component after text field

## Slicing principle going forward
Each Figma component should follow this flow:
1. identify variants
2. identify nested structure
3. identify optional parts
4. map design decisions to tokens
5. implement as a reusable Android XML component
6. avoid creating separate layouts when the design is only a combination of state or variant
