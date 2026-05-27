# mobile-lab-projects

《移动应用开发工程实践》课程实验项目集合。仓库按实验编号组织，每个实验都是一个可独立运行的 Android Gradle 模块。

## 实验目录

| 实验 | 模块 | 目录 | 重点 |
| --- | --- | --- | --- |
| 实验一 记事本 | `practiceNotepadApp` | `projects/mobile-app-practice/01-notepad` | SQLite、RecyclerView、AlertDialog、火山方舟 Ark 文本润色 |
| 实验二 Handler 的使用 | `practiceHandlerApp` | `projects/mobile-app-practice/02-handler-webview` | Handler、WebView、ProgressBar、Snackbar |
| 实验三 基础动画 | `practiceAnimationApp` | `projects/mobile-app-practice/03-basic-animation` | 逐帧动画、补间动画、Handler 延时控制 |

## Android Studio 运行

1. 使用 Android Studio 打开本仓库根目录。
2. 等待 Gradle Sync 完成。
3. 在右上角运行配置中选择对应模块。
4. 选择模拟器或真机，点击 Run 或 Debug。

## 命令行构建

```bash
./gradlew :practiceNotepadApp:assembleDebug
./gradlew :practiceHandlerApp:assembleDebug
./gradlew :practiceAnimationApp:assembleDebug
```

## Ark API Key 配置

实验一的记事本润色功能需要火山方舟 API Key。为了避免密钥泄露，仓库不会提交真实 Key。

本地运行时复制示例文件：

```bash
cp local.properties.example local.properties
```

然后在 `local.properties` 中填写：

```properties
ARK_API_KEY=你的火山方舟APIKey
```

也可以通过环境变量传入：

```bash
export ARK_API_KEY="你的火山方舟APIKey"
```
