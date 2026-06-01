# android-course-projects

Android 课程实验仓库。当前仓库按课程来源分成两块：早期的 Android 应用开发小实验，以及现在的《移动应用开发工程实践》实验。

## 仓库结构

```text
projects/
├── android-app-development/      # Android 应用开发小实验
│   ├── 01-register-page/         # 注册界面
│   ├── 02-headline/              # 仿今日头条列表
│   ├── 03-broadcast/             # 广播机制
│   └── 03-save-qq/               # SharedPreferences 保存 QQ 账号密码
└── mobile-app-practice/          # 移动应用开发工程实践
    ├── 02-handler-webview/       # 实验2 Handler 的使用
    ├── 03-basic-animation/       # 实验3 基础动画
    └── 05-mqtt-smart-socket/     # 实验5 基于MQTT协议的智能插座

output/
├── android-app-development/      # Android 应用开发实验报告和截图
└── mobile-app-practice/          # 实践课报告和实际运行截图
```

## 一、Android 应用开发小实验

这部分是 GitHub 上最开始整理的几个 Android 基础小实验，主要用于练习常见控件、列表和本地存储。

| 实验 | 模块 | 目录 | 内容 |
| --- | --- | --- | --- |
| 注册界面 | `app` | [01-register-page](/Users/cuing/AndroidStudioProjects/android-course-projects/projects/android-app-development/01-register-page) | 注册页布局、输入校验、Toast 提示 |
| 仿今日头条列表 | `headlineApp` | [02-headline](/Users/cuing/AndroidStudioProjects/android-course-projects/projects/android-app-development/02-headline) | RecyclerView、多条目新闻列表 |
| 广播机制 | `broadcastApp` | [03-broadcast](/Users/cuing/AndroidStudioProjects/android-course-projects/projects/android-app-development/03-broadcast) | 无序广播、有序广播、拦截广播、指定接收者 |
| 保存 QQ 账号密码 | `saveQQApp` | [03-save-qq](/Users/cuing/AndroidStudioProjects/android-course-projects/projects/android-app-development/03-save-qq) | SharedPreferences 保存与回显 |

构建命令：

```bash
./gradlew :app:assembleDebug
./gradlew :headlineApp:assembleDebug
./gradlew :broadcastApp:assembleDebug
./gradlew :saveQQApp:assembleDebug
```

## 二、移动应用开发工程实践

这部分是现在课程的实验交付内容，代码、报告和运行截图已经单独整理。

| 实验 | 模块 | 目录 | 输出 |
| --- | --- | --- | --- |
| 实验2 Handler 的使用 | `practiceHandlerApp` | [02-handler-webview](/Users/cuing/AndroidStudioProjects/android-course-projects/projects/mobile-app-practice/02-handler-webview) | [报告与截图](/Users/cuing/AndroidStudioProjects/android-course-projects/output/mobile-app-practice/02-handler) |
| 实验3 基础动画 | `practiceAnimationApp` | [03-basic-animation](/Users/cuing/AndroidStudioProjects/android-course-projects/projects/mobile-app-practice/03-basic-animation) | [报告与截图](/Users/cuing/AndroidStudioProjects/android-course-projects/output/mobile-app-practice/03-basic-animation) |
| 实验5 基于MQTT协议的智能插座 | `practiceMqttSocketApp` | [05-mqtt-smart-socket](/Users/cuing/AndroidStudioProjects/android-course-projects/projects/mobile-app-practice/05-mqtt-smart-socket) | [报告与截图](/Users/cuing/AndroidStudioProjects/android-course-projects/output/mobile-app-practice/05-mqtt-smart-socket) |

构建命令：

```bash
./gradlew :practiceHandlerApp:assembleDebug
./gradlew :practiceAnimationApp:assembleDebug
./gradlew :practiceMqttSocketApp:assembleDebug
```

## Android Studio 使用方式

1. 直接打开仓库根目录 `android-course-projects/`。
2. 等待 Gradle 同步完成。
3. 在运行配置中选择对应模块：
   - Android 应用开发：`app`、`headlineApp`、`broadcastApp`、`saveQQApp`
   - 移动应用开发工程实践：`practiceHandlerApp`、`practiceAnimationApp`、`practiceMqttSocketApp`
4. 连接模拟器或真机后运行。

## 输出文件

- [实验2 Handler 的使用报告](/Users/cuing/AndroidStudioProjects/android-course-projects/output/mobile-app-practice/02-handler/实验2_Handler的使用_实验报告.docx)
- [实验2 运行截图](/Users/cuing/AndroidStudioProjects/android-course-projects/output/mobile-app-practice/02-handler/screenshots)
- [实验3 基础动画报告](/Users/cuing/AndroidStudioProjects/android-course-projects/output/mobile-app-practice/03-basic-animation/实验3_基础动画_实验报告.docx)
- [实验3 运行截图](/Users/cuing/AndroidStudioProjects/android-course-projects/output/mobile-app-practice/03-basic-animation/screenshots)
- [实验5 MQTT 智能插座报告](/Users/cuing/AndroidStudioProjects/android-course-projects/output/mobile-app-practice/05-mqtt-smart-socket/doc/实验5_MQTT智能插座_实验报告.docx)
- [实验5 MQTT 智能插座运行截图](/Users/cuing/AndroidStudioProjects/android-course-projects/output/mobile-app-practice/05-mqtt-smart-socket/screenshots)
- [实验三 广播机制报告](/Users/cuing/AndroidStudioProjects/android-course-projects/output/android-app-development/03-broadcast/课内实验报告_实验3-广播_已完成.docx)
- [实验三 广播机制运行截图](/Users/cuing/AndroidStudioProjects/android-course-projects/output/android-app-development/03-broadcast/screenshots)

## 维护约定

- 课程代码统一放在 `projects/` 下，先按课程名分组，再按实验编号分目录。
- 实验报告和运行截图统一放在 `output/` 下，并按课程名分组。
- 新增实验时同步更新 `settings.gradle` 和本 README，避免目录、模块名和报告输出不一致。
