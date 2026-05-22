# 01-register-page

安卓开发课程实验一：注册界面实战。

## 实验目标

实现一个完整的注册页面，练习 Android 常用基础控件的使用，包括：

- `TextView`
- `EditText`
- `ImageView`
- `RadioGroup` / `RadioButton`
- `CheckBox`
- `Button`

同时完成用户输入校验和点击事件处理。

## 这个实验做了哪些事

- 搭建了与课本示例接近的注册界面。
- 使用背景资源和图标资源还原页面效果。
- 抽取了公共样式，减少布局中的重复代码。
- 实现了姓名、邮箱、密码、性别、兴趣爱好的输入与选择。
- 点击“提交”按钮后完成表单校验。
- 校验通过后弹出“注册成功”提示，并把注册信息输出到 Logcat。

## 具体实现过程

### 1. 页面布局实现

主布局在 [activity_main.xml](/Users/cuing/AndroidStudioProjects/MyApplication/projects/android-app-development/01-register-page/app/src/main/res/layout/activity_main.xml)。

实现思路：

- 使用 `ScrollView` 包裹整个页面，避免小屏幕内容显示不全。
- 页面上方放置“注册”标题和 QQ / 微信注册入口。
- 中间部分用多行表单实现名字、邮箱、密码输入。
- 性别部分使用 `RadioGroup` 实现单选。
- 兴趣爱好部分使用 `CheckBox` 实现多选。
- 页面底部放置提交按钮。

### 2. 样式抽取

公共样式定义在 [styles.xml](/Users/cuing/AndroidStudioProjects/MyApplication/projects/android-app-development/01-register-page/app/src/main/res/values/styles.xml)。

这里主要抽取了：

- 横向分割线样式
- 纵向分割线样式
- 顶部注册入口文字样式
- 左侧标签文字样式
- 输入框样式

这样做的好处是：

- 布局 XML 更简洁
- 修改风格时更方便
- 多个控件可以复用同一套外观配置

### 3. 资源实现

主要资源包括：

- 背景：`register_bg.xml`
- 提交按钮背景：`submit_btn_bg.xml`
- 图标：`qq_icon.xml`、`weixin_icon.xml`、`email_icon.xml`

这些资源放在 `res/drawable/` 目录中，用于还原课本中的视觉效果。

### 4. 交互逻辑实现

逻辑代码在 [MainActivity.kt](/Users/cuing/AndroidStudioProjects/MyApplication/projects/android-app-development/01-register-page/app/src/main/java/com/example/myapplication/MainActivity.kt)。

实现过程：

- 在 `onCreate()` 中调用 `initView()` 获取所有控件实例。
- 给提交按钮设置点击监听。
- 给兴趣爱好复选框设置选中状态监听。
- 给性别单选框设置选中变化监听。

### 5. 数据校验过程

点击提交后，会按顺序执行以下校验：

1. 是否输入姓名
2. 是否输入邮箱
3. 是否输入密码
4. 是否选择性别
5. 是否选择兴趣爱好

如果某一步为空，就立刻弹出对应 `Toast`，不继续提交。

如果全部通过：

- 弹出“注册成功”
- 使用 `Log.i()` 输出用户信息

## 关键文件

- [MainActivity.kt](/Users/cuing/AndroidStudioProjects/MyApplication/projects/android-app-development/01-register-page/app/src/main/java/com/example/myapplication/MainActivity.kt)
- [activity_main.xml](/Users/cuing/AndroidStudioProjects/MyApplication/projects/android-app-development/01-register-page/app/src/main/res/layout/activity_main.xml)
- [styles.xml](/Users/cuing/AndroidStudioProjects/MyApplication/projects/android-app-development/01-register-page/app/src/main/res/values/styles.xml)
- [strings.xml](/Users/cuing/AndroidStudioProjects/MyApplication/projects/android-app-development/01-register-page/app/src/main/res/values/strings.xml)
- [colors.xml](/Users/cuing/AndroidStudioProjects/MyApplication/projects/android-app-development/01-register-page/app/src/main/res/values/colors.xml)

## 运行方式

在仓库根目录运行：

```bash
./gradlew :app:assembleDebug
```

在 Android Studio 中运行时，选择模块 `app`。

## 学到的内容

- Android 基础控件的组合使用
- XML 页面布局编写
- 样式复用
- 点击事件与状态变化监听
- 表单输入校验
