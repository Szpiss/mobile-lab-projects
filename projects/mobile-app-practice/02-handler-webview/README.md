# 02-handler-webview

《移动应用开发工程实践》实验二：Handler 的使用。

## 实验目标

- 使用 `Handler` 完成子线程和主线程之间的消息传递。
- 使用 `WebView` 加载网页内容。
- 使用 Material Design 中的 `FloatingActionButton` 和 `Snackbar` 完成交互提示。
- 使用 `RelativeLayout` 摆放页面控件。

## 实现内容

- 页面包含 `WebView`、水平 `ProgressBar` 和蓝色悬浮按钮。
- 点击悬浮按钮后，显示进度条并通过 `WebView` 打开 `https://blog.csdn.net/`。
- 子线程每隔 1 秒把进度增加 10，并通过 `Handler` 更新进度条。
- 进度超过 100 后隐藏进度条，并弹出 `Snackbar`；点击“确定”后显示 `Toast`。
- 在 `AndroidManifest.xml` 中配置了网络访问权限。

## 运行方式

在仓库根目录运行：

```bash
./gradlew :practiceHandlerApp:assembleDebug
```

在 Android Studio 中打开仓库根目录后，选择 `practiceHandlerApp` 模块运行。
