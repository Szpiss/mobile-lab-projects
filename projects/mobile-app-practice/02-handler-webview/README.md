# 实验二：Handler 的使用

本实验通过 Handler 和 WebView 演示子线程与主线程之间的消息传递。

## 功能

- 点击悬浮按钮后显示进度条。
- WebView 加载 `https://blog.csdn.net/`。
- Handler 每秒更新一次 ProgressBar。
- 进度结束后隐藏进度条并弹出 Snackbar。
- 点击 Snackbar 的确认按钮后显示 Toast。

## 关键文件

- `app/src/main/java/cn/edu/android/MainActivity.java`
- `app/src/main/res/layout/activity_main.xml`
- `app/src/main/AndroidManifest.xml`

## 运行

```bash
./gradlew :practiceHandlerApp:assembleDebug
```

在 Android Studio 中选择 `practiceHandlerApp` 运行。
