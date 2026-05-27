# 实验三：基础动画

本实验使用 Android 原生动画完成逐帧动画和补间动画练习。

## 功能

- 使用 `animation-list` 播放逐帧动画。
- 使用旋转、缩放、透明度动画组合显示启动效果。
- 点击“跳过”后显示 `Hello World!`。
- 使用 Handler 延时触发平移动画。

## 关键文件

- `app/src/main/java/com/example/malllist/MainActivity.java`
- `app/src/main/res/layout/activity_main.xml`
- `app/src/main/res/drawable/anim.xml`
- `app/src/main/res/anim/alpha_anim.xml`
- `app/src/main/res/anim/rotate_anim.xml`
- `app/src/main/res/anim/scale_anim.xml`
- `app/src/main/res/anim/translate_anim.xml`

## 运行

```bash
./gradlew :practiceAnimationApp:assembleDebug
```

在 Android Studio 中选择 `practiceAnimationApp` 运行。
