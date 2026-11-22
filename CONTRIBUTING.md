# 贡献指南

感谢你愿意为 AE2 QoL做出贡献。

## 报告问题

- 先搜索 [已有 issue](https://github.com/Exs-r/ae2-qol/issues)，避免重复。
- 使用 [Bug report](https://github.com/Exs-r/ae2-qol/issues/new?template=bug_report.yml) 模板，附上 `latest.log` 或崩溃报告。
- 请说明 Minecraft、NeoForge、AE2 以及各可选附属的版本。

## 提交改动

1. Fork 仓库并从 `main` 拉出分支。
2. 改动尽量小而聚焦，一个 PR 解决一个问题。
3. 提交前运行：

   ```bash
   ./gradlew build
   ```

4. 如果改动影响游戏内行为，请在带 AE2 19.2.17+ 的 1.21.1 实例里实测。
5. 在 PR 描述里写清楚：改了什么、为什么改、如何验证。

## 代码风格

- Java 21，4 空格缩进，UTF-8，LF 换行。
- 客户端代码放在 `com.ae2qol.client`，不要被服务端类直接引用。
- 非 AE2 物品一律通过注册名查找（`ItemLookup`），不要新增硬依赖。
- 新增或修改文本时同时更新 `zh_cn.json` 与 `en_us.json`。

## 提交信息

建议使用简洁的英文前缀：

```
feat: ...
fix: ...
docs: ...
build: ...
ci: ...
```
