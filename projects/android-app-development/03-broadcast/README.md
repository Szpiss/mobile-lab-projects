# 实验三：广播机制

本实验通过“数鸭子”案例演示 Android 广播机制，包含无序广播、有序广播、同优先级有序广播、拦截广播和指定广播接收者。

## 功能说明

- 点击“发送无序广播”，三个动态注册的接收者会异步接收同一条广播。
- 点击“发送有序广播（优先级不同）”，接收者按照 900、600、300 的优先级依次接收。
- 点击“发送有序广播（优先级相同）”，三个接收者优先级均为 500，按照注册顺序接收。
- 点击“拦截有序广播”，高优先级接收者调用 `abortBroadcast()`，后续接收者不再收到广播。
- 点击“指定广播接收者”，通过 `ComponentName` 指定 `StaticDuckReceiver` 接收广播。

## 关键文件

- `app/src/main/java/cn/itcast/broadcast/MainActivity.java`
- `app/src/main/java/cn/itcast/broadcast/DuckReceiver.java`
- `app/src/main/java/cn/itcast/broadcast/StaticDuckReceiver.java`
- `app/src/main/res/layout/activity_main.xml`
- `app/src/main/AndroidManifest.xml`

## 构建运行

```bash
./gradlew :broadcastApp:assembleDebug
```

在 Android Studio 中打开仓库根目录，选择 `broadcastApp` 运行即可。
