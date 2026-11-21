# AE2 助手

> **下载**：<https://github.com/Exs-r/ae2-qol/releases> · **源码**：<https://github.com/Exs-r/ae2-qol>

面向 **NeoForge 1.21.1** 的 Applied Energistics 2 附属模组，让整合包玩家把精力集中在科技线上。

## 功能

| 功能 | 说明 | 状态 |
| --- | --- | --- |
| 快捷界面 | 默认按 `I` 打开，可在「选项 → 控制 → 按键绑定」修改 | 已验证 |
| 取消 AE2 频道限制 | 服务器全局开关，默认关闭；开启后 AE 网络不再需要频道 | 已验证 |
| 创造模式飞行 | 每位玩家独立开关，默认关闭；开启后双击空格飞行 | 已验证 |
| 新手礼包 | 可重复领取，满电 256k 便携元件 | 已验证 |
| ME 无限元件包 | 可重复领取，满电 256k 便携元件 | 已验证 |

界面使用 AE2 自身的面板颜色（`#413F54` 外描边、`#F2F2F2` 内描边、`#CBCCD4` 填充）和 AE2 官方
`AE2Button` 按钮贴图，与高版本 AE2 的终端界面保持一致。

## 礼包内容

**新手礼包**（`新手礼包` 便携元件，满电）

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

**ME 无限元件包**（`ME无限元件包` 便携元件，满电）

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

## 安装

1. 需要 Minecraft 1.21.1 + NeoForge 21.1.x + AE2 19.2.17+。
2. ExtendedAE、AE2WTLib、ExtendedAE-Plus、AE2-FTBQuest-Detector 为可选依赖：缺失时对应物品会跳过并写一条 WARN 日志，不会导致崩溃。

## 构建

需要 JDK 21 与 Gradle 8.10+。

```bat
gradle build
```

产物：`build/libs/ae2qol-1.0.0.jar`

`libs/` 中的编译依赖（AE2、Guideme、Glodium 的 jar）因版权原因不随仓库分发，请自行从 CurseForge / Modrinth 下载对应版本放入该目录。

## 实现要点

- 开关状态保存在世界存档的 `ae2qol_state`（`SavedData`），服务器重启后自动恢复。
- 频道开关调用 AE2 的 `AEConfig#setChannelModel(INFINITE/DEFAULT)`；该调用只改运行时配置，不会改写整合包的 `ae2-common.toml`。
- 礼包通过 AE2 数据组件写入：`STORAGE_CELL_INV`（内容）、`STORED_ENERGY` / `ENERGY_CAPACITY`（满电）。
- 两个量子缠绕态奇点共享同一个 `ENTANGLED_SINGULARITY_ID`，可以正常配对。
- 所有非 AE2 物品都用注册名查找，未安装对应附属时只跳过该条。
- 领取有 1 秒服务端冷却；背包满时元件掉落在玩家脚下。

## 目录

```
ae2-qol
├─ src/main/java/com/ae2qol/   源码
├─ src/main/resources/         mods.toml 与中英文语言文件
├─ libs/                       编译期依赖（需自行放入）
├─ build.gradle
├─ settings.gradle
└─ README.md
```
