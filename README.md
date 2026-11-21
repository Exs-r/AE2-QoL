# AE2 助手

[English](README.en.md) | 简体中文

[![Release](https://img.shields.io/github/v/release/Exs-r/ae2-qol?style=flat-square&label=Release)](https://github.com/Exs-r/ae2-qol/releases)
[![License](https://img.shields.io/badge/License-MIT-blue.svg?style=flat-square)](LICENSE)
[![Minecraft](https://img.shields.io/badge/Minecraft-1.21.1-green.svg?style=flat-square)](https://minecraft.net)
[![NeoForge](https://img.shields.io/badge/NeoForge-21.1.x-orange.svg?style=flat-square)](https://neoforged.net)
[![AE2](https://img.shields.io/badge/AE2-19.2.17%2B-lightgrey.svg?style=flat-square)](https://github.com/AppliedEnergistics/Applied-Energistics-2)

> 一个让玩家把精力集中在科技线上的 Applied Energistics 2 附属模组。

按 `I` 打开一个 AE2 风格的界面，可以取消频道限制、开启创造飞行，并领取新手礼包和 ME 无限元件包。

## 目录

- [功能](#功能)
- [兼容性](#兼容性)
- [安装](#安装)
- [礼包内容](#礼包内容)
- [构建](#构建)
- [常见问题](#常见问题)
- [许可证](#许可证)
- [致谢](#致谢)

## 功能

| 功能 | 说明 |
| --- | --- |
| 快捷界面 | 默认按 `I` 打开，可在「选项 → 控制 → 按键绑定」中修改 |
| 取消 AE2 频道限制 | 服务器全局开关，默认关闭；开启后 AE 网络不再需要频道，状态写入存档 |
| 创造模式飞行 | 每位玩家独立开关，默认关闭；开启后双击空格即可像创造模式一样飞行 |
| 新手礼包 | 可重复领取，满电 256k 便携元件，内含一套 AE2 开局设备 |
| ME 无限元件包 | 可重复领取，满电 256k 便携元件，内含 ME 无限元件与常用设备 |

界面使用 AE2 自身的面板颜色（`#413F54` 外描边、`#F2F2F2` 内描边、`#CBCCD4` 填充）和 AE2 官方
`AE2Button` 按钮贴图，与高版本 AE2 的终端界面保持一致。

## 兼容性

| 项目 | 版本 |
| --- | --- |
| Minecraft | 1.21.1 |
| NeoForge | 21.1.x |
| Applied Energistics 2 | 19.2.17+（必需） |

以下附属为**可选**：装了就发放对应物品，没装只会跳过该条并写一条 WARN 日志，不会崩溃。

- ExtendedAE
- AE2WTLib
- ExtendedAE-Plus
- AE2-FTBQuest-Detector

## 安装

1. 安装 Minecraft 1.21.1 与 NeoForge 21.1.x。
2. 安装 Applied Energistics 2 19.2.17 或更高版本。
3. 从 [Releases](https://github.com/Exs-r/ae2-qol/releases) 下载 `ae2qol-*.jar`，放入整合包的 `mods` 目录。

## 礼包内容

### 新手礼包

| 物品 | 注册名 | 数量 |
| --- | --- | --- |
| ME 控制器 | `ae2:controller` | 68 |
| ME 扩展驱动器 | `extendedae:ex_drive` | 64 |
| 福鲁伊克斯色 ME 智能线缆 | `ae2:fluix_smart_cable` | 64 |
| 创造能源元件 | `ae2:creative_energy_cell` | 1 |
| ME 量子环 | `ae2:quantum_ring` | 8 |
| ME 量子链接仓 | `ae2:quantum_link` | 1 |
| ME 合成终端 | `ae2:crafting_terminal` | 1 |
| ME 样板编码终端 | `ae2:pattern_encoding_terminal` | 1 |
| 无线合成终端 | `ae2:wireless_crafting_terminal` | 1 |
| 无线样板编码终端 | `ae2wtlib:wireless_pattern_encoding_terminal` | 1 |
| ME 扩展样板管理终端 | `extendedae:ex_pattern_access_part` | 1 |
| 无线扩展样板管理终端 | `extendedae:wireless_ex_pat` | 1 |
| 量子缠绕态奇点（同一配对 ID） | `ae2:quantum_entangled_singularity` | 2 |
| 量子桥卡 | `ae2wtlib:quantum_bridge_card` | 1 |
| 吞噬万籁的寂静 | `extendedae_plus:infinity_biginteger_cell` | 1 |
| ME 任务检测器 | `ae2_ftbquest_detector:me_quests_detector` | 1 |

### ME 无限元件包

| 物品 | 注册名 |
| --- | --- |
| 福鲁伊克斯色 ME 智能线缆 | `ae2:fluix_smart_cable` |
| 石英纤维 | `ae2:quartz_fiber` |
| ME 存储总线 | `ae2:storage_bus` |
| 加速卡 | `ae2:speed_card` |
| 空白样板 | `ae2:blank_pattern` |
| ME 无限水元件 | `extendedae:infinity_water_cell` |
| ME 无限圆石元件 | `extendedae:infinity_cobblestone_cell` |
| ME 扩展接口 | `extendedae:ex_interface` |
| ME 扩展样板供应器 | `extendedae:ex_pattern_provider` |
| ME 扩展输入总线 | `extendedae:ex_import_bus_part` |
| ME 扩展输出总线 | `extendedae:ex_export_bus_part` |
| 标签无线收发器 | `extendedae_plus:labeled_wireless_transceiver` |

两个礼包都使用满电的 256k 便携元件，内容通过 AE2 的 `STORAGE_CELL_INV` 数据组件写入。

## 构建

需要 JDK 21。

```bash
git clone https://github.com/Exs-r/ae2-qol.git
cd ae2-qol
./gradlew build
```

Windows 下用 `gradlew.bat build`。

产物位于 `build/libs/ae2qol-1.0.0.jar`。

`libs/` 中的编译依赖（AE2、Guideme、Glodium 的 jar）因版权原因不随仓库分发，请自行从 CurseForge / Modrinth 下载对应版本放入该目录。

## 常见问题

**按 I 没反应？**
先确认没有和其它模组冲突：到「选项 → 控制 → 按键绑定」搜索「AE2 助手」，把键位改成别的试试。

**频道开关重启后失效？**
开关保存在世界存档里；如果是换存档或新世界，默认是关闭状态。目前只支持「默认」和「无限」两种模式，不会修改整合包自带的 `ae2-common.toml`。

**礼包少了某几样东西？**
对应的附属模组没装。日志里会有一条 `Item 'xxx' is missing` 的 WARN，装上对应模组即可。

**飞行开关会影响别人吗？**
不会，飞行是每位玩家独立的；频道限制是服务器全局的。

**能和别的 AE2 附属一起用吗？**
可以。所有非 AE2 物品都按注册名查找，缺什么跳过什么，不会硬绑定版本。

## 许可证

本项目使用 [MIT License](LICENSE)。

## 致谢

- [Applied Energistics 2](https://github.com/AppliedEnergistics/Applied-Energistics-2) — 本模组的 API 与界面风格来源
- [ExtendedAE](https://github.com/GlodBlock/ExtendedAE) — ME 无限元件与扩展设备
- [AE2WTLib](https://github.com/Mithi83/AE2WTLib) — 无线终端与量子桥卡
- [ExtendedAE-Plus](https://modrinth.com/mod/extendedae-plus) — 吞噬万籁的寂静、标签无线收发器
- [AE2-Ftbquest-Detector](https://github.com/qihua233/ME_Quests_Detector_1.21.1) — ME 任务检测器
