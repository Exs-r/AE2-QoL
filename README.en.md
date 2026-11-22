# AE2 QoL

English | [简体中文](README.md)

[![Build](https://img.shields.io/github/actions/workflow/status/Exs-r/AE2-QoL/build.yml?style=flat-square&label=Build)](https://github.com/Exs-r/AE2-QoL/actions/workflows/build.yml)
[![Release](https://img.shields.io/github/v/release/Exs-r/AE2-QoL?style=flat-square&label=Release)](https://github.com/Exs-r/AE2-QoL/releases)
[![License](https://img.shields.io/badge/License-MIT-blue.svg?style=flat-square)](LICENSE)
[![Minecraft](https://img.shields.io/badge/Minecraft-1.21.1-green.svg?style=flat-square)](https://minecraft.net)
[![NeoForge](https://img.shields.io/badge/NeoForge-21.1.x-orange.svg?style=flat-square)](https://neoforged.net)
[![AE2](https://img.shields.io/badge/AE2-19.2.17%2B-lightgrey.svg?style=flat-square)](https://github.com/AppliedEnergistics/Applied-Energistics-2)

> An Applied Energistics 2 addon that lets you focus on the tech tree instead of the busywork.

Press `I` to open an AE2-styled panel where you can remove the channel limit, enable creative flight, and claim a starter kit and an ME infinity kit.

## Table of Contents

- [Features](#features)
- [Compatibility](#compatibility)
- [Installation](#installation)
- [Kit Contents](#kit-contents)
- [Building](#building)
- [FAQ](#faq)
- [License](#license)
- [Credits](#credits)

## Features

| Feature | Description |
| --- | --- |
| Quick panel | Bound to `I` by default, rebindable in Options → Controls → Key Binds |
| Unlimited AE2 channels | Server-wide toggle, off by default; AE networks no longer need channels, persisted in the world save |
| Creative flight | Per-player toggle, off by default; double-tap space to fly like in creative mode |
| Starter kit | Repeatable; a fully charged 256k portable cell with a complete AE2 starter setup |
| ME infinity kit | Repeatable; a fully charged 256k portable cell with ME infinity cells and useful devices |

The panel is drawn with AE2's own colours (`#413F54` outline, `#F2F2F2` inner line, `#CBCCD4` fill) and AE2's
`AE2Button` widget, so it matches the modern AE2 terminals.

## Compatibility

| Component | Version |
| --- | --- |
| Minecraft | 1.21.1 |
| NeoForge | 21.1.x |
| Applied Energistics 2 | 19.2.17+ (required) |

The following addons are **optional**. When present their items are included in the kits; when absent those
entries are skipped with a WARN log line and nothing crashes.

- ExtendedAE
- AE2WTLib
- ExtendedAE-Plus
- AE2-FTBQuest-Detector

## Installation

1. Install Minecraft 1.21.1 and NeoForge 21.1.x.
2. Install Applied Energistics 2 19.2.17 or newer.
3. Download `ae2qol-*.jar` from [Releases](https://github.com/Exs-r/AE2-QoL/releases) and put it into the `mods` folder.

## Kit Contents

### Starter Kit

| Item | Registry name | Count |
| --- | --- | --- |
| ME Controller | `ae2:controller` | 68 |
| ME Extended Drive | `extendedae:ex_drive` | 64 |
| Fluix ME Smart Cable | `ae2:fluix_smart_cable` | 64 |
| Creative Energy Cell | `ae2:creative_energy_cell` | 1 |
| ME Quantum Ring | `ae2:quantum_ring` | 8 |
| ME Quantum Link Chamber | `ae2:quantum_link` | 1 |
| ME Crafting Terminal | `ae2:crafting_terminal` | 1 |
| ME Pattern Encoding Terminal | `ae2:pattern_encoding_terminal` | 1 |
| Wireless Crafting Terminal | `ae2:wireless_crafting_terminal` | 1 |
| Wireless Pattern Encoding Terminal | `ae2wtlib:wireless_pattern_encoding_terminal` | 1 |
| ME Extended Pattern Access Terminal | `extendedae:ex_pattern_access_part` | 1 |
| Wireless Extended Pattern Access Terminal | `extendedae:wireless_ex_pat` | 1 |
| Quantum Entangled Singularity (same pair ID) | `ae2:quantum_entangled_singularity` | 2 |
| Quantum Bridge Card | `ae2wtlib:quantum_bridge_card` | 1 |
| Devouring Silence | `extendedae_plus:infinity_biginteger_cell` | 1 |
| ME Quest Detector | `ae2_ftbquest_detector:me_quests_detector` | 1 |

### ME Infinity Kit

| Item | Registry name |
| --- | --- |
| Fluix ME Smart Cable | `ae2:fluix_smart_cable` |
| Quartz Fiber | `ae2:quartz_fiber` |
| ME Storage Bus | `ae2:storage_bus` |
| Acceleration Card | `ae2:speed_card` |
| Blank Pattern | `ae2:blank_pattern` |
| ME Infinity Water Cell | `extendedae:infinity_water_cell` |
| ME Infinity Cobblestone Cell | `extendedae:infinity_cobblestone_cell` |
| ME Extended Interface | `extendedae:ex_interface` |
| ME Extended Pattern Provider | `extendedae:ex_pattern_provider` |
| ME Extended Import Bus | `extendedae:ex_import_bus_part` |
| ME Extended Export Bus | `extendedae:ex_export_bus_part` |
| Labeled Wireless Transceiver | `extendedae_plus:labeled_wireless_transceiver` |

Both kits use fully charged 256k portable cells; contents are written through AE2's `STORAGE_CELL_INV`
data component.

## Building

Requires JDK 21.

```bash
git clone https://github.com/Exs-r/AE2-QoL.git
cd ae2-qol
./gradlew build
```

On Windows use `gradlew.bat build`.

The output jar is placed in `build/libs/ae2qol-1.0.0.jar`.

AE2, Guideme and Glodium are resolved from Modrinth's Maven repository during the build, so no manual setup
is needed.

## FAQ

**Pressing I does nothing?**
Check for a key bind conflict: open Options → Controls → Key Binds, search for "AE2 QoL" and rebind it.

**The channel toggle resets after restart?**
The toggle is stored in the world save. New worlds start with it off. Only the "default" and "infinite"
modes are used; your modpack's `ae2-common.toml` is never modified.

**Some items are missing from a kit?**
The matching addon is not installed. A `Item 'xxx' is missing` WARN line will be in the log; install the addon
to include it.

**Does the flight toggle affect other players?**
No, it is per-player. The channel limit is server-wide.

**Is it compatible with other AE2 addons?**
Yes. Every non-AE2 item is looked up by registry name, so missing mods are skipped instead of causing a crash.

## License

[MIT License](LICENSE)

See [CHANGELOG.md](CHANGELOG.md) for changes and [CONTRIBUTING.md](CONTRIBUTING.md) for contribution guidelines.

## Credits

- [Applied Energistics 2](https://github.com/AppliedEnergistics/Applied-Energistics-2) — API and UI style
- [ExtendedAE](https://github.com/GlodBlock/ExtendedAE) — ME infinity cells and extended devices
- [AE2WTLib](https://github.com/Mithi83/AE2WTLib) — wireless terminals and quantum bridge card
- [ExtendedAE-Plus](https://modrinth.com/mod/extendedae-plus) — Devouring Silence, labeled wireless transceiver
- [AE2-Ftbquest-Detector](https://github.com/qihua233/ME_Quests_Detector_1.21.1) — ME quest detector
