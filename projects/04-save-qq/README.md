# 实验四：保存 QQ 账号与密码

本实验对应课本 5.3.3「实战演练——保存 QQ 账号与密码」。

## 实验目标

- 学习 `SharedPreferences` 的基本使用方式。
- 使用 `SharedPreferences` 保存 QQ 账号和密码。
- 程序重新启动后，从本地 `data.xml` 文件中读取账号密码并自动回显。
- 理解 `getSharedPreferences("data", Context.MODE_PRIVATE)` 生成 `shared_prefs/data.xml` 的过程。

## 实现内容

- 创建包名为 `cn.itcast.saveqq` 的登录项目。
- 编写 `SPSaveQQ` 工具类，封装保存和读取账号密码的方法。
- 编写登录界面，包含头像、账号输入框、密码输入框、登录按钮和“记住密码”复选框。
- 点击“登录”后进行非空校验，登录成功后使用 `SPSaveQQ.saveUserInfo()` 保存账号和密码。
- 启动程序时调用 `SPSaveQQ.getUserInfo()`，如果本地已有数据，则自动显示到输入框中。

## 核心实现

`SPSaveQQ.saveUserInfo()` 使用 `SharedPreferences.Editor` 将账号保存为 `userName`，密码保存为 `pwd`，并通过 `commit()` 提交。

`SPSaveQQ.getUserInfo()` 从同一个 `data` 文件中读取 `userName` 和 `pwd`，放入 `Map` 后返回给 `MainActivity`。

## 关键文件

- `app/src/main/java/cn/itcast/saveqq/MainActivity.java`
- `app/src/main/java/cn/itcast/saveqq/SPSaveQQ.java`
- `app/src/main/res/layout/activity_main.xml`
- `app/src/main/res/drawable-hdpi/head.png`
- `app/src/main/res/drawable-hdpi/head1.png`

## 运行方式

在仓库根目录执行：

```bash
./gradlew :saveQQApp:assembleDebug
```

在 Android Studio 顶部运行配置中选择 `saveQQApp` 后运行即可。
