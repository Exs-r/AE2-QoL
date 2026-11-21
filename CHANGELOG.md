# Changelog

本项目的重要变更都会记录在这里。

格式参考 [Keep a Changelog](https://keepachangelog.com/zh-CN/1.1.0/)，
版本号遵循 [Semantic Versioning](https://semver.org/lang/zh-CN/)。

## [1.0.0] - 2026-11-21

### 新增

- 按 `I` 打开 AE2 风格界面，按键可在「选项 → 控制 → 按键绑定」中修改
- 取消 AE2 频道限制开关（服务器全局，写入存档）
- 创造模式飞行开关（每位玩家独立）
- 新手礼包：满电 256k 便携元件，内含控制器、扩展驱动器、智能线缆、量子环、终端等
- ME 无限元件包：满电 256k 便携元件，内含 ME 无限水/圆石元件与常用设备
- 两个礼包均可重复领取，带 1 秒服务端冷却
- 一对共享配对 ID 的量子缠绕态奇点
- 中英文语言文件

### 说明

- ExtendedAE、AE2WTLib、ExtendedAE-Plus、AE2-FTBQuest-Detector 为可选依赖，缺失时跳过对应物品
- 频道开关只修改 AE2 运行时配置，不会改写整合包的 `ae2-common.toml`
