# Token Structure Draft

This document defines the initial Android XML token structure for the TERRA design system.

## Included in this draft

### Primitive colors
Primitive color tokens are represented in `design-tokens/src/main/res/values/colors.xml`.

Available draft groups:
- neutral: `terra_color_grey_50` through `terra_color_grey_900`
- surface: `terra_color_surface_blue_50`, `terra_color_surface_pink_50`
- overlay: `terra_color_overlay_black_50`
- shadow: `terra_color_shadow_black_12`
- brand orange: `terra_color_orange_50` through `terra_color_orange_900`
- brand teal: `terra_color_teal_50` through `terra_color_teal_900`
- status yellow: `terra_color_yellow_50` through `terra_color_yellow_900`
- status red: `terra_color_red_50` through `terra_color_red_900`

These are primitive/reference values that are now used by the semantic token layer.

### Semantic colors
Semantic color tokens are also represented in `design-tokens/src/main/res/values/colors.xml` and reference the primitive color tokens.

Available draft groups:
- background: `terra_color_bg_*`
- text: `terra_color_text_*`
- border: `terra_color_border_*`
- icon: `terra_color_icon_*`

This follows the token structure discussed from Figma, using Android-safe underscore naming instead of hyphens.

### Typography
Typography tokens are represented as Android text appearances in `design-tokens/src/main/res/values/styles.xml`.

The current draft now binds typography to bundled Jenius Sans Android font resources:
- `@font/jenius_sans_regular`
- `@font/jenius_sans_bold`

Available draft styles:
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

## Spacing
Spacing tokens are represented as dimens in `design-tokens/src/main/res/values/dimens.xml`.

Current token:
- `terra_spacing_16`

Future spacing tokens should follow the same naming pattern.

## Shadow
Shadow tokens from Figma are treated as Android implementation references rather than direct visual equivalents.

Current draft style:
- `Widget.Terra.Shadow.Card`

This style currently maps the card shadow to Android elevation. When color tokens and component styles are added, it can be paired with background, stroke, and shape styles.

## Pending tokens
The following are intentionally not included yet:
- finalized font resource bindings for Jenius Sans
- theme resources built on top of semantic color tokens
- component-level styles/widgets

These can be added in the next iteration.
