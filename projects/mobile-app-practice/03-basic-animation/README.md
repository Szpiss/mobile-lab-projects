# 03-basic-animation

《移动应用开发工程实践》实验三：基础动画。

## 实验目标

使用 Android 原生动画完成逐帧动画和补间动画练习。应用启动后播放素材逐帧动画，并叠加旋转、缩放、透明度补间效果；点击“跳过”后显示 `Hello World!`，同时动画图片向右平移并移出屏幕。

## 实现内容

- 将 `anim_1.png` 到 `anim_21.png` 放入 `mipmap-hdpi` 资源目录
- 在 `res/drawable/anim.xml` 中定义 `animation-list` 逐帧动画
- 在主布局中使用 `ImageView` 作为动画容器
- 在 `MainActivity.java` 中使用 `AnimationDrawable` 启动逐帧动画
- 使用 `RotateAnimation`、`ScaleAnimation`、`AlphaAnimation` 组合补间动画
- 点击“跳过”后通过 `Handler` 定时触发 `TranslateAnimation`
- 输出模拟器真实运行截图和实验报告

## 关键文件

- [MainActivity.java](/Users/cuing/AndroidStudioProjects/android-course-projects/projects/mobile-app-practice/03-basic-animation/app/src/main/java/com/example/malllist/MainActivity.java)
- [activity_main.xml](/Users/cuing/AndroidStudioProjects/android-course-projects/projects/mobile-app-practice/03-basic-animation/app/src/main/res/layout/activity_main.xml)
- [anim.xml](/Users/cuing/AndroidStudioProjects/android-course-projects/projects/mobile-app-practice/03-basic-animation/app/src/main/res/drawable/anim.xml)
- [translate_anim.xml](/Users/cuing/AndroidStudioProjects/android-course-projects/projects/mobile-app-practice/03-basic-animation/app/src/main/res/anim/translate_anim.xml)
- [scale_anim.xml](/Users/cuing/AndroidStudioProjects/android-course-projects/projects/mobile-app-practice/03-basic-animation/app/src/main/res/anim/scale_anim.xml)
- [rotate_anim.xml](/Users/cuing/AndroidStudioProjects/android-course-projects/projects/mobile-app-practice/03-basic-animation/app/src/main/res/anim/rotate_anim.xml)
- [alpha_anim.xml](/Users/cuing/AndroidStudioProjects/android-course-projects/projects/mobile-app-practice/03-basic-animation/app/src/main/res/anim/alpha_anim.xml)

## 运行方式

在仓库根目录运行：

```bash
./gradlew :practiceAnimationApp:assembleDebug
```

也可以在 Android Studio 中选择 `practiceAnimationApp` 模块后运行。

## 输出文件

- 实验报告：[实验3_基础动画_实验报告.docx](/Users/cuing/AndroidStudioProjects/android-course-projects/output/mobile-app-practice/03-basic-animation/实验3_基础动画_实验报告.docx)
- 运行截图目录：[screenshots](/Users/cuing/AndroidStudioProjects/android-course-projects/output/mobile-app-practice/03-basic-animation/screenshots)
