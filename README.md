# 移动应用开发工程实践实验仓库

本仓库统一整理《移动应用开发工程实践》课程实验。每个实验是一个可独立运行的 Android Gradle 模块，课程代码、依赖配置和运行说明集中在同一个仓库中，避免同课程实验重复建仓。

## 实验目录

| 实验 | 模块 | 目录 | 重点能力 |
| --- | --- | --- | --- |
| 实验一：记事本 | `practiceNotepadApp` | `projects/mobile-app-practice/01-notepad` | SQLite、RecyclerView、AlertDialog、火山方舟 Ark 文本润色 |
| 实验二：Handler 的使用 | `practiceHandlerApp` | `projects/mobile-app-practice/02-handler-webview` | Handler、WebView、ProgressBar、Snackbar |
| 实验三：基础动画 | `practiceAnimationApp` | `projects/mobile-app-practice/03-basic-animation` | 逐帧动画、补间动画、Handler 延时控制 |

## 仓库结构

```text
mobile-lab-projects/
├── projects/mobile-app-practice/     # 工程实践课程实验模块
├── local.properties.example          # API Key 配置示例
├── gradle/
├── build.gradle
├── settings.gradle
└── README.md
```

## Android Studio 运行

1. 使用 Android Studio 打开仓库根目录。
2. 等待 Gradle Sync 完成。
3. 在右上角运行配置中选择对应实验模块。
4. 选择模拟器或真机运行。

## 命令行构建

```bash
./gradlew :practiceNotepadApp:assembleDebug
./gradlew :practiceHandlerApp:assembleDebug
./gradlew :practiceAnimationApp:assembleDebug
```

## Ark API Key 配置

实验一的记事本润色功能需要火山方舟 API Key。真实 Key 不提交到仓库，运行前复制示例文件：

```bash
cp local.properties.example local.properties
```

然后在 `local.properties` 中填写：

```properties
ARK_API_KEY=你的火山方舟APIKey
```

也可以用环境变量传入：

```bash
export ARK_API_KEY="你的火山方舟APIKey"
```

## 维护规则

- 同一门工程实践课程的新实验继续放入本仓库。
- 实验报告、截图和提交说明建议放在对应实验目录下。
- 不提交真实 API Key、构建产物和本机路径配置。
